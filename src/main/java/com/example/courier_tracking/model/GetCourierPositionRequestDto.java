package com.example.courier_tracking.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class GetCourierPositionRequestDto {

    private LocalTime time;
    private String courierId;
    private double latitude;
    private double longitude;
}
