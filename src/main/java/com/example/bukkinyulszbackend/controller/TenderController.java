package com.example.bukkinyulszbackend.controller;


import com.example.bukkinyulszbackend.controller.interfaces.ActiveStatusInterface;
import com.example.bukkinyulszbackend.controller.interfaces.BaseControllerInterface;
import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Reward;
import com.example.bukkinyulszbackend.model.Sponsor;
import com.example.bukkinyulszbackend.model.Tender;
import com.example.bukkinyulszbackend.services.SponsorService;
import com.example.bukkinyulszbackend.services.TenderService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_TENDER)
public class TenderController extends BaseController<Tender> implements ActiveStatusInterface<Tender> {

    private TenderService service;

    @Autowired
    public void setService(TenderService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<Tender>> activeList() throws BusinessException {
        List<Tender> list = this.service.findAllActive();
        return returnListResponse(list);
    }

    @Override
    public ResponseEntity<List<Tender>> list() throws BusinessException {
        final List<Tender> fileStores = this.service.list();
        return returnListResponse(fileStores);
    }

    @Override
    public ResponseEntity<Tender> getById(long id) throws BusinessException {
        final Tender fileStore = this.service.getById(id);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<Tender> add(Tender newData) throws BusinessException {
        final Tender fileStore = this.service.add(newData);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<Tender> edit(Tender data) throws BusinessException {
        final Tender fileStore = this.service.edit(data);
        return returnSimpleResponse(fileStore);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        final Boolean result = this.service.delete(id);
        return returnBooleanResponse(result);
    }

}
