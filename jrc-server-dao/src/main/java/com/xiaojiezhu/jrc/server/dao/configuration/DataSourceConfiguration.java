package com.xiaojiezhu.jrc.server.dao.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author xiaojie.zhu
 */
@Configuration
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "jdbc")
    public DataSource memberDataSource(){
        DruidDataSource ds = new DruidDataSource();
        return ds;
    }
}
