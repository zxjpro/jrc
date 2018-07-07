package com.xiaojiezhu.jrc.web.server.component;

import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author xiaojie.zhu
 */
@Component
public class SimpleFlushCacheUrlStore implements FlushCacheUrlStore {
    private Set<String> urls = new LinkedHashSet<>();
    @Override
    public void put(String url) {
        urls.add(url);
    }

    @Override
    public boolean exists(String url) {
        return urls.contains(url);
    }


}
