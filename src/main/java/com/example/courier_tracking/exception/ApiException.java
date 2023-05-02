package com.example.courier_tracking.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String description;
}
