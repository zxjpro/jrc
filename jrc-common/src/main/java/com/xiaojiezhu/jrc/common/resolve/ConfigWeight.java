package com.xiaojiezhu.jrc.common.resolve;

import com.xiaojiezhu.jrc.common.exception.UnSupportConfigException;
import com.xiaojiezhu.jrc.common.config.ConfigType;
import com.xiaojiezhu.jrc.common.config.ConfigUtil;

import java.util.Comparator;

/**
 * config weight
 * @author xiaojie.zhu
 */
public class ConfigWeight implements Comparable<ConfigWeight>{

    private int weight;

    private String content;

    private ConfigType configType;

    public ConfigWeight() {
    }

    public ConfigWeight(String content) throws UnSupportConfigException {
        this(0,content);
    }

    public ConfigWeight(int weight, String content) throws UnSupportConfigException {
        this(weight,content,ConfigUtil.getConfigType(content));
    }

    public ConfigWeight(int weight, String content, ConfigType configType) {
        this.weight = weight;
        this.content = content;
        this.configType = configType;
    }

    public int getWeight() {
        return weight;
    }

    public ConfigType getConfigType() {
        return configType;
    }

    public void setConfigType(ConfigType configType) {
        this.configType = configType;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ConfigWeight{" +
                "weight=" + weight +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public int compareTo(ConfigWeight o) {
        return this.weight - o.weight;
    }
}
