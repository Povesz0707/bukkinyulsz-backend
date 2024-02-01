package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.BillingDetail;
import com.example.bukkinyulszbackend.model.Checkpoint;
import com.example.bukkinyulszbackend.model.CompetitorRegistration;
import com.example.bukkinyulszbackend.repository.BillingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BillingDetailService implements BaseServiceInterface<BillingDetail>{
    private BillingDetailRepository billingDetailRepository;

    @Autowired
    private void setBillingDetailRepository(BillingDetailRepository billingDetailRepository) {
        this.billingDetailRepository = billingDetailRepository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        return null;
    }

    @Override
    public BillingDetail add(BillingDetail data) throws BusinessException {
        BillingDetail item = this.billingDetailRepository.save(data);
        this.billingDetailRepository.flush();
        return item;
    }

    @Override
    public List<BillingDetail> list() throws BusinessException {
        return null;
    }

    @Override
    public BillingDetail getById(long id) throws BusinessException {
        return null;
    }

    @Override
    public BillingDetail edit(BillingDetail data) throws BusinessException {
        return null;
    }
}
