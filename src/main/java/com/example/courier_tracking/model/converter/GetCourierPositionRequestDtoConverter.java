package com.example.courier_tracking.model.converter;

import com.courier.swaggergen.model.GetCourierPositionRequest;
import com.example.courier_tracking.model.GetCourierPositionRequestDto;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class GetCourierPositionRequestDtoConverter {

    public GetCourierPositionRequestDto convert(final GetCourierPositionRequest getCourierPositionRequest) {
        final GetCourierPositionRequestDto getCourierPositionRequestDto = new GetCourierPositionRequestDto();
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

        getCourierPositionRequestDto.setCourierId(getCourierPositionRequest.getCourierId());
        getCourierPositionRequestDto.setLatitude(getCourierPositionRequest.getLatitude());
        getCourierPositionRequestDto.setLongitude(getCourierPositionRequest.getLongtitude());
        getCourierPositionRequestDto.setTime(LocalTime.parse(getCourierPositionRequest.getTime(), dtf));

        return getCourierPositionRequestDto;
    }
}
