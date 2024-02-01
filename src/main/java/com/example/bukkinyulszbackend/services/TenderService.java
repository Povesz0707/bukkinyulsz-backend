package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Sponsor;
import com.example.bukkinyulszbackend.model.Tender;
import com.example.bukkinyulszbackend.repository.SponsorRepository;
import com.example.bukkinyulszbackend.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TenderService extends BaseService<Tender> implements BaseServiceInterface<Tender>{
    private TenderRepository repository;
    @Autowired
    public void setRepository(TenderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        this.repository.deleteById(id);
        this.repository.flush();
        return null;
    }

    @Override
    public Tender add(Tender data) throws BusinessException {
        Tender marking = this.repository.save(data);
        this.repository.flush();
        return marking;
    }

    @Override
    public List<Tender> list() throws BusinessException {
        List<Tender> all = this.repository.findAll();
        return all;
    }

    @Override
    public Tender getById(long id) throws BusinessException {
        Optional<Tender> optionalMarking = this.repository.findById(id);
        return isPresent(optionalMarking);
    }

    @Override
    public Tender edit(Tender data) throws BusinessException {
        Tender marking = getById(data.getId());
        marking.edit(data);
        Tender saved = this.repository.save(marking);
        this.repository.flush();
        return saved;
    }

    public List<Tender> findAllActive() throws BusinessException{
        List<Tender> list = this.repository.findAllByActiveIsTrue();
        return list;
    }
}
