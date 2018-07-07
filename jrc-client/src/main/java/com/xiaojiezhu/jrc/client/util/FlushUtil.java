package com.xiaojiezhu.jrc.client.util;

import com.xiaojiezhu.jrc.client.JrcConfigCache;
import com.xiaojiezhu.jrc.client.core.DefaultJrcConfigCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaojie.zhu
 */
public class FlushUtil {
    public static final Logger LOG = LoggerFactory.getLogger(FlushUtil.class);
    private static final JrcConfigCache JRC_CONFIG_CACHE = new DefaultJrcConfigCache();


    /**
     * flush the jrc-server config cache
     */
    public static void flushCache(){
        LOG.info("flush config cache");
        JRC_CONFIG_CACHE.flushCache();
    }
}
