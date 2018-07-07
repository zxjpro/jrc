package com.xiaojiezhu.jrc.client.spring;

import java.util.Map;

/**
 * inject the config
 * @author xiaojie.zhu
 */
public interface ConfigInject<T> {

    /**
     * inject config on everything framework and environment
     * @param t it is a operator object
     * @param configMap properties config
     */
    void inject(T t , Map<String,?> configMap);
}
