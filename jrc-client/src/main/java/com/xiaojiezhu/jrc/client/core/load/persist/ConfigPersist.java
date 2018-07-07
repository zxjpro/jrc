package com.xiaojiezhu.jrc.client.core.load.persist;

import com.xiaojiezhu.jrc.client.core.load.ConfigResult;

/**
 * @author xiaojie.zhu
 */
public interface ConfigPersist {


    /**
     * save this config
     * @param configResult
     */
    void persist(ConfigResult configResult);
}
