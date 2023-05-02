package com.example.courier_tracking.adapter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
public class Store {

    private String name;
    private double lat;
    private double lng;
}
