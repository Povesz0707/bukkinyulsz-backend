package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Distance;
import com.example.bukkinyulszbackend.model.Marking;
import com.example.bukkinyulszbackend.repository.MarkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MarkingService extends BaseService<Marking> implements BaseServiceInterface<Marking>{
    private MarkingRepository markingRepository;

    @Autowired
    public void setMarkingRepository(MarkingRepository markingRepository) {
        this.markingRepository = markingRepository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        this.markingRepository.deleteById(id);
        this.markingRepository.flush();
        return true;
    }

    @Override
    public Marking add(Marking data) throws BusinessException {
        Marking marking = this.markingRepository.save(data);
        this.markingRepository.flush();
        return marking;
    }

    @Override
    public List<Marking> list() throws BusinessException {
        List<Marking> markingList = this.markingRepository.findAll();
        return markingList;
    }

    @Override
    public Marking getById(long id) throws BusinessException {
        Optional<Marking> optionalMarking = this.markingRepository.findById(id);
        return isPresent(optionalMarking);
    }

    @Override
    public Marking edit(Marking data) throws BusinessException {
        Marking marking = getById(data.getId());
        marking.edit(data);
        Marking saved = this.markingRepository.save(marking);
        this.markingRepository.flush();
        return saved;
    }
}
