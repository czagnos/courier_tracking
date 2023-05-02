package com.example.courier_tracking.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class StoreLog {

    private LocalTime time;
    private String storeName;
    private String courierId;

}
