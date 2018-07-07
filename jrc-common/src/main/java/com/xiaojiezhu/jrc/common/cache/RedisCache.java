package com.xiaojiezhu.jrc.common.cache;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxj<br>
 * 时间 2017/11/29 15:24
 * 说明 ...
 */
public abstract class RedisCache implements Cache{
    protected RedisConf redisConf;
    public RedisCache(RedisConf redisConf){
        this.redisConf = redisConf;
    }





    public static class RedisConf{
        public final static int STATUS_SINGLE = 0;
        /**
         * 0单点，1集群，2主从，3哨兵
         */
        private int status = STATUS_SINGLE;
        private List<RedisServer> redisServers = new ArrayList<RedisServer>();
        private String password;
        private int poolSize = 10;
        private int dataBase;

        /**
         * 单机器
         * @return
         */
        public RedisConf singleServer(){
            this.status = 0;
            return this;
        }

        /**
         * 增加节点
         * @param host
         * @param port
         * @return
         */
        public RedisConf addServer(String host,int port){
            this.redisServers.add(new RedisServer(host,port));
            return this;
        }
        public RedisConf setPassword(String password){
            this.password = password;
            return this;
        }
        public RedisConf setPoolSize(int size){
            this.poolSize = size;
            return this;
        }

        public int getStatus() {
            return status;
        }

        public List<RedisServer> getRedisServers() {
            return redisServers;
        }

        public String getPassword() {
            return password;
        }

        public int getPoolSize() {
            return poolSize;
        }

        public int getDataBase() {
            return dataBase;
        }

        public void setDataBase(int dataBase) {
            this.dataBase = dataBase;
        }

        class RedisServer{
            private String host;
            private int port;

            public String getHost() {
                return host;
            }

            public int getPort() {
                return port;
            }

            public RedisServer(String host, int port) {
                this.host = host;
                this.port = port;
            }
        }
    }
}
