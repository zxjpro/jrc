package com.xiaojiezhu.jrc.model;

import java.io.Serializable;

/**
 * @author xiaojie.zhu
 */
public class Dependency implements Serializable {
    private int versionId;

    private String group;

    private String unit;

    private String version;

    private String profile;

    private String versionDescription;


    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
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

    public String getVersionDescription() {
        return versionDescription;
    }

    public void setVersionDescription(String versionDescription) {
        this.versionDescription = versionDescription;
    }

    @Override
    public String toString() {
        return "Dependency{" +
                "versionId=" + versionId +
                ", group='" + group + '\'' +
                ", unit='" + unit + '\'' +
                ", version='" + version + '\'' +
                ", profile='" + profile + '\'' +
                ", versionDescription='" + versionDescription + '\'' +
                '}';
    }
}
