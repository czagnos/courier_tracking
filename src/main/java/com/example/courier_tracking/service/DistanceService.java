package com.example.courier_tracking.service;

import com.courier.swaggergen.model.CourierTotalDistance;
import com.courier.swaggergen.model.GetCourierTotalDistanceResponse;
import com.example.courier_tracking.model.GetCourierPositionRequestDto;

import java.util.List;

public interface DistanceService {
    void calculateTotalDistance(GetCourierPositionRequestDto getCourierPositionRequestDto);

    GetCourierTotalDistanceResponse getTotalDistance(String courierId);

    List<CourierTotalDistance> getAllCourierTotalDistance();
}
