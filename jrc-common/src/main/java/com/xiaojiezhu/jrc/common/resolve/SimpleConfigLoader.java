package com.xiaojiezhu.jrc.common.resolve;

import com.xiaojiezhu.jrc.common.config.ConfigType;
import com.xiaojiezhu.jrc.kit.CloseUtil;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.InputStreamResource;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author xiaojie.zhu
 */
public class SimpleConfigLoader implements ConfigLoader {

    @Override
    public void loader(Map<String, String> data, ConfigWeight configWeight) {
        InputStream in = new ByteArrayInputStream(configWeight.getContent().getBytes(Charset.forName("UTF-8")));
        Map<String, String> tmp;
        if(ConfigType.YAML.equals(configWeight.getConfigType())){
            tmp = loadYaml(in);
            loader(data,tmp);
        }else if(ConfigType.PROPERTIES.equals(configWeight.getConfigType())){
            tmp = loadProperties(in);
            loader(data,tmp);
        }else{
            throw new RuntimeException("load error");
        }
    }

    private void loader(Map<String,String> data,Map<String,String> tmp){
        if(tmp != null){
            Iterator<Map.Entry<String, String>> iterator = tmp.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> entry = iterator.next();
                if(data.get(entry.getKey()) == null){
                    data.put(entry.getKey(),entry.getValue());
                }
            }
        }
    }


    private Map<String,String> loadYaml(InputStream in){
        YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
        bean.setResources(new InputStreamResource(in));
        CloseUtil.close(in);
        Properties properties = bean.getObject();
        Set<Object> keys = properties.keySet();
        Map<String,String> data = new HashMap<>();
        for(Object key : keys){
            data.put(String.valueOf(key),String.valueOf(properties.get(key)));
        }
        return data;
    }

    private Map<String,String> loadProperties(InputStream inputStream){
        Map<String,String> data = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null){
                if("".equals(line) || line.startsWith("#")){
                    break;
                }
                String[] split = line.split("=");
                StringBuffer sb = new StringBuffer();
                for(int i = 1 ; i < split.length ; i ++){
                    sb.append(split[i]);
                }
                data.put(split[0],sb.toString());
            }

            return data;
        } catch (IOException e) {
            e.printStackTrace();
            throw new NullPointerException("the null config map");
        }
    }


}
