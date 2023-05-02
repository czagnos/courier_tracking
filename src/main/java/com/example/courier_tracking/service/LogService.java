package com.example.courier_tracking.service;

import com.example.courier_tracking.model.GetCourierPositionRequestDto;
import com.example.courier_tracking.model.StoreLog;

public interface LogService {
    StoreLog getLoggedStoreName(String name, GetCourierPositionRequestDto getCourierPositionRequestDto);

    void clearStoreLog(String name);
}
