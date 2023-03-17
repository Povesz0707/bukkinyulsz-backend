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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    ResponseEntity<TourEvent> getLatestActive()  throws BusinessException{
        System.out.println("CALLED");
        final TourEvent tourEvent = this.tourEventService.getLatestActive();
        return returnSimpleResponse(tourEvent);
    }
}
