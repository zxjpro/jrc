package com.xiaojiezhu.jrc.common.cache.counter;


import com.xiaojiezhu.jrc.common.cache.Cache;
import com.xiaojiezhu.jrc.common.cache.counter.exception.CounterCanNotWriteException;

/**
 * @author zxj<br>
 * 时间 2017/11/29 16:04
 * 说明 基于缓存实现的计数器
 */
public class CacheCounter implements Counter{
    /**
     * 计数器的名字
     */
    private String name;
    private String cacheKey;
    private Cache cache;
    private Long expireTime;
    /**
     * 默认有读写权限，如果改为false,就只有读权限
     */
    private boolean writeRead = true;

    /**
     * 一个永久计数器
     * @param name 计数器名字
     * @param cache 缓存对象
     */
    public CacheCounter(String name, Cache cache) {
        this.name = name;
        this.cacheKey = getClass().getName() + "_" + name;
        this.cache = cache;
    }

    public CacheCounter(String name, Cache cache,boolean writeRead) {
        this(name,cache);
        this.writeRead = writeRead;
    }

    /**
     * 一个会过期的计数器
     * @param name 计数器名字
     * @param cache 缓存对象
     * @param expireTime 过期时间
     */
    public CacheCounter(String name, Cache cache,long expireTime) {
        this(name,cache);
        this.expireTime = expireTime;
    }

    @Override
    public void add(long x) {
        Long value = get();
        if(value == null){
            value = 0L;
        }
        value += x;
        set(value);
    }

    @Override
    public void subtract(long x) {
        Long value = get();
        if(value == null){
            value = 0L;
        }
        value -= x;
        set(value);
    }

    @Override
    public Long get() {
        Long value = cache.getLong(cacheKey);
        if(value == null){
            value = 0L;
        }
        return value;
    }

    private void set(long value){
        if(!writeRead){
            throw new CounterCanNotWriteException("计数器当前是只读状态，无法写入");
        }
        cache.set(cacheKey,value);
        if(expireTime != null){
            cache.expire(cacheKey,expireTime);
        }
    }


}
