package com.xiaojiezhu.jrc.server.configuration.cache;

import com.xiaojiezhu.jrc.common.cache.Cache;
import com.xiaojiezhu.jrc.common.cache.RedisCache;
import com.xiaojiezhu.jrc.common.cache.RedissonCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * init redis cache
 * @author xiaojie.zhu
 */
@Configuration
@ConfigurationProperties(prefix = "cache.redis")
@ConditionalOnProperty(prefix = "cache" , name = "type" , havingValue = "1")
public class RedisConfiguration {

    private String host;

    private int port;

    private String password;

    private int db;

    @Bean
    public Cache cache(){
        RedisCache.RedisConf redisConf = new RedisCache.RedisConf().singleServer();
        if(password != null){
            redisConf.setPassword(password);
        }
        redisConf.setPoolSize(10);
        redisConf.setDataBase(db);
        redisConf.addServer(host,port);
        Cache cache = new RedissonCache(redisConf);
        return cache;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }
}
