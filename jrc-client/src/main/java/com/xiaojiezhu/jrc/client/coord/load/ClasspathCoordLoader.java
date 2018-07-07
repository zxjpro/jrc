package com.xiaojiezhu.jrc.client.coord.load;

import com.xiaojiezhu.jrc.kit.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author xiaojie.zhu
 */
public class ClasspathCoordLoader extends AbstractPropertiesLoader {
    public final static Logger LOG = LoggerFactory.getLogger(ClasspathCoordLoader.class);
    private final static String PATH = "META-INF/jrc.cfg";
    private Properties properties;

    public ClasspathCoordLoader(){
        this.init();
    }


    protected void init(){
        try {
            InputStream inputStream = ClasspathCoordLoader.class.getClassLoader().getResourceAsStream(PATH);
            this.properties = IOUtil.readProperties(inputStream);
        } catch (Exception e) {
            //e.printStackTrace();
            LOG.error("get classpath:/"+PATH+" fail");
        }
    }


    @Override
    protected Properties getProperties() {
        return this.properties;
    }
}
