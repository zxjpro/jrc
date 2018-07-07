package com.xiaojiezhu.jrc.client.coord.load;

import com.xiaojiezhu.jrc.client.coord.CoordLoader;

/**
 * @author xiaojie.zhu
 */
public class PropertiesCoordLoader implements CoordLoader {
    @Override
    public String getGroup() {
        return System.getProperty(GROUP);
    }

    @Override
    public String getUnit() {
        return System.getProperty(UNIT);
    }

    @Override
    public String getVersion() {
        return System.getProperty(VERSION);
    }

    @Override
    public String getProfile() {
        return System.getProperty(PROFILE);
    }
}
