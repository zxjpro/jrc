package com.xiaojiezhu.jrc.client.spring;

import com.xiaojiezhu.jrc.client.JrcConfig;
import com.xiaojiezhu.jrc.client.JrcConfigFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @author xiaojie.zhu
 */
public class RemotePropertiesCreator implements PropertiesCreator {
    @Override
    public Properties getProperties() {
        Properties properties = new Properties();

        Map<String, ?> configMap = getConfigMap();
        if(configMap != null && configMap.size() > 0){
            Iterator<? extends Map.Entry<String, ?>> iterator = configMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, ?> entry = iterator.next();
                properties.setProperty(entry.getKey(),String.valueOf(entry.getValue()));
            }
        }
        return properties;
    }

    @Override
    public Map<String, ?> getConfigMap() {
        JrcConfig jrcConfig = JrcConfigFactory.getJrcConfig();
        Map<String, ?> configMap = jrcConfig.getConfigMap();
        return configMap;
    }
}
