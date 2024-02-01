package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.controller.interfaces.BaseControllerInterface;
import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.CompetitorRegistration;
import com.example.bukkinyulszbackend.model.payload.CompetitorRegistrationSimpleRequest;
import com.example.bukkinyulszbackend.services.CompetitorRegistrationService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_COMPETITOR_REGISTRATION)
public class CompetitorRegistrationController  extends BaseController<CompetitorRegistrationController> implements BaseControllerInterface<CompetitorRegistration> {
    private CompetitorRegistrationService competitorRegistrationService;

    @Autowired
    private void setCompetitorRegistrationService(CompetitorRegistrationService competitorRegistrationService) {
        this.competitorRegistrationService = competitorRegistrationService;
    }

    @PostMapping(AppConstant.URI_API_REGISTRATION)
    @Transactional(rollbackFor = BusinessException.class)
    public List<CompetitorRegistration> competitorRegistrationFirstStep(@RequestBody final CompetitorRegistrationSimpleRequest registrationSimpleRequest){
        // call after billing successfully
        List<CompetitorRegistration> list = this.competitorRegistrationService.competitorRegistration(registrationSimpleRequest);
        // create bill
        // create barcode(s) pdf
        // send email with barcode(s)
        return list;
    }


    @Override
    public ResponseEntity<List<CompetitorRegistration>> list() throws BusinessException {
//        return returnListResponse(this.competitorRegistrationService.list());
    return null;
    }

    @Override
    public ResponseEntity<CompetitorRegistration> getById(long id) throws BusinessException {
        return null;
    }

    @Override
    public ResponseEntity<CompetitorRegistration> add(CompetitorRegistration newData) throws BusinessException {
        return null;
    }

    @Override
    public ResponseEntity<CompetitorRegistration> edit(CompetitorRegistration data) throws BusinessException {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        return null;
    }

}
