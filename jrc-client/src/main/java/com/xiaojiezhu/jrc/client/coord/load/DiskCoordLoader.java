package com.xiaojiezhu.jrc.client.coord.load;

import com.xiaojiezhu.jrc.client.util.PlatformUtil;
import com.xiaojiezhu.jrc.kit.CloseUtil;
import com.xiaojiezhu.jrc.kit.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author xiaojie.zhu
 */
public class DiskCoordLoader extends AbstractPropertiesLoader {
    public final static Logger LOG = LoggerFactory.getLogger(DiskCoordLoader.class);
    private Properties properties;

    public DiskCoordLoader(){
        this.init();
    }



    protected void init() {
        try {
            File jrcDir = new File(PlatformUtil.getDiskJrcPath());
            if(jrcDir.exists()){
                File jrcJson = new File(PlatformUtil.getDiskJrcPath() + "/jrc.cfg");
                if(jrcJson.exists()){
                    FileInputStream inputStream = new FileInputStream(jrcJson);
                    this.properties = IOUtil.readProperties(inputStream);
                    CloseUtil.close(inputStream);
                }else{
                    LOG.warn("the jrc.json on " + PlatformUtil.getDiskJrcPath() + "not found");
                }

            }else{
                LOG.info("init jrc disk dir");
                boolean mkdirs = jrcDir.mkdirs();
                if(!mkdirs){
                    LOG.error("cant not make jrc dir,please check permission");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage() + "program will exit");
        }
    }

    @Override
    protected Properties getProperties() {
        return this.properties;
    }
}
