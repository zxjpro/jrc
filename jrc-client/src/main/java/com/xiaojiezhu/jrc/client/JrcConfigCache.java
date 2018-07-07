package com.xiaojiezhu.jrc.client;

import com.xiaojiezhu.jrc.client.exception.FlushCacheFailException;

/**
 * jrc config cache
 * @author xiaojie.zhu
 */
public interface JrcConfigCache {

    /**
     * flush the config cache,by default ,you did not need to call this if you not update config on database,
     * on the web admin ,update config where auto flush cache
     */
    void flushCache()throws FlushCacheFailException;
}
