package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Reward;
import com.example.bukkinyulszbackend.model.SubSection;
import com.example.bukkinyulszbackend.model.TourEvent;
import com.example.bukkinyulszbackend.model.TourEventDistance;
import com.example.bukkinyulszbackend.services.TourEventService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_TOUR_EVENT)
public class TourEventController extends BaseController<TourEvent> implements BaseControllerInterface<TourEvent>{
    private TourEventService tourEventService;

    @Autowired
    public void setTourEventService(TourEventService tourEventService) {
        this.tourEventService = tourEventService;
    }

    @Override
    public ResponseEntity<List<TourEvent>> list() throws BusinessException {
        final List<TourEvent> tourEventList = this.tourEventService.list();
        return returnListResponse(tourEventList);
    }

    @Override
    public ResponseEntity<TourEvent> getById(long id) throws BusinessException {
        final TourEvent tourEvent = this.tourEventService.getById(id);
        return returnSimpleResponse(tourEvent);
    }

    @Override
    public ResponseEntity<TourEvent> add(TourEvent newData) throws BusinessException {
        final TourEvent tourEvent = this.tourEventService.add(newData);
        return returnSimpleResponse(tourEvent);
    }

    @Override
    public ResponseEntity<TourEvent> edit(TourEvent data) throws BusinessException {
        final TourEvent tourEvent = this.tourEventService.edit(data);
        return returnSimpleResponse(tourEvent);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        final Boolean result = this.tourEventService.delete(id);
        return returnBooleanResponse(result);
    }

    @GetMapping(AppConstant.URI_API_GET + "/"+AppConstant.URI_API_TOUR_EVENT_GET_LATEST_ACTIVE)
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseEntity<TourEvent> getLatestActive()  throws BusinessException{
        final TourEvent tourEvent = this.tourEventService.getLatestActive();
        return returnSimpleResponse(tourEvent);
    }

    @GetMapping(AppConstant.URI_API_LIST + "/" + AppConstant.URI_API_ACTIVE)
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseEntity<List<TourEvent>> getActives()  throws BusinessException{
        final List<TourEvent> tourEvents = this.tourEventService.getActives();
        return returnListResponse(tourEvents);
    }


    @PostMapping(AppConstant.URI_API_UPDATE_ACTIVE_STATUS+"/{status}")
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseEntity<Boolean> updateActiveStatus(@PathVariable final Boolean status,@RequestBody final List<TourEvent> tourEventList)  throws BusinessException{
        final Boolean result = this.tourEventService.updateActiveStatus(tourEventList, status);
        return returnBooleanResponse(result);
    }
}
