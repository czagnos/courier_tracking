package com.example.courier_tracking.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourierPosition {

    private double latitude;
    private double longitude;
    private String courierId;
    private double totalDistance;

}
