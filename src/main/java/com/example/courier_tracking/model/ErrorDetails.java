package com.example.courier_tracking.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDetails {

    private String description;
    private HttpStatus status;
}
