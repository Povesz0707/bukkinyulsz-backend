package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Checkpoint;
import com.example.bukkinyulszbackend.model.SubSection;
import com.example.bukkinyulszbackend.repository.CheckpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CheckpointService implements BaseServiceInterface<Checkpoint>{
    private CheckpointRepository checkpointRepository;

    @Autowired
    public void setCheckpointRepository(CheckpointRepository checkpointRepository) {
        this.checkpointRepository = checkpointRepository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        final Checkpoint checkpoint = getById(id);
        this.checkpointRepository.delete(checkpoint);
        this.checkpointRepository.flush();
        return true;
    }

    @Override
    public Checkpoint add(Checkpoint data) throws BusinessException {
        Checkpoint checkpoint = this.checkpointRepository.save(data);
        this.checkpointRepository.flush();
        return checkpoint;
    }

    @Override
    public List<Checkpoint> list() throws BusinessException {
        List<Checkpoint> checkpointList = this.checkpointRepository.findAll();
        return checkpointList;
    }

    @Override
    public Checkpoint getById(long id) throws BusinessException {
        final Optional<Checkpoint> optionalCheckpoint = this.checkpointRepository.findById(id);
        if(optionalCheckpoint.isPresent()){
            final Checkpoint checkpoint = optionalCheckpoint.get();
            return checkpoint;
        }
        else{
            throw new BusinessException(BusinessException.HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND);
        }
    }

    @Override
    public Checkpoint edit(Checkpoint data) throws BusinessException {
        Checkpoint checkpoint = this.getById(data.getId());
        checkpoint.edit(data);
        Checkpoint savedCheckpoint = this.checkpointRepository.save(checkpoint);
        this.checkpointRepository.flush();
        return savedCheckpoint;
    }
}
