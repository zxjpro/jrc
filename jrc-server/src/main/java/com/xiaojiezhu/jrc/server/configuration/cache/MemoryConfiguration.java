package com.xiaojiezhu.jrc.server.configuration.cache;

import com.xiaojiezhu.jrc.common.cache.Cache;
import com.xiaojiezhu.jrc.common.cache.MemoryCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * init jvm memory cache
 * @author xiaojie.zhu
 */
@Configuration
@ConditionalOnProperty(prefix = "cache" , name = "type" , havingValue = "0")
public class MemoryConfiguration {

    @Bean
    public Cache cache(){
        return new MemoryCache();
    }
}
