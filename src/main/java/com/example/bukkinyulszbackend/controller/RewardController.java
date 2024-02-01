package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.controller.interfaces.BaseControllerInterface;
import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Reward;
import com.example.bukkinyulszbackend.services.RewardService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_REWARD)
public class RewardController extends BaseController<Reward> implements BaseControllerInterface<Reward> {
    private RewardService rewardService;

    @Autowired
    public void setRewardService(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @Override
    public ResponseEntity<List<Reward>> list() throws BusinessException {
        final List<Reward> rewardList = this.rewardService.list();
        return returnListResponse(rewardList);
    }

    @Override
    public ResponseEntity<Reward> getById(long id) throws BusinessException {
        final Reward reward = this.rewardService.getById(id);
        return returnSimpleResponse(reward);
    }

    @Override
    public ResponseEntity<Reward> add(Reward newData) throws BusinessException {
        final Reward reward = this.rewardService.add(newData);
        return returnSimpleResponse(reward);
    }

    @Override
    public ResponseEntity<Reward> edit(Reward data) throws BusinessException {
        final Reward reward = this.rewardService.edit(data);
        return returnSimpleResponse(reward);
    }

    @Override
    public ResponseEntity<Boolean> delete(long id) throws BusinessException {
        final Boolean result = this.rewardService.delete(id);
        return returnBooleanResponse(result);
    }
}
