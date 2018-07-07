package com.xiaojiezhu.jrc.client;

import com.xiaojiezhu.jrc.client.core.DefaultJrcConfig;
import com.xiaojiezhu.jrc.kit.CloseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author xiaojie.zhu
 */
public class MetaConfig {
    public final static String CONFIG_PATH = "/META-INF/jrc.properties";
    public final static Logger LOG = LoggerFactory.getLogger(DefaultJrcConfig.class);

    private static String serverUrl;
    private static String jrcKey;
    /**
     * how long request time
     */
    private static long requestConfigTime;

    static {
        InputStream inputStream = null;
        try {
            inputStream = DefaultJrcConfig.class.getResourceAsStream(CONFIG_PATH);
            Properties properties = new Properties();
            properties.load(inputStream);

            serverUrl = properties.getProperty("jrc.server.url");
            LOG.info("jrc-server address:" + serverUrl);
            jrcKey = properties.getProperty("jrc.key");
            requestConfigTime = Long.parseLong(properties.getProperty("jrc.requestConfigTime"));
        } catch (IOException e) {
            LOG.error("load jrc-client的" + CONFIG_PATH + "fail，program will exit");
            System.exit(0);
        } finally {
            CloseUtil.close(inputStream);
        }
    }


    public static String getServerUrl(){
        return serverUrl;
    }

    public static String getJrcKey(){
        return jrcKey;
    }

    public static long getRequestConfigTime() {
        return requestConfigTime;
    }
}
