package com.xiaojiezhu.jrc.common.config;

/**
 * @author xiaojie.zhu
 */
public class Config {

    public static ConfigType getConfigTypeByCode(int code){
        if(code == 0){
            return ConfigType.PROPERTIES;
        }else if(code == 1){
            return ConfigType.YAML;
        }else{
            throw new RuntimeException("code is not define:" + code);
        }
    }
    public static int getCodeByConfigType(ConfigType configType){
        if(configType == null){
            throw new NullPointerException("config type can not be null");
        }
        if(ConfigType.PROPERTIES.equals(configType)){
            return 0;
        }else if(ConfigType.YAML.equals(configType)){
                return 1;
        }else{
            throw new RuntimeException(configType + " not define");
        }
    }

    private ConfigType configType;
    private String content;

    public Config(ConfigType configType, String content) {
        this.configType = configType;
        this.content = content;
    }

    public Config() {
    }

    public ConfigType getConfigType() {
        return configType;
    }

    public void setConfigType(ConfigType configType) {
        this.configType = configType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Config{" +
                "configType=" + configType +
                ", content='" + content + '\'' +
                '}';
    }
}
