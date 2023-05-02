package com.example.courier_tracking.service.impl;

import com.courier.swaggergen.model.CourierTotalDistance;
import com.courier.swaggergen.model.GetCourierTotalDistanceResponse;
import com.example.courier_tracking.exception.NotFoundException;
import com.example.courier_tracking.model.CourierPosition;
import com.example.courier_tracking.model.GetCourierPositionRequestDto;
import com.example.courier_tracking.service.DistanceService;
import com.example.courier_tracking.util.MathUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class DistanceServiceImpl implements DistanceService {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void calculateTotalDistance(final GetCourierPositionRequestDto getCourierPositionRequestDto) {
        if (Objects.isNull(Objects.requireNonNull(cacheManager.getCache("courierTotalDistance"))
            .get(getCourierPositionRequestDto.getCourierId()))) {

            final CourierPosition courierPosition = new CourierPosition();

            courierPosition.setCourierId(getCourierPositionRequestDto.getCourierId());
            courierPosition.setLatitude(getCourierPositionRequestDto.getLatitude());
            courierPosition.setLongitude(getCourierPositionRequestDto.getLongitude());
            courierPosition.setTotalDistance(0.0);

            Objects.requireNonNull(cacheManager.getCache("courierTotalDistance")).
                putIfAbsent(courierPosition.getCourierId(), courierPosition);
        } else {
            final CourierPosition  courierPosition =
                Objects.requireNonNull(cacheManager.getCache("courierTotalDistance"))
                .get(getCourierPositionRequestDto.getCourierId(), CourierPosition.class);

            final double newDistance = MathUtil.calculateDistance(
                getCourierPositionRequestDto.getLatitude(),
                getCourierPositionRequestDto.getLongitude(),
                courierPosition.getLatitude(),
                courierPosition.getLongitude());

            courierPosition.setTotalDistance(courierPosition.getTotalDistance() + newDistance);
            courierPosition.setLongitude(getCourierPositionRequestDto.getLongitude());
            courierPosition.setLatitude(getCourierPositionRequestDto.getLatitude());

            Objects.requireNonNull(cacheManager.getCache("courierTotalDistance")).put(courierPosition.getCourierId(), courierPosition);

        }
    }

    @Override
    public GetCourierTotalDistanceResponse getTotalDistance(String courierId) {
        final CourierPosition  courierPosition =
            Objects.requireNonNull(cacheManager.getCache("courierTotalDistance"))
                .get(courierId, CourierPosition.class);

        if (Objects.isNull(courierPosition) || courierPosition.getTotalDistance() == NumberUtils.DOUBLE_ZERO) {
            throw new NotFoundException("Courier has not been move yet");
        }

        final GetCourierTotalDistanceResponse getCourierTotalDistanceResponse = new GetCourierTotalDistanceResponse();

        getCourierTotalDistanceResponse.setTotalDistance(courierPosition.getTotalDistance());
        return getCourierTotalDistanceResponse;
    }

    @Override
    public List<CourierTotalDistance> getAllCourierTotalDistance() {
        final List<CourierTotalDistance> responseList = new ArrayList<>();
        final Map<String ,CourierPosition> courierPositionList = (Map<String, CourierPosition>)
            Objects.requireNonNull(cacheManager.getCache("courierTotalDistance")).getNativeCache();

        if (!courierPositionList.isEmpty()) {

            for (Map.Entry<String, CourierPosition> set : courierPositionList.entrySet()) {
                final CourierTotalDistance courierTotalDistance = new CourierTotalDistance();

                courierTotalDistance.setTotalDistance(set.getValue().getTotalDistance());
                courierTotalDistance.setCourierId(set.getKey());

                responseList.add(courierTotalDistance);
            }

            } else {
            throw new NotFoundException("Couriers has not been move yet");
        }

        return responseList;
    }

}
