package com.xiaojiezhu.jrc.common.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * It is a memory cache,it is r thread safe method
 * @author xiaojie.zhu
 */
public class MemoryCache implements Cache {
    private final static Map<String,Object> DATA = new HashMap<>();
    private final static Map<String,Long> EXPIRE = new HashMap<>();
    @Override
    public synchronized boolean exist(String key) {
        Object o = DATA.get(key);
        if(o != null){
            return !isExpire(key);
        }else{
            return false;
        }
    }

    /**
     * the key if
     * @param key
     * @return if true , it is expire, if false, not  expire
     */
    private synchronized boolean isExpire(String key){
        Long expireTime = EXPIRE.get(key);
        if(expireTime == null){
            //if null,then it will not set expire time
            return false;
        }else{
            if(System.currentTimeMillis() > expireTime){
                //expire
                DATA.remove(key);
                EXPIRE.remove(key);
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public synchronized void delete(String key) {
        DATA.remove(key);
    }

    @Override
    public synchronized void expire(String key, long millisecond) {
        if(DATA.get(key) != null){
            EXPIRE.put(key,System.currentTimeMillis() + millisecond);
        }
    }

    @Override
    public synchronized void set(String key, Object value) {
        DATA.put(key,value);
    }

    @Override
    public synchronized <T> T getObject(String key) {
        if(exist(key)){
            return (T) DATA.get(key);
        }else{
            return null;
        }

    }

    @Override
    public synchronized String getString(String key) {
        if(exist(key)){
            return String.valueOf(DATA.get(key));
        }else{
            return null;
        }

    }

    @Override
    public synchronized Integer getInt(String key) {
        if(exist(key)){
            return (Integer) DATA.get(key);
        }else{
            return null;
        }

    }

    @Override
    public synchronized Long getLong(String key) {
        if(exist(key)){
            return (Long) DATA.get(key);
        }else{
            return null;
        }

    }

    @Override
    public synchronized Double getDouble(String key) {
        if(exist(key)){
            return (Double) DATA.get(key);
        }else {
            return null;
        }

    }

    @Override
    public synchronized Date getDate(String key) {
        if(exist(key)){
            return (Date) DATA.get(key);
        }else {
            return null;
        }

    }

    @Override
    public synchronized void flushDb() {
        DATA.clear();
        EXPIRE.clear();
    }


    public MemoryCache(){

    }

    public static Cache getInstance(){
        return Instance.cache;
    }


    private static class Instance{
        private static Cache cache = new MemoryCache();
    }
}
