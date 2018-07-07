package com.xiaojiezhu.jrc.client;

import com.xiaojiezhu.jrc.client.core.DefaultJrcConfig;

/**
 * @author xiaojie.zhu
 */
public class JrcConfigFactory {

    public static JrcConfig getJrcConfig(){
        return Instance.DEFAULT_JRC_CONFIG;
    }

    private static class Instance{
        private static final DefaultJrcConfig DEFAULT_JRC_CONFIG = new DefaultJrcConfig();
    }
}
