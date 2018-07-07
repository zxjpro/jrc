package com.xiaojiezhu.jrc.common.cache;

import java.util.Date;

/**
 * @author zxj<br>
 * 时间 2017/11/29 14:29
 * 说明 ...
 */
public interface Cache {

    /**
     * 查询一个key是否还存在
     * @return 如果返回true，则说明存在
     */
    boolean exist(String key);

    /**
     * 删除一个key
     * @param key
     */
    void delete(String key);

    /**
     * 设置一个过期时间
     * @param key
     * @param millisecond 毫秒
     */
    void expire(String key, long millisecond);

    void set(String key, Object value);
    <T> T  getObject(String key);

    String getString(String key);
    Integer getInt(String key);
    Long getLong(String key);
    Double getDouble(String key);
    Date getDate(String key);


    /**
     * 清空数据库
     */
    void flushDb();



}
