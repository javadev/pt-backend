package com.osomapps.pt.config;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.cache.support.SimpleCacheManager;

public class CachingConfigTest {

    @Test
    public void cacheManager() {
        CachingConfig instance = new CachingConfig();
        assertTrue(instance.cacheManager() instanceof SimpleCacheManager);
    }
}
