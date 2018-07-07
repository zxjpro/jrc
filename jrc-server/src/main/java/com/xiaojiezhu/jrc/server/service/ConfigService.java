package com.xiaojiezhu.jrc.server.service;

import com.xiaojiezhu.jrc.kit.StateConfig;

import java.util.Map;

/**
 * @author xiaojie.zhu
 */
public interface ConfigService {


    /**
     * get the global with dependency config
     * @param group
     * @param unit
     * @param version
     * @param profile
     * @return
     */
    StateConfig getGlobalVersionConfig(String group, String unit, String version, String profile);
}
