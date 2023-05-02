package com.example.courier_tracking.exception;

import org.springframework.http.HttpStatus;

public class ErrorException extends ApiException{
    public ErrorException(HttpStatus httpStatus, String messageKey) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, messageKey);
    }
}
