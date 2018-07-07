package com.xiaojiezhu.jrc.client.coord.load;

import com.xiaojiezhu.jrc.client.coord.CoordLoader;

import java.util.Properties;

/**
 * @author xiaojie.zhu
 */
public abstract class AbstractPropertiesLoader implements CoordLoader {

    /**
     * get properties,please cache this object
     * @return
     */
    protected abstract Properties getProperties();


    private String get(String key){
        Properties properties = getProperties();
        if(properties == null){
            return null;
        }else{
            String property = properties.getProperty(key);
            return property;
        }
    }

    @Override
    public String getGroup() {
        return get(GROUP);
    }

    @Override
    public String getUnit() {
        return get(UNIT);
    }

    @Override
    public String getVersion() {
        return get(VERSION);
    }

    @Override
    public String getProfile() {
        return get(PROFILE);
    }

}
