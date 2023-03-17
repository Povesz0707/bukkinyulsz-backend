package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Checkpoint;
import com.example.bukkinyulszbackend.model.Marking;
import com.example.bukkinyulszbackend.services.CheckpointService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_CHECKPOINT)
public class CheckpointController extends BaseController<Checkpoint> implements BaseControllerInterface<Checkpoint>{
    private CheckpointService checkpointService;

    @Autowired
    public void setCheckpointService(CheckpointService checkpointService) {
        this.checkpointService = checkpointService;
    }

    @Override
    public ResponseEntity<List<Checkpoint>> list() throws BusinessException {
        final List<Checkpoint> checkpointList = this.checkpointService.list();
        return returnListResponse(checkpointList);
    }

    @Override
    public ResponseEntity<Checkpoint> getById(long id) throws BusinessException {
        final Checkpoint checkpoint = this.checkpointService.getById(id);
        return returnSimpleResponse(checkpoint);
    }

    @Override
    public ResponseEntity<Checkpoint> add(Checkpoint newData) throws BusinessException {
        final Checkpoint checkpoint = this.checkpointService.add(newData);
        return returnSimpleResponse(checkpoint);
    }

    @Override
    public ResponseEntity<Checkpoint> edit(Checkpoint data) throws BusinessException {
        final Checkpoint checkpoint = this.checkpointService.edit(data);
        return returnSimpleResponse(checkpoint);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        final Boolean result = this.checkpointService.delete(id);
        return returnBooleanResponse(result);
    }
}
