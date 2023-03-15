package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Marking;
import com.example.bukkinyulszbackend.model.Section;
import com.example.bukkinyulszbackend.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SectionService implements BaseServiceInterface<Section>{
    private SectionRepository sectionRepository;

    @Autowired
    public void setSectionRepository(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        this.sectionRepository.deleteById(id);
        this.sectionRepository.flush();
        return true;
    }

    @Override
    public Section add(Section data) throws BusinessException {
        Section section = this.sectionRepository.save(data);
        return section;
    }

    @Override
    public List<Section> list() throws BusinessException {
        List<Section> sectionList = this.sectionRepository.findAll();
        return sectionList;
    }

    @Override
    public Section getById(long id) throws BusinessException {
        Optional<Section> optionalSection = this.sectionRepository.findById(id);
        if(optionalSection.isPresent()){
            Section section = optionalSection.get();
            return section;
        }
        else{
            throw new BusinessException(BusinessException.HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND);
        }
    }

    @Override
    public Section edit(Section data) throws BusinessException {
        Section section = getById(data.getId());
        section.edit(section);
        Section saveDSection = this.sectionRepository.save(section);
        this.sectionRepository.flush();
        return saveDSection;
    }
}
