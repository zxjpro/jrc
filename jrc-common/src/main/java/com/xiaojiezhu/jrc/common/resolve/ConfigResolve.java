package com.xiaojiezhu.jrc.common.resolve;

import java.util.Map;

/**
 * @author xiaojie.zhu
 */
public interface ConfigResolve {

    /**
     * resolve the config
     * @return
     */
    Map<String,String> resolve();
}
