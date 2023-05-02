package com.example.courier_tracking.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableCaching
public class CacheConfig {

    public static final String CACHE_USERS = "storeName";
    public static final String COURIERTOTALDISTANCE = "courierTotalDistance";

    @Autowired
    private CacheProperties cacheProperties;

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(CACHE_USERS, COURIERTOTALDISTANCE) {

            @Override
            protected Cache createConcurrentMapCache(final String name) {
                return new ConcurrentMapCache(name, CacheBuilder.newBuilder()
                    .build().asMap(), false);
            }
        };
    }

}
