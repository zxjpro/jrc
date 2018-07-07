package com.xiaojiezhu.jrc.client;

import java.util.Map;

/**
 * @author xiaojie.zhu
 */
public interface JrcConfig {


    /**
     * get config and dependency global config
     * @return
     */
    String getConfig();

    /**
     * get the config map
     * @return
     */
    Map<String,?> getConfigMap();

    Object getObject(String key);
    Object getObject(String key,Object defaultValue);

    Integer getInt(String key);
    Integer getInt(String key,Integer defaultValue);

    Long getLong(String key);
    Long getLong(String key,Long defaultValue);

    String getString(String key);
    String getString(String key,String defaultValue);

    Double getDouble(String key);
    Double getDouble(String key,Double defaultValue);
}
