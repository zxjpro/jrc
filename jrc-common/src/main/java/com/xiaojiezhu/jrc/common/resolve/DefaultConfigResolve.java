package com.xiaojiezhu.jrc.common.resolve;

import com.xiaojiezhu.jrc.common.exception.UnSupportConfigException;

import java.util.*;

/**
 * @author xiaojie.zhu
 */
public class DefaultConfigResolve implements ConfigResolve {
    private List<ConfigWeight> configs = new LinkedList<>();
    private ConfigLoader configLoader;

    private Map<String,String> data = new HashMap<>();

    public DefaultConfigResolve(){
        this.configLoader = new SimpleConfigLoader();
    }
    public DefaultConfigResolve(ConfigWeight configWeight){
        this();
        this.configs.add(configWeight);
    }


    @Override
    public Map<String,String> resolve() {
        Collections.sort(configs);
        for(ConfigWeight configWeight : configs){
            configLoader.loader(data,configWeight);
        }
        return data;
    }

    /**
     * It is not safe thread
     * @param weight
     * @param config
     * @throws UnSupportConfigException
     */
    public void addConfig(int weight,String config) throws UnSupportConfigException {
        this.configs.add(new ConfigWeight(weight,config));
    }

    /**
     * It is not safe thread
     * @param config
     * @throws UnSupportConfigException
     */
    public void addConfig(String config) throws UnSupportConfigException {
        this.configs.add(new ConfigWeight(data.size(),config));
    }

}
