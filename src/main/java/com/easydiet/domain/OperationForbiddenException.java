package com.easydiet.domain;

public class OperationForbiddenException extends Exception {

    public OperationForbiddenException(String message) {
        super(message);
    }
}
