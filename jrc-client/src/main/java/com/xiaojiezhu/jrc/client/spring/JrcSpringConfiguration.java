package com.xiaojiezhu.jrc.client.spring;

import com.xiaojiezhu.jrc.client.JrcConfig;
import com.xiaojiezhu.jrc.client.JrcConfigFactory;
import com.xiaojiezhu.jrc.kit.JrcConstant;
import com.xiaojiezhu.jrc.kit.JrcUtil;
import com.xiaojiezhu.jrc.kit.exception.ConfigNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xiaojie.zhu
 */
@Component
public class JrcSpringConfiguration implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    public final static Logger LOG = LoggerFactory.getLogger(JrcSpringConfiguration.class);

    private ConfigInject<ConfigurableEnvironment> configInject = new SpringConfigInject();
    private PropertiesCreator propertiesCreator = PropertiesCreatorFactory.getPropertiesCreator();

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        boolean enableJrcConfig = JrcUtil.isEnableJrcConfig();
        if(!enableJrcConfig){
            LOG.warn("the jrc config is disable");
            return;
        }

        Map<String, ?> configMap = propertiesCreator.getConfigMap();
        if(configMap == null || configMap.size() == 0){
            throw new ConfigNotFoundException("can not fond the remote config from jrc,please check you config");
        }

        configInject.inject(event.getEnvironment(),configMap);


    }
}
