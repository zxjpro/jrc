package com.xiaojiezhu.jrc.client.core.load;

import com.xiaojiezhu.jrc.client.util.PlatformUtil;
import com.xiaojiezhu.jrc.kit.CloseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @author xiaojie.zhu
 */
class DiskLoader extends ConfigLoader {
    public final static Logger LOG = LoggerFactory.getLogger(DiskLoader.class);

    public DiskLoader(String group, String unit, String version, String profile) {
        super(group, unit, version, profile);
    }

    @Override
    public ConfigResult load() throws Exception {
        String path = PlatformUtil.getDiskJrcPath() + group + File.separator + unit + File.separator + version + File.separator + profile + File.separator + "config.properties";
        File file = new File(path);
        if(!file.exists()){
            return null;
        }else{
            LOG.info("load config from disk  , group:" + group + " , unit:" + unit + " , version:" + version + " , profile:" + profile);
            InputStream inputStream = new FileInputStream(path);
            Properties properties = new Properties();
            properties.load(inputStream);
            CloseUtil.close(inputStream);
            Set<Object> keys = properties.keySet();
            Map<String,Object> data = new HashMap<>();
            for(Object key : keys){
                data.put(String.valueOf(key) , properties.get(key));
            }
            ConfigResult configResult = new ConfigResult(2, data);
            configResult.setGroup(group);
            configResult.setUnit(unit);
            configResult.setVersion(version);
            configResult.setProfile(profile);
            return configResult;
        }
    }
}
