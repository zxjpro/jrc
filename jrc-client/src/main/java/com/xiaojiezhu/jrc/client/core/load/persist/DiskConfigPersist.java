package com.xiaojiezhu.jrc.client.core.load.persist;

import com.xiaojiezhu.jrc.client.core.load.ConfigResult;
import com.xiaojiezhu.jrc.client.util.PlatformUtil;
import com.xiaojiezhu.jrc.kit.CloseUtil;
import com.xiaojiezhu.jrc.kit.JrcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * persist config data to the disk
 * @author xiaojie.zhu
 */
public class DiskConfigPersist implements ConfigPersist {
    public final static Logger LOG = LoggerFactory.getLogger(DiskConfigPersist.class);

    /**
     * count the persist, if the config is first enter,whatever it will be save in disk
     */
    private Map<String,Integer> COUNTER = new HashMap<>();


    @Override
    public void persist(ConfigResult configResult) {
        if(configResult == null){
            LOG.error("the config is null,can not persist");
            return;
        }else{
            String key = JrcUtil.getConfigKey(configResult.getGroup(), configResult.getUnit(), configResult.getVersion(), configResult.getProfile());
            Integer count = COUNTER.get(key);
            if(count == null){
                persistDisk(configResult);
            }else{
                if(configResult.getCode() == 1){
                    //remote has chage
                    persistDisk(configResult);
                }
            }
        }
    }

    private void persistDisk(ConfigResult configResult){
        LOG.info("the config is persist to disk, group:" + configResult.getGroup() + " , unit:" + configResult.getUnit() + " , version:" + configResult.getVersion() + " , profile:" + configResult.getProfile());
        String path = PlatformUtil.getCoordDiskPath(configResult.getGroup(), configResult.getUnit(), configResult.getVersion(), configResult.getProfile());
        File file = new File(path);
        File dir = file.getParentFile();
        if(!dir.exists()){
            dir.mkdirs();
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file,false));
            //Iterator<Map.Entry<String, ?>> iterator = configResult.getData().entrySet().iterator();
            Iterator<? extends Map.Entry<String, ?>> iterator = configResult.getData().entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, ?> entry = iterator.next();
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("the persist file is fail , " + e.getMessage());
        } finally {
            CloseUtil.close(writer);
        }

    }

    private DiskConfigPersist(){}

    public static DiskConfigPersist getInstance(){
        return Instance.ins;
    }
    private static class Instance{
        private final static DiskConfigPersist ins = new DiskConfigPersist();
    }
}
