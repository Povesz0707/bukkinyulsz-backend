package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.TourEvent;
import com.example.bukkinyulszbackend.repository.TourEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TourEventService implements BaseServiceInterface<TourEvent>{
    private TourEventRepository tourEventRepository;

    @Autowired
    public void setTourEventRepository(TourEventRepository tourEventRepository) {
        this.tourEventRepository = tourEventRepository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        this.tourEventRepository.deleteById(id);
        this.tourEventRepository.flush();
        return true;
    }

    @Override
    public TourEvent add(TourEvent data) throws BusinessException {
        TourEvent tourEvent = this.tourEventRepository.save(data);
        this.tourEventRepository.flush();
        return tourEvent;
    }

    @Override
    public List<TourEvent> list() throws BusinessException {
        List<TourEvent> tourEventList = this.tourEventRepository.findAll();
        return tourEventList;
    }

    @Override
    public TourEvent getById(long id) throws BusinessException {
        Optional<TourEvent> optionalTourEvent = this.tourEventRepository.findById(id);
        if(optionalTourEvent.isPresent()){
            TourEvent tourEvent = optionalTourEvent.get();
            return tourEvent;
        }
        else{
            throw new BusinessException(BusinessException.HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND);
        }
    }

    @Override
    public TourEvent edit(TourEvent data) throws BusinessException {
        TourEvent tourEvent = getById(data.getId());
        tourEvent.edit(data);
        System.out.println(tourEvent.getApplicationFrom());
        TourEvent savedTourEvent = this.tourEventRepository.save(tourEvent);
        this.tourEventRepository.flush();
        return savedTourEvent;
    }

    @Transactional(rollbackFor = BusinessException.class)
    public TourEvent getLatestActive()  throws BusinessException{
        TourEvent tourEvent = this.tourEventRepository.findFirstByActiveIsTrueOrderByDateOfEventDesc();
        if(tourEvent != null){
            return tourEvent;

        }
        return null;
    }

    @Transactional(rollbackFor = BusinessException.class)
    public Boolean updateActiveStatus(List<TourEvent> tourEventList, Boolean activeStatus)  throws BusinessException{
        if(tourEventList != null && !tourEventList.isEmpty()){
            for(TourEvent tourEvent : tourEventList){
                tourEvent.setActive(activeStatus);
                this.edit(tourEvent);
            }
            return true;
        }
        else{
            throw new BusinessException(BusinessException.HANDLED_EXCEPTION_TYPE_LIST_IS_EMPTY_OR_NULL);
        }
    }
}
