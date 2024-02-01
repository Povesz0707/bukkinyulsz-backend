package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.controller.interfaces.BaseControllerInterface;
import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.FileStore;
import com.example.bukkinyulszbackend.model.payload.imageUpload.FileUploadRequest;
import com.example.bukkinyulszbackend.model.payload.imageUpload.FileUploadResponse;
import com.example.bukkinyulszbackend.services.FileStoreService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_FILE_STORE)
public class FileStoreController extends BaseController<FileStore> implements BaseControllerInterface<FileStore> {

    private String FILE_PATH_ROOT = "C:/imageStore/";
    private FileStoreService fileStoreService;

    @Autowired
    public void setImageStoreService(FileStoreService fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    @Override
    public ResponseEntity<List<FileStore>> list() throws BusinessException {
        final List<FileStore> fileStores = this.fileStoreService.list();
        return returnListResponse(fileStores);
    }

    @Override
    public ResponseEntity<FileStore> getById(long id) throws BusinessException {
        final FileStore fileStore = this.fileStoreService.getById(id);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<FileStore> add(FileStore newData) throws BusinessException {
        final FileStore fileStore = this.fileStoreService.add(newData);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<FileStore> edit(FileStore data) throws BusinessException {
        final FileStore fileStore = this.fileStoreService.edit(data);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        final Boolean result = this.fileStoreService.delete(id);
        return returnBooleanResponse(result);
    }

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) throws BusinessException {
        byte[] image = new byte[0];
        String mimeType = "";
        try {
            File file = new File(FILE_PATH_ROOT + filename);
            mimeType = URLConnection.guessContentTypeFromName(file.getName());

            image = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            final String decriptedString = new String(Base64.decodeBase64(filename), StandardCharsets.UTF_8);
            try {
                final String stringDir = FILE_PATH_ROOT + decriptedString;
                File file = new File(stringDir);
                mimeType = URLConnection.guessContentTypeFromName(file.getName());
                if(mimeType == null){
                    int dotIndex = stringDir.lastIndexOf(".");
                    final String postFix = stringDir.substring(dotIndex);
                    if(postFix.equals(".gpx")){
                        mimeType = "text/xml";
                    }
                }
                image = FileUtils.readFileToByteArray(file);
            } catch (IOException ex) {
                ResponseEntity.ok(BusinessException.HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND);
            }
        }
        if(mimeType != null){
            //application/octet-stream downlaod mimitype
            return ResponseEntity.ok().contentType(MediaType.asMediaType(MimeType.valueOf(mimeType))).body(image);
        }
        return null;
    }


    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse> getFile(@RequestBody final FileUploadRequest fileUploadRequest) throws BusinessException {
        final String imageDataBytes = fileUploadRequest.getBase64().substring(fileUploadRequest.getBase64().indexOf(",") + 1);
        byte[] data = Base64.decodeBase64(imageDataBytes);
            int dotIndex = fileUploadRequest.getName().lastIndexOf(".");
            final String fileName = UUID.randomUUID() + fileUploadRequest.getName().substring(dotIndex);
            final String savingDir = FILE_PATH_ROOT + fileUploadRequest.getFolder();
            createDirIfNotExists(FILE_PATH_ROOT + fileUploadRequest.getFolder());
            final String fullPath = savingDir + "/" +fileName;
            final String encodedFilePath = java.util.Base64.getEncoder().encodeToString((fileUploadRequest.getFolder()+"/"+fileName).getBytes());

            try (OutputStream stream = new FileOutputStream(fullPath)) {
                stream.write(data);
                FileStore fileStore = new FileStore();
                fileStore.setName(fileName);
                fileStore.setBaseType(fileUploadRequest.getBaseType());
                fileStore.setPath(encodedFilePath);
                add(fileStore);
            }catch (Exception ex){
                throw new BusinessException(ex.getMessage(), ex);
            }
        return ResponseEntity.ok(new FileUploadResponse(encodedFilePath));
    }

    private void createDirIfNotExists(final String dir) throws BusinessException {
        try {
            Files.createDirectories(Paths.get(dir));
        }catch (Exception ex){
            throw  new BusinessException(ex.getMessage(), ex);
        }
    }

}
