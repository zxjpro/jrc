package com.xiaojiezhu.jrc.common.resolve;

import java.util.Map;

/**
 * @author xiaojie.zhu
 */
public interface ConfigLoader {


    /**
     * loader the config to the map
     * @param configWeight
     */
    void loader(Map<String, String> data,ConfigWeight configWeight);
}
