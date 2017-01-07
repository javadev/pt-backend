package com.osomapps.pt.config;

import com.google.common.cache.CacheBuilder;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@Configuration
@EnableCaching
@EnableJdbcHttpSession
class CachingConfig {

    @Bean
    CacheManager cacheManager() {
        final SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
            new ConcurrentMapCache("dictionaryData",
                CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build().asMap(), false),
            new ConcurrentMapCache("dictionaryAllData",
                CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build().asMap(), false)));
        return cacheManager;
   }
}
