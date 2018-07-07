package com.xiaojiezhu.jrc.test;

import com.xiaojiezhu.jrc.client.JrcConfig;
import com.xiaojiezhu.jrc.client.JrcConfigFactory;

import java.util.Map;

/**
 * @author xiaojie.zhu
 */
public class JrcTest {

    public static void main(String[] args) throws InterruptedException {
        JrcConfig jrcConfig = JrcConfigFactory.getJrcConfig();
        System.out.println(jrcConfig.getInt("sms.cmport"));
        Map<String, ?> configMap = jrcConfig.getConfigMap();
        System.out.println(configMap);
    }

}
