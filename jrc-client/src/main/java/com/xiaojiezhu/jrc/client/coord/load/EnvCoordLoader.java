package com.xiaojiezhu.jrc.client.coord.load;

import com.xiaojiezhu.jrc.client.coord.CoordLoader;

/**
 * @author xiaojie.zhu
 */
public class EnvCoordLoader implements CoordLoader {


    @Override
    public String getGroup() {
        return System.getenv(GROUP);
    }

    @Override
    public String getUnit() {
        return System.getenv(UNIT);
    }

    @Override
    public String getVersion() {
        return System.getenv(VERSION);
    }

    @Override
    public String getProfile() {
        return System.getenv(PROFILE);
    }
}
