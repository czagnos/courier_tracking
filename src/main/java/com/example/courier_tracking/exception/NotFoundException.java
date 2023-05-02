package com.example.courier_tracking.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    public NotFoundException(final String messageKey) {
        super(HttpStatus.NOT_FOUND, messageKey);
    }
}
