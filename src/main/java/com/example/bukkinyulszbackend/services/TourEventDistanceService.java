package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.SubSection;
import com.example.bukkinyulszbackend.model.TourEventDistance;
import com.example.bukkinyulszbackend.repository.TourEventDistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TourEventDistanceService implements BaseServiceInterface<TourEventDistance> {
    private TourEventDistanceRepository tourEventDistanceRepository;

    @Autowired
    public void setTourEventDistanceRepository(TourEventDistanceRepository tourEventDistanceRepository) {
        this.tourEventDistanceRepository = tourEventDistanceRepository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        this.tourEventDistanceRepository.deleteById(id);
        this.tourEventDistanceRepository.flush();
        return true;
    }

    @Override
    public TourEventDistance add(TourEventDistance data) throws BusinessException {
        TourEventDistance tourEventDistance = this.tourEventDistanceRepository.save(data);
        this.tourEventDistanceRepository.flush();
        return tourEventDistance;
    }

    @Override
    public List<TourEventDistance> list() throws BusinessException {
        List<TourEventDistance> tourEventDistanceList = this.tourEventDistanceRepository.findAll();
        return tourEventDistanceList;
    }

    @Override
    public TourEventDistance getById(long id) throws BusinessException {
        Optional<TourEventDistance> optionalTourEventDistance = this.tourEventDistanceRepository.findById(id);
        if(optionalTourEventDistance.isPresent()){
            TourEventDistance tourEventDistance = optionalTourEventDistance.get();
            return tourEventDistance;
        }
        else{
            throw new BusinessException(BusinessException.HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND);
        }
    }

    @Override
    public TourEventDistance edit(TourEventDistance data) throws BusinessException {
        TourEventDistance tourEventDistance = getById(data.getId());
        tourEventDistance.edit(tourEventDistance);
        TourEventDistance savedTourEventDistance = this.tourEventDistanceRepository.save(tourEventDistance);
        this.tourEventDistanceRepository.flush();
        return savedTourEventDistance;
    }
}
