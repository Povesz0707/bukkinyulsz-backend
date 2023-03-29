package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.FileStore;
import com.example.bukkinyulszbackend.repository.ImageStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FileStoreService implements BaseServiceInterface<FileStore>{
    private ImageStoreRepository imageStoreRepository;

    @Autowired
    public void setImageStoreRepository(ImageStoreRepository imageStoreRepository) {
        this.imageStoreRepository = imageStoreRepository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        final FileStore fileStore = getById(id);
        this.imageStoreRepository.delete(fileStore);
        this.imageStoreRepository.flush();
        return true;
    }

    @Override
    public FileStore add(FileStore data) throws BusinessException {
        FileStore fileStore = this.imageStoreRepository.save(data);
        this.imageStoreRepository.flush();
        return fileStore;
    }

    @Override
    public List<FileStore> list() throws BusinessException {
        List<FileStore> fileStores = this.imageStoreRepository.findAll();
        return fileStores;
    }

    @Override
    public FileStore getById(long id) throws BusinessException {
        final Optional<FileStore> optionalImageStore = this.imageStoreRepository.findById(id);
        if(optionalImageStore.isPresent()){
            final FileStore fileStore = optionalImageStore.get();
            return fileStore;
        }
        else{
            throw new BusinessException(BusinessException.HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND);
        }
    }

    @Override
    public FileStore edit(FileStore data) throws BusinessException {
        FileStore fileStore = this.getById(data.getId());
        fileStore.edit(data);
        FileStore savedCheckpoint = this.imageStoreRepository.save(fileStore);
        this.imageStoreRepository.flush();
        return savedCheckpoint;
    }
}
