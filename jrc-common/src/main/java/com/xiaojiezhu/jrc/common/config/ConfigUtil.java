package com.xiaojiezhu.jrc.common.config;

import com.xiaojiezhu.jrc.common.exception.UnSupportConfigException;
import com.xiaojiezhu.jrc.kit.CloseUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author xiaojie.zhu
 */
public class ConfigUtil {
    public final static String E = "=";

    public static ConfigType getConfigType(String content) throws UnSupportConfigException {
        boolean b = validProperties(content);
        if(b){
            return ConfigType.PROPERTIES;
        }else{
            boolean yaml = validYaml(content);
            if(yaml){
                return ConfigType.YAML;
            }else{
                throw new UnSupportConfigException("not support config format,we just support properties and yaml,please check it:\n" + content);
            }
        }
    }

    /**
     * valid it is a properties?
     * @param content
     * @return
     */
    public static boolean validProperties(String content){
        if(content == null){
            return false;
        }else{
            return validProperties(new ByteArrayInputStream(content.getBytes(Charset.forName("UTF-8"))));
        }
    }

    /**
     * valid this may be for a properties
     * @param inputStream
     * @return
     */
    public static boolean validProperties(InputStream inputStream){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null){
                if("".equals(line)){
                    break;
                }
                if(!line.startsWith("#")){
                    String[] split = line.split(E);
                    if(split.length < 2){
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            CloseUtil.close(reader);
        }

    }

    public static boolean validYaml(String content){
        if(content == null){
            return false;
        }else {
            return validYaml(new ByteArrayInputStream(content.getBytes(Charset.forName("UTF-8"))));
        }
    }
    public static boolean validYaml(InputStream inputStream){
        Yaml yaml = new Yaml();
        try {
            Map<String,Object> map = yaml.loadAs(inputStream, Map.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
