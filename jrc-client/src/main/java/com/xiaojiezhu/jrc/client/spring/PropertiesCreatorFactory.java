package com.xiaojiezhu.jrc.client.spring;

/**
 * @author xiaojie.zhu
 */
public class PropertiesCreatorFactory {

    public static PropertiesCreator getPropertiesCreator(){
        return new RemotePropertiesCreator();
    }
}
