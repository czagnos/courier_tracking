package com.example.courier_tracking.service.impl;

import com.example.courier_tracking.adapter.model.Store;
import com.example.courier_tracking.model.GetCourierPositionRequestDto;
import com.example.courier_tracking.model.StoreLog;
import com.example.courier_tracking.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Override
    @Cacheable(value = "storeName", key = "#getCourierPositionRequestDto.courierId")
    public StoreLog getLoggedStoreName(final String storeName,
        final GetCourierPositionRequestDto getCourierPositionRequestDto) {

        final StoreLog storeLog = new StoreLog();

        storeLog.setStoreName(storeName);
        storeLog.setTime(getCourierPositionRequestDto.getTime());
        storeLog.setCourierId(getCourierPositionRequestDto.getCourierId());

        log.info("Courier is near the store. Courier id:  {}, Store Name: {}, Time : {} ",
            getCourierPositionRequestDto.getCourierId(),
            storeName,
            getCourierPositionRequestDto.getTime());

        return storeLog;
    }

    @Override
    @CacheEvict(value = "storeName", key = "#storeName")
    public void clearStoreLog(final String storeName) {}
}
