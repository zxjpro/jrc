package com.xiaojiezhu.jrc.client.core.load;

import java.util.Map;

/**
 * return the config result
 * @author xiaojie.zhu
 */
public class ConfigResult {

    /**
     * 0 not change
     * 1 has change
     * 2 from disk
     */
    private int code;

    private String group;
    private String unit;
    private String version;
    private String profile;

    /**
     * the config
     */
    private Map<String,?> data;

    public ConfigResult(int code, Map<String, ?> data) {
        this.code = code;
        this.data = data;
    }


    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public ConfigResult() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<String, ?> getData() {
        return data;
    }

    public void setData(Map<String, ?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ConfigResult{" +
                "code=" + code +
                ", group='" + group + '\'' +
                ", unit='" + unit + '\'' +
                ", version='" + version + '\'' +
                ", profile='" + profile + '\'' +
                ", data=" + data +
                '}';
    }
}
