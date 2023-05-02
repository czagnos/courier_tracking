package com.example.courier_tracking.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "cache")
public class CacheProperties {

    private int ttl;
    private boolean allowNullValues;
    private int maximumSize;
}