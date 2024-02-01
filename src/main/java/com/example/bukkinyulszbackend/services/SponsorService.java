package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.News;
import com.example.bukkinyulszbackend.model.Sponsor;
import com.example.bukkinyulszbackend.repository.NewsRepository;
import com.example.bukkinyulszbackend.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SponsorService extends BaseService<Sponsor> implements BaseServiceInterface<Sponsor>{
    private SponsorRepository repository;
    @Autowired
    public void setRepository(SponsorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        this.repository.deleteById(id);
        this.repository.flush();
        return null;
    }

    @Override
    public Sponsor add(Sponsor data) throws BusinessException {
        Sponsor marking = this.repository.save(data);
        this.repository.flush();
        return marking;
    }

    @Override
    public List<Sponsor> list() throws BusinessException {
        List<Sponsor> all = this.repository.findAll();
        return all;
    }

    @Override
    public Sponsor getById(long id) throws BusinessException {
        Optional<Sponsor> optionalMarking = this.repository.findById(id);
        return isPresent(optionalMarking);
    }

    @Override
    public Sponsor edit(Sponsor data) throws BusinessException {
        Sponsor marking = getById(data.getId());
        marking.edit(data);
        Sponsor saved = this.repository.save(marking);
        this.repository.flush();
        return saved;
    }

    public List<Sponsor> findAllActive() throws BusinessException{
        List<Sponsor> list = this.repository.findAllByActiveIsTrue();
        return list;
    }
}
