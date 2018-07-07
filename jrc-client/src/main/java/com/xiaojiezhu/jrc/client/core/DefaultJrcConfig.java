package com.xiaojiezhu.jrc.client.core;

import com.alibaba.fastjson.JSON;
import com.xiaojiezhu.jrc.client.JrcConfig;
import com.xiaojiezhu.jrc.client.core.store.ConfigStore;
import com.xiaojiezhu.jrc.client.core.store.SimpleConfigStore;
import com.xiaojiezhu.jrc.client.coord.CoordLoader;
import com.xiaojiezhu.jrc.client.coord.DefaultCoordLoader;
import com.xiaojiezhu.jrc.client.exception.CanNotCastDoubleException;
import com.xiaojiezhu.jrc.client.exception.CanNotCastIntegerException;
import com.xiaojiezhu.jrc.client.exception.CanNotCastLongException;

import java.util.Map;

/**
 * @author xiaojie.zhu
 */
public class DefaultJrcConfig implements JrcConfig {

    private ConfigStore configStore;
    private CoordLoader coordLoader = new DefaultCoordLoader();

    /**
     * load config file
     */
    public DefaultJrcConfig() {
        this.loadDefaultJrc();
    }

    /**
     * load the default config
     */
    private void loadDefaultJrc(){
        String group = coordLoader.getGroup();
        String unit = coordLoader.getUnit();
        String version = coordLoader.getVersion();
        String profile = coordLoader.getProfile();

        ConfigStore configStore = new SimpleConfigStore(group,unit,version,profile);
        this.setConfigStore(configStore);
    }


    public DefaultJrcConfig(String group,String unit,String version,String profile){
        ConfigStore configStore = new SimpleConfigStore(group,unit,version,profile);
        this.setConfigStore(configStore);
    }

    public void setConfigStore(ConfigStore configStore) {
        this.configStore = configStore;
    }

    @Override
    public String getConfig() {
        return JSON.toJSONString(getConfigMap());
    }

    @Override
    public Map<String, ?> getConfigMap() {
        return configStore.getConfig();
    }

    @Override
    public Object getObject(String key) {
        return getConfigMap().get(key);
    }

    @Override
    public Object getObject(String key, Object defaultValue) {
        Object value = this.getObject(key);
        return value == null ? defaultValue : value;
    }

    @Override
    public Integer getInt(String key) {
        Object value = this.getConfigMap().get(key);
        if(value == null){
            return null;
        }else{
            try {
                return Integer.parseInt(String.valueOf(value));
            } catch (NumberFormatException e) {
                throw new CanNotCastIntegerException(String.valueOf(value));
            }
        }
    }

    @Override
    public Integer getInt(String key, Integer defaultValue) {
        Integer value = this.getInt(key);
        return value == null ? defaultValue : value;
    }

    @Override
    public Long getLong(String key) {
        Object value = this.getConfigMap().get(key);
        if(value == null){
            return null;
        }else{
            try {
                return Long.parseLong(String.valueOf(value));
            } catch (NumberFormatException e) {
                throw new CanNotCastLongException(String.valueOf(value));
            }
        }
    }

    @Override
    public Long getLong(String key, Long defaultValue) {
        Long value = this.getLong(key);
        return value == null ? defaultValue : value;
    }

    @Override
    public String getString(String key) {
        Object value = this.getConfigMap().get(key);
        if(value == null){
            return null;
        }else{
            return value.toString();
        }
    }

    @Override
    public String getString(String key, String defaultValue) {
        String value = this.getString(key);
        return value == null ? defaultValue : value;
    }

    @Override
    public Double getDouble(String key) {
        Object value = this.getConfigMap().get(key);
        if(value == null){
            return null;
        }else{
            try {
                return Double.parseDouble(String.valueOf(value));
            } catch (NumberFormatException e) {
                throw new CanNotCastDoubleException(String.valueOf(value));
            }
        }
    }

    @Override
    public Double getDouble(String key, Double defaultValue) {
        Double value = this.getDouble(key);
        return value == null ? defaultValue : value;
    }

}
