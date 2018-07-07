package com.xiaojiezhu.jrc.client.coord;

/**
 * @author xiaojie.zhu
 */
public class NativeConfig {

    private String group;

    private String unit;

    private String version;

    private String profile;

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

    @Override
    public String toString() {
        return "NativeConfig{" +
                "group='" + group + '\'' +
                ", unit='" + unit + '\'' +
                ", version='" + version + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}
