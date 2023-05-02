package com.example.courier_tracking.controller;

import com.courier.swaggergen.controller.CourierApi;
import com.courier.swaggergen.model.GetCourierPositionRequest;
import com.courier.swaggergen.model.GetCourierPositionResponse;
import com.courier.swaggergen.model.GetCourierTotalDistanceResponse;
import com.example.courier_tracking.model.GetCourierPositionRequestDto;
import com.example.courier_tracking.model.converter.GetCourierPositionRequestDtoConverter;
import com.example.courier_tracking.service.CourierService;
import com.example.courier_tracking.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourierController implements CourierApi {

    @Autowired
    private CourierService courierService;

    @Autowired
    private DistanceService distanceService;

    @Autowired
    private GetCourierPositionRequestDtoConverter getCourierPositionRequestDtoConverter;

    public ResponseEntity<GetCourierPositionResponse> getCourierPosition(final GetCourierPositionRequest getCourierPositionRequest) {
        final GetCourierPositionRequestDto getCourierPositionRequestDto = getCourierPositionRequestDtoConverter.convert(
            getCourierPositionRequest);

            final GetCourierPositionResponse response =
                courierService.logNearCourierForStore(getCourierPositionRequestDto);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<GetCourierTotalDistanceResponse> getCourierTotalDistance(
        final @PathVariable String courierId) {

        final GetCourierTotalDistanceResponse response = distanceService.getTotalDistance(courierId);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
