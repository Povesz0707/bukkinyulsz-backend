package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.GaleryImage;
import com.example.bukkinyulszbackend.model.Marking;
import com.example.bukkinyulszbackend.model.News;
import com.example.bukkinyulszbackend.repository.GaleryImageRepository;
import com.example.bukkinyulszbackend.repository.MarkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GaleryImageService extends BaseService<GaleryImage> implements BaseServiceInterface<GaleryImage>{
    private GaleryImageRepository repository;

    @Autowired
    public void setRepository(GaleryImageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        this.repository.deleteById(id);
        this.repository.flush();
        return null;
    }

    @Override
    public GaleryImage add(GaleryImage data) throws BusinessException {
        GaleryImage marking = this.repository.save(data);
        this.repository.flush();
        return marking;
    }

    @Override
    public List<GaleryImage> list() throws BusinessException {
        List<GaleryImage> markingList = this.repository.findAll();
        return markingList;
    }

    @Override
    public GaleryImage getById(long id) throws BusinessException {
        Optional<GaleryImage> optionalMarking = this.repository.findById(id);
        return isPresent(optionalMarking);
    }

    @Override
    public GaleryImage edit(GaleryImage data) throws BusinessException {
        GaleryImage marking = getById(data.getId());
        marking.edit(data);
        GaleryImage saved = this.repository.save(marking);
        this.repository.flush();
        return saved;
    }

    public List<GaleryImage> findAllActiveDesc() throws BusinessException{
        List<GaleryImage> list = this.repository.findAllByActiveIsTrueOrderByInsertedDesc();
        return list;
    }

}
