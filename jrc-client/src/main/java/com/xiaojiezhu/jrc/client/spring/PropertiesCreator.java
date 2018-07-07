package com.xiaojiezhu.jrc.client.spring;

import java.util.Map;
import java.util.Properties;

/**
 * get the config properties
 * @author xiaojie.zhu
 */
public interface PropertiesCreator {


    /**
     * get the config of properties
     * @return
     */
    Properties getProperties();

    /**
     * get the config of map
     * @return
     */
    Map<String,?> getConfigMap();
}
