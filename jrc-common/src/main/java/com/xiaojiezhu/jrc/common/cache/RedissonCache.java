package com.xiaojiezhu.jrc.common.cache;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.DoubleCodec;
import org.redisson.client.codec.IntegerCodec;
import org.redisson.client.codec.LongCodec;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.xiaojiezhu.jrc.common.cache.RedisCache.RedisConf.STATUS_SINGLE;

/**
 * @author zxj<br>
 * 时间 2017/11/29 14:45
 * 说明 ...
 */
public class RedissonCache extends RedisCache{
    private RedissonClient redissonClient;
    private RKeys redissonKeys;

    public RedissonCache(RedisConf redisConf) {
        super(redisConf);
        init();
    }
    private void init(){
        int status = redisConf.getStatus();
        Config config = new Config();
        if(status == STATUS_SINGLE){
            List<RedisConf.RedisServer> redisServers = redisConf.getRedisServers();
            if(redisServers == null || redisServers.size() == 0){
                throw new NullPointerException("redis服务器不能为空");
            }
            RedisConf.RedisServer redisServer = redisServers.get(0);
            //单点
            SingleServerConfig singleServerConfig = config.useSingleServer();
            singleServerConfig.setAddress("redis://" + redisServer.getHost() + ":" + redisServer.getPort());
            if(redisConf.getPassword() != null && redisConf.getPassword().length() > 0){
                singleServerConfig.setPassword(redisConf.getPassword());
            }
            singleServerConfig.setConnectionPoolSize(redisConf.getPoolSize());
            singleServerConfig.setDatabase(redisConf.getDataBase());

            this.redissonClient = Redisson.create(config);
            this.redissonKeys = redissonClient.getKeys();
        }else{
            throw new RuntimeException("暂时只写了单点redis的代码，集群代码还没写");
        }
    }

    @Override
    public boolean exist(String key) {
        keyNotNull(key);
        long l = redissonKeys.countExists(key);
        return l > 0;
    }

    @Override
    public void delete(String key) {
        keyNotNull(key);
        redissonKeys.delete(key);
    }

    @Override
    public void expire(String key, long millisecond) {
        keyNotNull(key);
        redissonKeys.expire(key,millisecond, TimeUnit.MILLISECONDS);
    }

    @Override
    public void set(String key, Object value) {
        keyNotNull(key);
        Class<?> valueClass = value.getClass();
        if(isIntClass(valueClass)){
            //int
            setInt(key, (Integer) value);
        }else if(isStringClass(valueClass)){
            //String
            setString(key, (String) value);
        }else if(isLongClass(valueClass)){
            //Long
            setLong(key, (Long) value);
        }else if(isDoubleClass(valueClass)){
            //double
            setDouble(key, (Double) value);
        }else if(isDateClass(valueClass)){
            //date
            setLong(key,((Date)value).getTime());
        }else{
            //other object
            setObject(key,value);
        }
    }

    @Override
    public <T> T getObject(String key) {
        keyNotNull(key);
        RBucket<Object> bucket = redissonClient.getBucket(key);
        return (T) bucket.get();
    }
    private void setObject(String key,Object value){
        keyNotNull(key);
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }


    @Override
    public String getString(String key) {
        keyNotNull(key);
        RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        return bucket.get();
    }
    private void setString(String key,String value){
        keyNotNull(key);
        RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        bucket.set(value);
    }


    @Override
    public Integer getInt(String key) {
        keyNotNull(key);
        RBucket<Integer> bucket = redissonClient.getBucket(key, IntegerCodec.INSTANCE);
        return bucket.get();
    }
    private void setInt(String key,Integer value){
        keyNotNull(key);
        RBucket<Integer> bucket = redissonClient.getBucket(key, IntegerCodec.INSTANCE);
        bucket.set(value);
    }


    @Override
    public Long getLong(String key) {
        keyNotNull(key);
        RBucket<Long> bucket = redissonClient.getBucket(key, LongCodec.INSTANCE);
        return bucket.get();
    }
    private void setLong(String key,Long value){
        keyNotNull(key);
        RBucket<Long> bucket = redissonClient.getBucket(key, LongCodec.INSTANCE);
        bucket.set(value);
    }

    @Override
    public Double getDouble(String key) {
        keyNotNull(key);
        RBucket<Double> bucket = redissonClient.getBucket(key, DoubleCodec.INSTANCE);
        return bucket.get();
    }
    private void setDouble(String key,Double value){
        keyNotNull(key);
        RBucket<Double> bucket = redissonClient.getBucket(key, DoubleCodec.INSTANCE);
        bucket.set(value);
    }


    @Override
    public Date getDate(String key) {
        keyNotNull(key);
        Long times = this.getLong(key);
        if(times == null){
            return null;
        }else{
            return new Date(times);
        }
    }

    @Override
    public void flushDb() {
        redissonKeys.flushdb();
    }


    private final static void keyNotNull(String key){
        if(key==null || key.length() == 0){
            throw new NullPointerException("存储的key不能为空");
        }
    }

    private final static boolean isStringClass(Class<?> valueClass){
        return valueClass == String.class;
    }
    private final static boolean isIntClass(Class<?> valueClass){
        return valueClass == int.class || valueClass == Integer.class;
    }
    private final static boolean isLongClass(Class<?> valueClass){
        return valueClass == long.class || valueClass == Long.class;
    }
    private final static boolean isDoubleClass(Class<?> valueClass){
        return valueClass == double.class || valueClass == Double.class;
    }

    private final static boolean isDateClass(Class<?> valueClass){
        return valueClass == Date.class;
    }
}
