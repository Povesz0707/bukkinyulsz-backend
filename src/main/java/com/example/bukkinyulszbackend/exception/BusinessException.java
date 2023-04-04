package com.example.bukkinyulszbackend.exception;

public class BusinessException extends RuntimeException{
    public static final String HANDLED_EXCEPTION_TYPE_ITEM_NOT_FOUND = "Item not found";
    public static final String HANDLED_EXCEPTION_TYPE_LIST_IS_EMPTY_OR_NULL = "List is empty or NULL";
    public static final String HANDLED_EXCEPTION_TYPE_EMAIL_IS_ALREADY_USED = "Email is already used";
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
