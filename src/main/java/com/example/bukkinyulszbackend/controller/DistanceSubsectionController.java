package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.controller.interfaces.BaseControllerInterface;
import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.*;
import com.example.bukkinyulszbackend.services.DistanceSubsectionService;
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
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_DISTANCE_SUB_SECTION)
public class DistanceSubsectionController extends BaseController<DistanceSubsection> implements BaseControllerInterface<DistanceSubsection> {
    private DistanceSubsectionService distanceSubsectionService;

    @Autowired
    public void setTourEventSubsectionService(DistanceSubsectionService distanceSubsectionService) {
        this.distanceSubsectionService = distanceSubsectionService;
    }

    @Override
    public ResponseEntity<List<DistanceSubsection>> list() throws BusinessException {
        final List<DistanceSubsection> tourEventSubsectionList = this.distanceSubsectionService.list();
        return returnListResponse(tourEventSubsectionList);
    }

    @Override
    public ResponseEntity<DistanceSubsection> getById(long id) throws BusinessException {
        final DistanceSubsection tourEventSubsection = this.distanceSubsectionService.getById(id);
        return returnSimpleResponse(tourEventSubsection);
    }

    @Override
    public ResponseEntity<DistanceSubsection> add(DistanceSubsection newData) throws BusinessException {
        final DistanceSubsection tourEventSubsection = this.distanceSubsectionService.add(newData);
        return returnSimpleResponse(tourEventSubsection);
    }

    @Override
    public ResponseEntity<DistanceSubsection> edit(DistanceSubsection data) throws BusinessException {
        final DistanceSubsection tourEventSubsection = this.distanceSubsectionService.edit(data);
        return returnSimpleResponse(tourEventSubsection);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        final Boolean result = this.distanceSubsectionService.delete(id);
        return returnBooleanResponse(result);
    }

    @PostMapping(AppConstant.URI_API_GET_BY+"/"+AppConstant.URI_API_DISTANCE)
    @Transactional(rollbackFor = BusinessException.class)
    ResponseEntity<List<DistanceSubsection>> findAllByDistance(@RequestBody final Distance distance)  throws BusinessException{
        List<DistanceSubsection> distanceSubsections = this.distanceSubsectionService.findAllByDistance(distance);
        return returnListResponse(distanceSubsections);
    }

    @PostMapping(AppConstant.URI_API_GET_BY+"/"+AppConstant.URI_API_SUB_SECTION)
    @Transactional(rollbackFor = BusinessException.class)
    ResponseEntity<List<DistanceSubsection>> findAllBySubSection(@RequestBody final SubSection subSection)  throws BusinessException{
        List<DistanceSubsection> distanceSubsections = this.distanceSubsectionService.findAllBySubSection(subSection);
        return returnListResponse(distanceSubsections);
    }
}
