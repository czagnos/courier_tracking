package com.example.courier_tracking.service;

import com.courier.swaggergen.model.GetCourierPositionResponse;
import com.courier.swaggergen.model.GetCourierTotalDistanceResponse;
import com.example.courier_tracking.model.GetCourierPositionRequestDto;

public interface DistanceService {
    void calculateTotalDistance(GetCourierPositionRequestDto getCourierPositionRequestDto);

    GetCourierTotalDistanceResponse getTotalDistance(String courierId);
}
