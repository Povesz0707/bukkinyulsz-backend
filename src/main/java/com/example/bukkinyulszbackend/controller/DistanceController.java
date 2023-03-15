package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Distance;
import com.example.bukkinyulszbackend.services.DistanceService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_DISTANCE)
public class DistanceController implements BaseControllerInterface<Distance>{

    private DistanceService distanceService;

    @Autowired
    public void setDistanceService(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @Override
    public ResponseEntity<List<Distance>> list() throws BusinessException {
        List<Distance> distanceList = this.distanceService.list();
        return ResponseEntity.ok(distanceList);
    }

    @Override
    public ResponseEntity<Distance> getById(long id) throws BusinessException {
        Distance distance = this.distanceService.getById(id);
        return ResponseEntity.ok(distance);
    }

    @Override
    public ResponseEntity<Distance> add(Distance newData) throws BusinessException {
        Distance saved = this.distanceService.add(newData);
        return ResponseEntity.ok(saved);
    }

    @Override
    public ResponseEntity<Distance> edit(Distance data) throws BusinessException {
        Distance saved = this.distanceService.edit(data);
        return ResponseEntity.ok(saved);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        this.distanceService.delete(id);
        return ResponseEntity.ok(true);
    }
}
