package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Distance;
import com.example.bukkinyulszbackend.model.SubSection;
import com.example.bukkinyulszbackend.model.TourEvent;
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
    private DistanceService distanceService;
    private TourEventService tourEventService;

    @Autowired
    public void setDistanceService(DistanceService distanceService) {
        this.distanceService = distanceService;
    }
    @Autowired
    public void setTourEventService(TourEventService tourEventService) {
        this.tourEventService = tourEventService;
    }

    @Autowired
    public void setTourEventDistanceRepository(TourEventDistanceRepository tourEventDistanceRepository) {
        this.tourEventDistanceRepository = tourEventDistanceRepository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        TourEventDistance tourEventDistance = getById(id);
        Distance distance = this.distanceService.getById(tourEventDistance.getDistance().getId());
        this.tourEventDistanceRepository.deleteById(tourEventDistance.getId());
        this.distanceService.delete(distance.getId());
        this.tourEventDistanceRepository.flush();
        return true;
    }

    @Override
    public TourEventDistance add(TourEventDistance data) throws BusinessException {
        TourEvent tourEvent = this.tourEventService.getById(data.getTourEvent().getId());
        Distance distance = this.distanceService.add(data.getDistance());
        TourEventDistance newTourEventDistance = new TourEventDistance();
        newTourEventDistance.setDistance(distance);
        newTourEventDistance.setTourEvent(tourEvent);

        TourEventDistance tourEventDistance = this.tourEventDistanceRepository.save(newTourEventDistance);
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
        tourEventDistance.edit(data);
        TourEventDistance savedTourEventDistance = this.tourEventDistanceRepository.save(tourEventDistance);
        this.tourEventDistanceRepository.flush();
        return savedTourEventDistance;
    }

    @Transactional(rollbackFor = BusinessException.class)
    public List<TourEventDistance> findAllByDistance(final Distance distance)  throws BusinessException{
        Distance findDistance = this.distanceService.getById(distance.getId());
        List<TourEventDistance> tourEventDistances = this.tourEventDistanceRepository.findAllByDistance(findDistance);
        return tourEventDistances;
    }
    @Transactional(rollbackFor = BusinessException.class)
    public List<TourEventDistance> findAllByTourEvent(final TourEvent tourEvent)  throws BusinessException{
        TourEvent findDistance = this.tourEventService.getById(tourEvent.getId());
        List<TourEventDistance> tourEventDistances = this.tourEventDistanceRepository.findAllByTourEvent(findDistance);
        return tourEventDistances;
    }
}
