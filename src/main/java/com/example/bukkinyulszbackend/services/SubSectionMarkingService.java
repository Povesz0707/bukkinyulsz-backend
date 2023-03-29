package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Marking;
import com.example.bukkinyulszbackend.model.Reward;
import com.example.bukkinyulszbackend.model.SubSectionMarking;
import com.example.bukkinyulszbackend.repository.SubSectionMarkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubSectionMarkingService implements BaseServiceInterface<SubSectionMarking>{
    private SubSectionMarkingRepository subSectionMarkingRepository;

    @Autowired
    public void setMarkingRepository(SubSectionMarkingRepository markingRepository) {
        this.subSectionMarkingRepository = markingRepository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        this.subSectionMarkingRepository.deleteById(id);
        this.subSectionMarkingRepository.flush();
        return true;
    }

    @Override
    public SubSectionMarking add(SubSectionMarking data) throws BusinessException {
        SubSectionMarking subSectionMarking = this.subSectionMarkingRepository.save(data);
        this.subSectionMarkingRepository.flush();
        return subSectionMarking;
    }

    @Override
    public List<SubSectionMarking> list() throws BusinessException {
        List<SubSectionMarking> subSectionMarkingList = this.subSectionMarkingRepository.findAll();
        return subSectionMarkingList;
    }

    @Override
    public SubSectionMarking getById(long id) throws BusinessException {
        Optional<SubSectionMarking> optionalSubSectionMarking = this.subSectionMarkingRepository.findById(id);
        if(optionalSubSectionMarking.isPresent()){
            SubSectionMarking subSectionMarking = optionalSubSectionMarking.get();
            return subSectionMarking;
        }
        else{
            throw new BusinessException(BusinessException.HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND);
        }
    }

    @Override
    public SubSectionMarking edit(SubSectionMarking data) throws BusinessException {
        SubSectionMarking subSectionMarking = getById(data.getId());
        subSectionMarking.edit(data);
        SubSectionMarking savedSubSectionMarking = this.subSectionMarkingRepository.save(subSectionMarking);
        this.subSectionMarkingRepository.flush();
        return savedSubSectionMarking;
    }
}
