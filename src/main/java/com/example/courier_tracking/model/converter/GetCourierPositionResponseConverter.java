package com.example.courier_tracking.model.converter;

import com.courier.swaggergen.model.GetCourierPositionResponse;
import com.example.courier_tracking.model.StoreLog;
import org.springframework.stereotype.Component;

@Component
public class GetCourierPositionResponseConverter {

    public GetCourierPositionResponse convert(final StoreLog storeLog) {
        final GetCourierPositionResponse getCourierPositionResponse = new GetCourierPositionResponse();

        getCourierPositionResponse.setCourierId(storeLog.getCourierId());
        getCourierPositionResponse.setTime(storeLog.getTime().toString());
        getCourierPositionResponse.setStoreName(storeLog.getStoreName());

        return getCourierPositionResponse;
    }
}
