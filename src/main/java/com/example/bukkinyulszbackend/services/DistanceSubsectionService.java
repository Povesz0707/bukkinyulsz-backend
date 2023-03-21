package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Distance;
import com.example.bukkinyulszbackend.model.DistanceSubsection;
import com.example.bukkinyulszbackend.model.SubSection;
import com.example.bukkinyulszbackend.model.TourEventDistance;
import com.example.bukkinyulszbackend.repository.DistanceSubsectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DistanceSubsectionService implements BaseServiceInterface<DistanceSubsection>{
    private DistanceSubsectionRepository distanceSubsectionRepository;
    private DistanceService distanceService;
    private SubSectionService subSectionService;

    @Autowired
    public void setDistanceSubsectionRepository(DistanceSubsectionRepository distanceSubsectionRepository) {
        this.distanceSubsectionRepository = distanceSubsectionRepository;
    }

    @Autowired
    public void setDistanceService(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @Autowired
    public void setSubSectionService(SubSectionService subSectionService) {
        this.subSectionService = subSectionService;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        this.distanceSubsectionRepository.deleteById(id);
        this.distanceSubsectionRepository.flush();
        return true;
    }

    @Override
    public DistanceSubsection add(DistanceSubsection data) throws BusinessException {
        Distance distance = this.distanceService.getById(data.getDistance().getId());
        SubSection newSubSection = this.subSectionService.add(data.getSubSection());
        DistanceSubsection distanceSubsection = new DistanceSubsection();
        distanceSubsection.setDistance(distance);
        distanceSubsection.setSubSection(newSubSection);
        DistanceSubsection tourEventSubsection = this.distanceSubsectionRepository.save(distanceSubsection);
        this.distanceSubsectionRepository.flush();

        return tourEventSubsection;
    }

    @Override
    public List<DistanceSubsection> list() throws BusinessException {
        List<DistanceSubsection> tourEventSubsections = this.distanceSubsectionRepository.findAll();
        return tourEventSubsections;
    }

    @Override
    public DistanceSubsection getById(long id) throws BusinessException {
        Optional<DistanceSubsection> optionalTourEventSubsection = this.distanceSubsectionRepository.findById(id);
        if(optionalTourEventSubsection.isPresent()){
            DistanceSubsection tourEventSubsection = optionalTourEventSubsection.get();
            return tourEventSubsection;
        }
        else{
            throw new BusinessException(BusinessException.HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND);
        }
    }

    @Override
    public DistanceSubsection edit(DistanceSubsection data) throws BusinessException {
        DistanceSubsection tourEventSubsection = getById(data.getId());
        tourEventSubsection.edit(data);
        DistanceSubsection savedTourEventSubsection = this.distanceSubsectionRepository.save(tourEventSubsection);
        this.distanceSubsectionRepository.flush();
        return savedTourEventSubsection;
    }
    @Transactional(rollbackFor = BusinessException.class)
    public List<DistanceSubsection> findAllByDistance(final Distance distance)  throws BusinessException{
        Distance findDistance = this.distanceService.getById(distance.getId());
        List<DistanceSubsection> tourEventDistances = this.distanceSubsectionRepository.findAllByDistance(findDistance);
        return tourEventDistances;
    }

    @Transactional(rollbackFor = BusinessException.class)
    public List<DistanceSubsection> findAllBySubSection(final SubSection subSection)  throws BusinessException{
        SubSection findSubSection = this.subSectionService.getById(subSection.getId());
        List<DistanceSubsection> tourEventDistances = this.distanceSubsectionRepository.findAllBySubSection(findSubSection);
        return tourEventDistances;
    }


}
