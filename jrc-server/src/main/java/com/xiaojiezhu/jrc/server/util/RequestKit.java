package com.xiaojiezhu.jrc.server.util;

/**
 * @author xiaojie.zhu
 */
public class RequestKit {

    public static final ThreadLocal<String> REQUEST_CONTENT = new ThreadLocal<>();

    public static void set(String data){
        REQUEST_CONTENT.set(data);
    }

    public static String get(){
        return REQUEST_CONTENT.get();
    }
}
