package com.xiaojiezhu.jrc.web.server.service.impl;

import com.xiaojiezhu.jrc.client.core.load.ConfigLoader;
import com.xiaojiezhu.jrc.client.core.load.ConfigResult;
import com.xiaojiezhu.jrc.client.core.load.DefaultConfigLoader;
import com.xiaojiezhu.jrc.kit.exception.ConfigNotFoundException;
import com.xiaojiezhu.jrc.web.server.service.RemoteConfigService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xiaojie.zhu
 */
@Service
public class DefaultRemoteConfigService implements RemoteConfigService {

    @Override
    public Map<String, ?> getGlobalVersionConfig(String group, String unit, String version, String profile) throws Exception {
        ConfigLoader configLoader = new DefaultConfigLoader(group,unit,version,profile);
        ConfigResult configResult = configLoader.load();
        if(configResult == null){
            throw new ConfigNotFoundException("config not found");
        }
        return configResult.getData();
    }
}
