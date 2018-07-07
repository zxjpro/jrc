package com.xiaojiezhu.jrc.kit;

import java.util.Map;

/**
 * @author xiaojie.zhu
 */
public class StateConfig {
    /**
     * true has change
     * false not change
     */
    private boolean hasChange;

    Map<String,?> config;


    public StateConfig(boolean hasChange, Map<String, ?> config) {
        this.hasChange = hasChange;
        this.config = config;
    }

    public StateConfig() {
    }



    public Map<String, ?> getConfig() {
        return config;
    }

    public void setConfig(Map<String, ?> config) {
        this.config = config;
    }

    public boolean isHasChange() {
        return hasChange;
    }

    public void setHasChange(boolean hasChange) {
        this.hasChange = hasChange;
    }

    @Override
    public String toString() {
        return "StateConfig{" +
                "hasChange=" + hasChange +
                ", config=" + config +
                '}';
    }
}
