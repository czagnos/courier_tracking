package com.example.courier_tracking.service.impl;

import com.courier.swaggergen.model.GetCourierPositionResponse;
import com.example.courier_tracking.adapter.model.Store;
import com.example.courier_tracking.constant.Constant;
import com.example.courier_tracking.exception.NotFoundException;
import com.example.courier_tracking.model.GetCourierPositionRequestDto;
import com.example.courier_tracking.model.StoreLog;
import com.example.courier_tracking.model.converter.GetCourierPositionResponseConverter;
import com.example.courier_tracking.service.CourierService;
import com.example.courier_tracking.service.DistanceService;
import com.example.courier_tracking.service.LogService;
import com.example.courier_tracking.util.JsonUtil;
import com.example.courier_tracking.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    private LogService logService;

    @Autowired
    private GetCourierPositionResponseConverter getCourierPositionResponseConverter;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private DistanceService distanceService;

    @Override
    public GetCourierPositionResponse logNearCourierForStore(GetCourierPositionRequestDto getCourierPositionRequestDto) {

        final Store[] stores = JsonUtil.json2Java("/store/store.json", Store[].class);

        distanceService.calculateTotalDistance(getCourierPositionRequestDto);

        for (final Store store : stores) {
            StoreLog storeLog;

            final double distance = MathUtil.calculateDistance(getCourierPositionRequestDto.getLatitude(),
                getCourierPositionRequestDto.getLongitude(),
                store.getLat(),
                store.getLng());


            if (distance <= Constant.DISTANCE_LIMIT) {
                storeLog = logService.getLoggedStoreName(store.getName(), getCourierPositionRequestDto);

                if (storeLog.getTime().plusMinutes(1).isBefore(getCourierPositionRequestDto.getTime())
                    && Objects.equals(storeLog.getCourierId(), getCourierPositionRequestDto.getCourierId())) {

                    logService.clearStoreLog(storeLog.getCourierId());
                    storeLog = logService.getLoggedStoreName(store.getName(), getCourierPositionRequestDto);

                }

                return getCourierPositionResponseConverter.convert(storeLog);
            }
        }

        throw new NotFoundException("Courier is not near any store");
    }

}
