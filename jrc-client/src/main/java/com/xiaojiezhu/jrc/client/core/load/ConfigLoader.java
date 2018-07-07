package com.xiaojiezhu.jrc.client.core.load;

/**
 * @author xiaojie.zhu
 */
public abstract class ConfigLoader {
    protected String group;
    protected String unit;
    protected String version;
    protected String profile;

    public ConfigLoader(String group, String unit, String version, String profile) {
        if(group == null || unit == null || version == null || profile == null){
            throw new NullPointerException("the coord is fail, group:" + group + " , unit:" + unit + " , version:" + version + " , profile:" + profile);
        }
        this.group = group;
        this.unit = unit;
        this.version = version;
        this.profile = profile;
    }

    /**
     * load config by coord
     * @return
     */
    public abstract ConfigResult load()throws Exception;



}
