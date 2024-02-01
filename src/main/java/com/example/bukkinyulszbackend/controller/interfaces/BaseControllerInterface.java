package com.example.bukkinyulszbackend.controller.interfaces;


import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseControllerInterface<T> {

    @GetMapping(AppConstant.URI_API_LIST)
    @Transactional(rollbackFor = BusinessException.class)
    ResponseEntity<List<T>> list()  throws BusinessException;

    @GetMapping(AppConstant.URI_API_GET+"/{id}")
    @Transactional(rollbackFor = BusinessException.class)
    ResponseEntity<T> getById(@PathVariable final long id)  throws BusinessException;

    @PostMapping(AppConstant.URI_API_ADD)
    @Transactional(rollbackFor = BusinessException.class)
    ResponseEntity<T> add(@RequestBody final T newData) throws BusinessException;

    @PutMapping(AppConstant.URI_API_EDIT)
    @Transactional(rollbackFor = BusinessException.class)
    ResponseEntity<T> edit(@RequestBody T data) throws BusinessException;

    @DeleteMapping(AppConstant.URI_API_DELETE+"/{id}")
    @Transactional(rollbackFor = BusinessException.class)
    ResponseEntity<Boolean> delete(@PathVariable final long id) throws BusinessException;

}
