package com.xiaojiezhu.jrc.web.server.component;

/**
 * this object is store which url on request need to flush cache
 * @author xiaojie.zhu
 */
public interface FlushCacheUrlStore {
    /**
     * add the flush cache url
     * @param url
     */
    void put(String url);

    /**
     * this url is exists this store
     * @param url
     * @return
     */
    boolean exists(String url);

}
