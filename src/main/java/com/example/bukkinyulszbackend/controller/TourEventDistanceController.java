package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.controller.interfaces.BaseControllerInterface;
import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.*;
import com.example.bukkinyulszbackend.services.TourEventDistanceService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_TOUR_EVENT_DISTANCE)
public class TourEventDistanceController extends BaseController<TourEventDistance> implements BaseControllerInterface<TourEventDistance> {
    private TourEventDistanceService tourEventDistanceService;

    @Autowired
    public void setTourEventDistanceService(TourEventDistanceService tourEventDistanceService) {
        this.tourEventDistanceService = tourEventDistanceService;
    }

    @Override
    public ResponseEntity<List<TourEventDistance>> list() throws BusinessException {
        final List<TourEventDistance> tourEventDistanceList = this.tourEventDistanceService.list();
        return returnListResponse(tourEventDistanceList);
    }

    @Override
    public ResponseEntity<TourEventDistance> getById(long id) throws BusinessException {
        final TourEventDistance tourEventDistance = this.tourEventDistanceService.getById(id);
        return returnSimpleResponse(tourEventDistance);
    }

    @Override
    public ResponseEntity<TourEventDistance> add(TourEventDistance newData) throws BusinessException {
        final TourEventDistance tourEventDistance = this.tourEventDistanceService.add(newData);
        return returnSimpleResponse(tourEventDistance);
    }

    @Override
    public ResponseEntity<TourEventDistance> edit(TourEventDistance data) throws BusinessException {
        final TourEventDistance tourEventDistance = this.tourEventDistanceService.edit(data);
        return returnSimpleResponse(tourEventDistance);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        final Boolean result = this.tourEventDistanceService.delete(id);
        return returnBooleanResponse(result);
    }

    @PostMapping(AppConstant.URI_API_GET_BY+"/"+AppConstant.URI_API_DISTANCE)
    @Transactional(rollbackFor = BusinessException.class)
    ResponseEntity<List<TourEventDistance>> findAllByDistance(@RequestBody final Distance distance)  throws BusinessException{
        List<TourEventDistance> tourEventDistances = this.tourEventDistanceService.findAllByDistance(distance);
        return returnListResponse(tourEventDistances);
    }

    @PostMapping(AppConstant.URI_API_GET_BY+"/"+AppConstant.URI_API_TOUR_EVENT)
    @Transactional(rollbackFor = BusinessException.class)
    ResponseEntity<List<TourEventDistance>> findAllByTourEvent(@RequestBody final TourEvent tourEvent)  throws BusinessException{
        List<TourEventDistance> tourEventDistances = this.tourEventDistanceService.findAllByTourEvent(tourEvent);
        return returnListResponse(tourEventDistances);
    }

}
