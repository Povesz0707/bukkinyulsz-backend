package com.example.bukkinyulszbackend.controller.interfaces;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ActiveStatusInterface<T> extends BaseControllerInterface<T>{
    @GetMapping(AppConstant.URI_API_ACTIVE)
    @Transactional(rollbackFor = BusinessException.class)
    ResponseEntity<List<T>> activeList()  throws BusinessException;
}
