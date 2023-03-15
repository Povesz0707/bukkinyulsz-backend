package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BaseServiceInterface<T> {
    @Transactional(rollbackFor = BusinessException.class)
    Boolean delete(final long id) throws BusinessException;

    @Transactional(rollbackFor = BusinessException.class)
    T add(T data) throws BusinessException;

    @Transactional(rollbackFor = BusinessException.class)
    List<T> list() throws BusinessException;

    @Transactional(rollbackFor = BusinessException.class)
    T getById(final long id)  throws BusinessException;

    @Transactional(rollbackFor = BusinessException.class)
    T edit(T data) throws BusinessException;

}
