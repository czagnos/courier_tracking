package com.example.courier_tracking.service;

import com.courier.swaggergen.model.GetCourierPositionResponse;
import com.example.courier_tracking.model.GetCourierPositionRequestDto;

public interface CourierService {

    GetCourierPositionResponse logNearCourierForStore(GetCourierPositionRequestDto getCourierPositionRequestDto);
}
