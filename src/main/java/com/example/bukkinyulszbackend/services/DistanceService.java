package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Distance;
import com.example.bukkinyulszbackend.repository.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DistanceService implements BaseServiceInterface<Distance>{
    private DistanceRepository distanceRepository;

    @Autowired
    public void setDistanceRepository(DistanceRepository distanceRepository) {
        this.distanceRepository = distanceRepository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        Distance distance = getById(id);
        this.distanceRepository.delete(distance);
        this.distanceRepository.flush();
        return true;
    }

    @Override
    public Distance add(Distance data) throws BusinessException {
        Distance saved = this.distanceRepository.save(data);
        return saved;
    }

    @Override
    public List<Distance> list() throws BusinessException {
        List<Distance> distanceList = this.distanceRepository.findAll();
        return distanceList;
    }

    @Override
    public Distance getById(long id) throws BusinessException {
        Optional<Distance> distanceOptional = this.distanceRepository.findById(id);
        if(distanceOptional.isPresent()){
            Distance distance = distanceOptional.get();
            return distance;
        }
        return null;
    }

    @Override
    public Distance edit(Distance data) throws BusinessException {
        Distance distance = getById(data.getId());
        distance.edit(data);
        Distance saved = this.distanceRepository.save(distance);
        this.distanceRepository.findAll();
        return saved;
    }
}
