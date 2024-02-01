package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Marking;
import com.example.bukkinyulszbackend.model.Reward;
import com.example.bukkinyulszbackend.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RewardService implements BaseServiceInterface<Reward>{
    private RewardRepository rewardRepository;
    @Autowired
    public void setRewardRepository(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        final Reward reward = this.getById(id);
        this.rewardRepository.delete(reward);
        this.rewardRepository.flush();
        return true;
    }

    @Override
    public Reward add(Reward data) throws BusinessException {
        final Reward reward = this.rewardRepository.save(data);
        this.rewardRepository.flush();
        return reward;
    }

    @Override
    public List<Reward> list() throws BusinessException {
        final List<Reward> rewardList = this.rewardRepository.findAll();
        return rewardList;
    }

    @Override
    public Reward getById(long id) throws BusinessException {
        final Optional<Reward> optionalReward = this.rewardRepository.findById(id);
        if(optionalReward.isPresent()){
            final Reward reward = optionalReward.get();
            return reward;
        }
        else{
            throw new BusinessException(BusinessException.HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND);
        }
    }

    @Override
    public Reward edit(Reward data) throws BusinessException {
        Reward reward = this.getById(data.getId());
        reward.edit(data);
        final Reward savedReward = this.rewardRepository.save(reward);
        return savedReward;
    }
}
