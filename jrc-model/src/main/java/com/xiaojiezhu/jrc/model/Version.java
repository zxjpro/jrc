package com.xiaojiezhu.jrc.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaojie.zhu
 */
public class Version implements Serializable {

    private int id;
    private int unitId;

    private boolean enable;

    @NotNull(message = "group cant not be null")
    private String group;
    @NotNull(message = "unit cant not be null")
    private String unit;

    @NotNull(message = "version cant not be null")
    private String version;

    @NotNull(message = "profile cant not be null")
    private String profile;

    @NotNull(message = "description cant not be null")
    private String description;

    /**
     * 0 properties
     * 1 yaml
     */
    private int configType;
    private String content;

    /**
     * request number
     */
    private int requestNumber;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public int getId() {
        return id;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
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

    public int getConfigType() {
        return configType;
    }

    public void setConfigType(int configType) {
        this.configType = configType;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Version{" +
                "id=" + id +
                ", unitId=" + unitId +
                ", enable=" + enable +
                ", group='" + group + '\'' +
                ", unit='" + unit + '\'' +
                ", version='" + version + '\'' +
                ", profile='" + profile + '\'' +
                ", description='" + description + '\'' +
                ", configType=" + configType +
                ", content='" + content + '\'' +
                ", requestNumber=" + requestNumber +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
