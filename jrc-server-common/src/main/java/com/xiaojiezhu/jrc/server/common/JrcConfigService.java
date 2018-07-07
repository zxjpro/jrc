package com.xiaojiezhu.jrc.server.common;

import java.util.Map;

/**
 * @author xiaojie.zhu
 */
public interface JrcConfigService {

    /**
     * Get the global version config, with dependency version config
     * @param versionId
     * @return
     */
    Map<String, String> getGlobalVersionConfig(int versionId);


    /**
     * get the global version config with pointer
     * @param group
     * @param unit
     * @param version
     * @param profile
     * @return
     */
    Map<String,?> getGlobalVersionConfig(String group, String unit, String version, String profile);
}
