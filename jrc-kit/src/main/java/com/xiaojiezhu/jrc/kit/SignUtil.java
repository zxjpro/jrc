package com.xiaojiezhu.jrc.kit;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author xiaojie.zhu
 */
public class SignUtil {

    public static String sign(TreeMap<String,Object> data,String key){
        String queryString = getQueryString(data);
        String sign = DigestUtils.sha256Hex(queryString + key);
        return sign;
    }
    public static String sign(TreeMap<String,Object> data,String key,String ... excludeFields){
        String queryString = getQueryString(data,excludeFields);
        String sign = DigestUtils.sha256Hex(queryString + key);
        return sign;
    }

    public static String getQueryString(TreeMap<String, Object> data) {
        return getQueryString(data,null);
    }

    public static String getQueryString(TreeMap<String, Object> data,String ... excludeFields) {
        Iterator<Map.Entry<String, Object>> iterator = data.entrySet().iterator();
        StringBuffer sb = new StringBuffer();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            if(excludeFields != null){
                for(String f : excludeFields){
                    if(f.equals(entry.getKey())){
                        break;
                    }
                }
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }else{
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return sb.substring(0,sb.length()-1);
    }


}
