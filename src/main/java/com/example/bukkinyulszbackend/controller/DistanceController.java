package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Distance;
import com.example.bukkinyulszbackend.model.TourEvent;
import com.example.bukkinyulszbackend.repository.TourEventRepository;
import com.example.bukkinyulszbackend.services.DistanceService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_DISTANCE)
public class DistanceController extends BaseController<Distance> implements BaseControllerInterface<Distance>{
    private DistanceService distanceService;

    private TourEventRepository tourEventRepository;

    @Autowired
    public void setTourEventRepository(TourEventRepository tourEventRepository) {
        this.tourEventRepository = tourEventRepository;
    }

    @Autowired
    public void setDistanceService(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @Override
    public ResponseEntity<List<Distance>> list() throws BusinessException {
        final List<Distance> distanceList = this.distanceService.list();
        return returnListResponse(distanceList);
    }

    @GetMapping(AppConstant.URI_API_LIST+"1")
    @Transactional(rollbackFor = BusinessException.class)
    ResponseEntity<List<TourEvent>> listTour()  throws BusinessException{
        final List<TourEvent> list = this.tourEventRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<Distance> getById(long id) throws BusinessException {
        final Distance distance = this.distanceService.getById(id);
        return returnSimpleResponse(distance);
    }

    @Override
    public ResponseEntity<Distance> add(Distance newData) throws BusinessException {
        final Distance saved = this.distanceService.add(newData);
        return returnSimpleResponse(saved);
    }

    @Override
    public ResponseEntity<Distance> edit(Distance data) throws BusinessException {
        final Distance saved = this.distanceService.edit(data);
        return returnSimpleResponse(saved);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        final Boolean result = this.distanceService.delete(id);
        return returnBooleanResponse(result);
    }
}
