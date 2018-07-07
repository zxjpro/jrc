package com.xiaojiezhu.jrc.client.spring;

import com.xiaojiezhu.jrc.kit.exception.ConfigNotFoundException;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;

/**
 * inject spring boot configuration
 * @author xiaojie.zhu
 */
public class SpringConfigInject implements ConfigInject<ConfigurableEnvironment> {
    /**
     * the name of config
     */
    private final static String CONFIG_NAME = "jrc";


    @Override
    public void inject(ConfigurableEnvironment configurableEnvironment, Map<String, ?> configMap) {
        if(configMap == null){
            throw new ConfigNotFoundException("can not fond the remote config from jrc,please check you config");
        }
        MapPropertySource mapPropertySource = new MapPropertySource(CONFIG_NAME, (Map<String, Object>) configMap);
        configurableEnvironment.getPropertySources().addFirst(mapPropertySource);
    }
}
