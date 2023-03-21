package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.SubSection;
import com.example.bukkinyulszbackend.repository.SubSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubSectionService implements BaseServiceInterface<SubSection>{
    private SubSectionRepository subSectionRepository;

    @Autowired
    public void setSectionRepository(SubSectionRepository subSectionRepository) {
        this.subSectionRepository = subSectionRepository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        this.subSectionRepository.deleteById(id);
        this.subSectionRepository.flush();
        return true;
    }

    @Override
    public SubSection add(SubSection data) throws BusinessException {
        SubSection subSection = this.subSectionRepository.save(data);
        this.subSectionRepository.flush();
        return subSection;
    }

    @Override
    public List<SubSection> list() throws BusinessException {
        List<SubSection> subSectionList = this.subSectionRepository.findAll();
        return subSectionList;
    }

    @Override
    public SubSection getById(long id) throws BusinessException {
        Optional<SubSection> optionalSection = this.subSectionRepository.findById(id);
        if(optionalSection.isPresent()){
            SubSection subSection = optionalSection.get();
            return subSection;
        }
        else{
            throw new BusinessException(BusinessException.HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND);
        }
    }

    @Override
    public SubSection edit(SubSection data) throws BusinessException {
        SubSection subSection = getById(data.getId());
        subSection.edit(data);
        SubSection saveDSubSection = this.subSectionRepository.save(subSection);
        this.subSectionRepository.flush();
        return saveDSubSection;
    }
}
