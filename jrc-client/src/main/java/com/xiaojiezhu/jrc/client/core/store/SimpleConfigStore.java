package com.xiaojiezhu.jrc.client.core.store;

import com.xiaojiezhu.jrc.client.MetaConfig;
import com.xiaojiezhu.jrc.client.core.load.ConfigLoader;
import com.xiaojiezhu.jrc.client.core.load.ConfigResult;
import com.xiaojiezhu.jrc.client.core.load.DefaultConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author xiaojie.zhu
 */
public class SimpleConfigStore implements ConfigStore {
    public final static Logger LOG = LoggerFactory.getLogger(SimpleConfigStore.class);
    private final static Charset UTF8 = Charset.forName("UTF-8");
    private String group;
    private String unit;
    private String version;
    private String profile;

    private Map<String,?> configData;

    @Override
    public Map<String, String> getConfig() {
        return (Map<String, String>) configData;
    }

    public SimpleConfigStore(String group, String unit, String version, String profile) {
        if(group == null || unit == null || version == null || profile == null){
            throw new NullPointerException("the pointer is fail,group:" + group + ",unit:" + unit + ",version:" + version + ",profile:" + profile);
        }
        this.group = group;
        this.unit = unit;
        this.version = version;
        this.profile = profile;
        LOG.info("init jrc config , group:" + group + " ,unit:" + unit + ", version:" + version + " ,profile:" + profile);
        this.init();
    }

    private void loadRemoteData() throws Exception {
        LOG.debug("request jrc-server...");
        ConfigLoader configLoader = new DefaultConfigLoader(group,unit,version,profile);
        ConfigResult configResult = configLoader.load();
        configData = configResult.getData();
        LOG.debug("jrc-server response:" + configResult);
    }



    private void init(){
        try {
            loadRemoteData();
        } catch (Exception e) {
            LOG.error("request jrc-server fail , reason:" + e.getMessage() + " jrc-server address:" + MetaConfig.getServerUrl());
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        loadRemoteData();
                    } catch (Exception e) {
                        //e.printStackTrace();
                        LOG.error("request jrc-server fail , reason:" + e.getMessage() + " jrc-server address:" + MetaConfig.getServerUrl());
                    }

                    try {
                        Thread.sleep(MetaConfig.getRequestConfigTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
