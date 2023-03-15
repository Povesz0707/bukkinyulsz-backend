package com.example.bukkinyulszbackend.exception;

public class BusinessException extends Exception{
    public static final String HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND = "Item not found";
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
