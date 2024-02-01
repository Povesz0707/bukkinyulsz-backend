package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.controller.interfaces.BaseControllerInterface;
import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.SubSectionMarking;
import com.example.bukkinyulszbackend.services.SubSectionMarkingService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_SUB_SECTION_MARKING)
public class SubSectionMarkingController extends BaseController<SubSectionMarking> implements BaseControllerInterface<SubSectionMarking> {
    private SubSectionMarkingService subSectionMarkingService;

    @Autowired
    public void setSubSectionMarkingService(SubSectionMarkingService subSectionMarkingService) {
        this.subSectionMarkingService = subSectionMarkingService;
    }

    @Override
    public ResponseEntity<List<SubSectionMarking>> list() throws BusinessException {
        final List<SubSectionMarking> subSectionMarkingList = this.subSectionMarkingService.list();
        return returnListResponse(subSectionMarkingList);
    }

    @Override
    public ResponseEntity<SubSectionMarking> getById(long id) throws BusinessException {
        final SubSectionMarking subSectionMarking = this.subSectionMarkingService.getById(id);
        return returnSimpleResponse(subSectionMarking);
    }

    @Override
    public ResponseEntity<SubSectionMarking> add(SubSectionMarking newData) throws BusinessException {
        final SubSectionMarking subSectionMarking = this.subSectionMarkingService.add(newData);
        return returnSimpleResponse(subSectionMarking);
    }

    @Override
    public ResponseEntity<SubSectionMarking> edit(SubSectionMarking data) throws BusinessException {
        final SubSectionMarking subSectionMarking = this.subSectionMarkingService.edit(data);
        return returnSimpleResponse(subSectionMarking);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        final Boolean result = this.subSectionMarkingService.delete(id);
        return returnBooleanResponse(result);
    }
}
