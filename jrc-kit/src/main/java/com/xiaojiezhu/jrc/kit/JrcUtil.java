package com.xiaojiezhu.jrc.kit;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author xiaojie.zhu
 */
public class JrcUtil {

    public static String getConfigKey(String group, String unit, String version, String profile) {
        if(group == null || unit == null || version == null || profile == null){
            throw new NullPointerException("the pointer error , group:" + group + " , unit:" + unit + " , version:" + version + " , profile:" + profile);
        }
        String key = group + unit + version + profile;
        try {
            key = URLEncoder.encode(key,"UTF-8");
            return key;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new NullPointerException("key is null");
        }
    }


    /**
     * get is enable jrc config,if false,it will be disable
     * @return
     */
    public static boolean isEnableJrcConfig(){
        String status = System.getProperty(JrcConstant.DISABLE_NAME);
        if(status == null){
            status = System.getenv(JrcConstant.DISABLE_NAME);
        }
        if(Boolean.FALSE.toString().equals(status)){
            return false;
        }else{
            return true;
        }
    }



}
