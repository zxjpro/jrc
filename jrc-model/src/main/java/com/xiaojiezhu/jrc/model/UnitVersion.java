package com.xiaojiezhu.jrc.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaojie.zhu
 */
public class UnitVersion implements Serializable {
    private int unitId;

    private String group;

    private String unit;
    private String version;
    private String profile;
    private int versionId;
    private String unitDescription;
    private String versionDescription;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
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

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    public String getVersionDescription() {
        return versionDescription;
    }

    public void setVersionDescription(String versionDescription) {
        this.versionDescription = versionDescription;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UnitVersion{" +
                "unitId=" + unitId +
                ", group='" + group + '\'' +
                ", unit='" + unit + '\'' +
                ", version='" + version + '\'' +
                ", profile='" + profile + '\'' +
                ", versionId=" + versionId +
                ", unitDescription='" + unitDescription + '\'' +
                ", versionDescription='" + versionDescription + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
