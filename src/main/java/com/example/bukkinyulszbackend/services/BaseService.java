package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Marking;

import java.util.Optional;

public class BaseService<T> {
    public T isPresent(Optional<T> optional) throws BusinessException{
        if(optional.isPresent()){
            return optional.get();
        }
        throw new BusinessException(BusinessException.HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND);
    }
}
