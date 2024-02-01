package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.controller.interfaces.ActiveStatusInterface;
import com.example.bukkinyulszbackend.controller.interfaces.BaseControllerInterface;
import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.GaleryImage;
import com.example.bukkinyulszbackend.model.News;
import com.example.bukkinyulszbackend.services.GaleryImageService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_GALERY_IMAGE)
public class GaleryImageController extends BaseController<GaleryImage> implements ActiveStatusInterface<GaleryImage> {
    private GaleryImageService service;

    @Autowired
    public void setService(GaleryImageService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<GaleryImage>> list() throws BusinessException {
        final List<GaleryImage> fileStores = this.service.list();
        return returnListResponse(fileStores);
    }

    @Override
    public ResponseEntity<GaleryImage> getById(long id) throws BusinessException {
        final GaleryImage fileStore = this.service.getById(id);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<GaleryImage> add(GaleryImage newData) throws BusinessException {
        final GaleryImage fileStore = this.service.add(newData);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<GaleryImage> edit(GaleryImage data) throws BusinessException {
        final GaleryImage fileStore = this.service.edit(data);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        final Boolean result = this.service.delete(id);
        return returnBooleanResponse(result);
    }

    @Override
    public ResponseEntity<List<GaleryImage>> activeList() throws BusinessException {
        List<GaleryImage> list = this.service.findAllActiveDesc();
        return returnListResponse(list);
    }
}
