package com.xiaojiezhu.jrc.client.test;

import com.xiaojiezhu.jrc.client.JrcConfig;
import com.xiaojiezhu.jrc.client.JrcConfigFactory;

/**
 * @author xiaojie.zhu
 */
public class ConfigTest {

    public static void main(String[] args) {
        JrcConfig jrcConfig = JrcConfigFactory.getJrcConfig();

        String config = jrcConfig.getConfig();
    }
}
