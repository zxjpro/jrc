package com.xiaojiezhu.jrc.common.config;

/**
 * @author xiaojie.zhu
 */
public enum ConfigType {

    PROPERTIES("PROPERTIES"),
    YAML("YAML");

    private String value;

    ConfigType(String value) {
        this.value = value;
    }




    @Override
    public String toString() {
        return value;
    }
}
