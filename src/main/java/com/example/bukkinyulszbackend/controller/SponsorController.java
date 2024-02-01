package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.controller.interfaces.ActiveStatusInterface;
import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.News;
import com.example.bukkinyulszbackend.model.Sponsor;
import com.example.bukkinyulszbackend.services.SponsorService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_SPONSOR)
public class SponsorController extends BaseController<Sponsor> implements ActiveStatusInterface<Sponsor> {
    private SponsorService service;

    @Autowired
    public void setService(SponsorService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<Sponsor>> activeList() throws BusinessException {
        List<Sponsor> list = this.service.findAllActive();
        return returnListResponse(list);
    }

    @Override
    public ResponseEntity<List<Sponsor>> list() throws BusinessException {
        final List<Sponsor> fileStores = this.service.list();
        return returnListResponse(fileStores);
    }

    @Override
    public ResponseEntity<Sponsor> getById(long id) throws BusinessException {
        final Sponsor fileStore = this.service.getById(id);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<Sponsor> add(Sponsor newData) throws BusinessException {
        final Sponsor fileStore = this.service.add(newData);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<Sponsor> edit(Sponsor data) throws BusinessException {
        final Sponsor fileStore = this.service.edit(data);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        final Boolean result = this.service.delete(id);
        return returnBooleanResponse(result);
    }
}
