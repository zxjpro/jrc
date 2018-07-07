package com.xiaojiezhu.jrc.web.server.service;

import java.util.Map;

/**
 * request the jrc-server
 * @author xiaojie.zhu
 */
public interface RemoteConfigService {


    /**
     * get the global config detail
     * @param group
     * @param unit
     * @param version
     * @param profile
     * @return
     */
    Map<String,?> getGlobalVersionConfig(String group, String unit, String version, String profile) throws Exception;

}
