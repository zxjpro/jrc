package com.xiaojiezhu.jrc.client.core.load;

import com.xiaojiezhu.jrc.client.core.load.persist.ConfigPersist;
import com.xiaojiezhu.jrc.client.core.load.persist.DiskConfigPersist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * load config from remote or disk ,and persist config to disk
 * @author xiaojie.zhu
 */
public class DefaultConfigLoader extends ConfigLoader {
    public final static Logger LOG = LoggerFactory.getLogger(DefaultConfigLoader.class);
    private ConfigPersist configPersis;

    public DefaultConfigLoader(String group, String unit, String version, String profile) {
        super(group, unit, version, profile);
        this.setConfigPersis(DiskConfigPersist.getInstance());
    }

    public ConfigPersist getConfigPersis() {
        return configPersis;
    }

    public void setConfigPersis(ConfigPersist configPersis) {
        this.configPersis = configPersis;
    }

    @Override
    public ConfigResult load() throws Exception {
        ConfigResult configResult = getRemoteConfig();
        if(configResult != null){
            configPersis.persist(configResult);
        }else{
            LOG.debug("load config from disk");
            configResult = getDiskConfig();
        }
        return configResult;
    }

    protected ConfigResult getRemoteConfig(){
        ConfigLoader remoteLoader = new RemoteLoader(group,unit,version,profile);
        try {
            ConfigResult configResult = remoteLoader.load();
            return configResult;
        } catch (Exception e) {
            LOG.warn("load remote config fail : " + e.getMessage());
            return null;
        }
    }

    protected ConfigResult getDiskConfig(){
        try {
            ConfigLoader diskLoader = new DiskLoader(group,unit,version,profile);
            ConfigResult configResult = diskLoader.load();
            return configResult;
        } catch (Exception e) {
            LOG.warn("load disk config fail : " + e.getMessage());
            return null;
        }
    }
}
