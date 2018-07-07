package com.xiaojiezhu.jrc.web.server.configuration;

import com.xiaojiezhu.jrc.web.server.interceptor.FlushCacheInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author xiaojie.zhu
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    private FlushCacheInterceptor flushCacheInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(flushCacheInterceptor);
    }
}
