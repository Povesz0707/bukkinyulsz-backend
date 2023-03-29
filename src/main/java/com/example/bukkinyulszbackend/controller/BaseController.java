package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.exception.BusinessException;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BaseController<T> {
    public ResponseEntity<T> returnSimpleResponse(T item) throws BusinessException {
        return ResponseEntity.ok(item);
    }
    public ResponseEntity<List<T>> returnListResponse(List<T> items) throws BusinessException{
        return ResponseEntity.ok(items);
    }
    public ResponseEntity<Boolean> returnBooleanResponse(Boolean item) throws BusinessException{
        return ResponseEntity.ok(item);
    }

}
