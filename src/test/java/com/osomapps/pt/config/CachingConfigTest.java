package com.osomapps.pt.config;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.cache.support.SimpleCacheManager;

public class CachingConfigTest {

    @Test
    public void cacheManager() {
        CachingConfig instance = new CachingConfig();
        assertTrue(instance.cacheManager() instanceof SimpleCacheManager);
    }
    
}
