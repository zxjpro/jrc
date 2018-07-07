package com.xiaojiezhu.jrc.client.coord;

/**
 * @author xiaojie.zhu
 */
public interface CoordLoader {
    String GROUP = "group";
    String UNIT = "unit";
    String VERSION = "version";
    String PROFILE = "profile";

    String getGroup();

    String getUnit();

    String getVersion();

    String getProfile();
}
