package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Distance;
import com.example.bukkinyulszbackend.model.Marking;
import com.example.bukkinyulszbackend.services.MarkingService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_MARKING)
public class MarkingController implements BaseControllerInterface<Marking>{
    private MarkingService markingService;

    @Autowired
    public void setMarkingService(MarkingService markingService) {
        this.markingService = markingService;
    }

    @Override
    public ResponseEntity<List<Marking>> list() throws BusinessException {
        List<Marking> markingList = this.markingService.list();
        return ResponseEntity.ok(markingList);
    }

    @Override
    public ResponseEntity<Marking> getById(long id) throws BusinessException {
        Marking marking = this.markingService.getById(id);
        return ResponseEntity.ok(marking);
    }

    @Override
    public ResponseEntity<Marking> add(Marking newData) throws BusinessException {
        Marking marking = this.markingService.add(newData);
        return ResponseEntity.ok(marking);
    }

    @Override
    public ResponseEntity<Marking> edit(Marking data) throws BusinessException {
        Marking marking = this.markingService.edit(data);
        return ResponseEntity.ok(marking);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        Boolean result = this.markingService.delete(id);
        return ResponseEntity.ok(result);
    }
}
