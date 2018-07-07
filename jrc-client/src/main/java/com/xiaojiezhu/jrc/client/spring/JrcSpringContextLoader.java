package com.xiaojiezhu.jrc.client.spring;

import com.xiaojiezhu.jrc.kit.JrcUtil;
import com.xiaojiezhu.jrc.kit.exception.ConfigNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * inject config in spring framework
 * @author xiaojie.zhu
 */
public class JrcSpringContextLoader implements ApplicationContextAware {
    public final static Logger LOG = LoggerFactory.getLogger(JrcSpringContextLoader.class);
    private static ApplicationContext context;

    private ConfigInject<ConfigurableEnvironment> configInject = new SpringConfigInject();
    private PropertiesCreator propertiesCreator = PropertiesCreatorFactory.getPropertiesCreator();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;

        boolean enableJrcConfig = JrcUtil.isEnableJrcConfig();
        if(!enableJrcConfig){
            LOG.warn("the jrc config is disable");
            return;
        }

        Map<String, ?> configMap = propertiesCreator.getConfigMap();
        if(configMap == null || configMap.size() == 0){
            throw new ConfigNotFoundException("can not fond the remote config from jrc,please check you config");
        }
        ConfigurableEnvironment environment = (ConfigurableEnvironment) applicationContext.getEnvironment();
        configInject.inject(environment,configMap);


    }

    public static ApplicationContext getContext() {
        return context;
    }
}
