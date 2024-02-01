package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.controller.interfaces.BaseControllerInterface;
import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.SubSection;
import com.example.bukkinyulszbackend.services.SubSectionService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_SUB_SECTION)
public class SubSectionController extends BaseController<SubSection> implements BaseControllerInterface<SubSection> {
    private SubSectionService subSectionService;

    @Autowired
    public void setSubSectionService(SubSectionService subSectionService) {
        this.subSectionService = subSectionService;
    }

    @Override
    public ResponseEntity<List<SubSection>> list() throws BusinessException {
        final List<SubSection> subSectionList = this.subSectionService.list();
        return returnListResponse(subSectionList);
    }

    @Override
    public ResponseEntity<SubSection> getById(long id) throws BusinessException {
        final SubSection subSection = this.subSectionService.getById(id);
        return returnSimpleResponse(subSection);
    }

    @Override
    public ResponseEntity<SubSection> add(SubSection newData) throws BusinessException {
        final SubSection subSection = this.subSectionService.add(newData);
        return returnSimpleResponse(subSection);
    }

    @Override
    public ResponseEntity<SubSection> edit(SubSection data) throws BusinessException {
        final SubSection subSection = this.subSectionService.edit(data);
        return returnSimpleResponse(subSection);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        final Boolean result = this.subSectionService.delete(id);
        return returnBooleanResponse(result);
    }
}
