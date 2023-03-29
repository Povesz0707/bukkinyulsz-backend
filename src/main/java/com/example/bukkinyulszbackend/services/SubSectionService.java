package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Marking;
import com.example.bukkinyulszbackend.model.SubSection;
import com.example.bukkinyulszbackend.model.SubSectionMarking;
import com.example.bukkinyulszbackend.repository.SubSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class SubSectionService implements BaseServiceInterface<SubSection>{
    private SubSectionRepository subSectionRepository;
    private SubSectionMarkingService subSectionMarkingService;
    private MarkingService markingService;

    @Autowired
    public void setSectionRepository(SubSectionRepository subSectionRepository) {
        this.subSectionRepository = subSectionRepository;
    }
    @Autowired
    private void setSubSectionMarkingService(SubSectionMarkingService subSectionMarkingService) {
        this.subSectionMarkingService = subSectionMarkingService;
    }
    @Autowired
    private void setMarkingService(MarkingService markingService) {
        this.markingService = markingService;
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
        listEdit(subSection, data);
        this.subSectionRepository.flush();
        return saveDSubSection;
    }
    private void listEdit(SubSection current, SubSection newData) throws BusinessException{
        Set<Long> addableMarkingIds = new HashSet<>();
        Set<Long> removableMarkingIds = new HashSet<>();
        Set<Long> currentMarkingIds = new HashSet<>();
        Set<Long> newDataMarkingIds = new HashSet<>();
        final List<SubSectionMarking> currentList = current.getMarkings();

        if(newData.getMarkings() != null && !newData.getMarkings().isEmpty()){
            for(SubSectionMarking subSectionMarking : newData.getMarkings()){
                newDataMarkingIds.add(subSectionMarking.getMarking().getId());
                System.out.println(subSectionMarking.getMarking().getId());
            }
        }
        if(currentList != null && !currentList.isEmpty()){
            for(SubSectionMarking subSectionMarking : currentList){
                currentMarkingIds.add(subSectionMarking.getMarking().getId());
            }
            for(Long markingId : newDataMarkingIds){
                if(!currentMarkingIds.contains(markingId)){
                    addableMarkingIds.add(markingId);
                }
            }
            for(Long markingId : currentMarkingIds){
                if(!newDataMarkingIds.contains(markingId)){
                    removableMarkingIds.add(markingId);
                }
            }
        }
        else{
            for(Long markingId : newDataMarkingIds){
                addableMarkingIds.add(markingId);
            }
        }
        for(Long markingId : addableMarkingIds){
            final SubSectionMarking subSectionMarking = new SubSectionMarking();
            final Marking marking = this.markingService.getById(markingId);
            subSectionMarking.setMarking(marking);
            subSectionMarking.setSubSection(current);
            this.subSectionMarkingService.add(subSectionMarking);
            System.out.println("addableMarkingId: "+markingId);
        }
        for(Long markingId : removableMarkingIds){
            for(SubSectionMarking subSectionMarking: currentList){
                if(subSectionMarking.getMarking().getId().equals(markingId)){
                    System.out.println(subSectionMarking.getId());
                    this.subSectionMarkingService.delete(subSectionMarking.getId());
                    System.out.println("removableMarkingId: "+markingId);
                }
            }
        }
    }

}
