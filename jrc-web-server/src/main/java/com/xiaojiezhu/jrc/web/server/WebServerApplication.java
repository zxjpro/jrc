package com.xiaojiezhu.jrc.web.server;

import com.xiaojiezhu.jrc.server.dao.configuration.DataSourceConfiguration;
import com.xiaojiezhu.jrc.server.dao.configuration.MybatisConfiguration;
import com.xiaojiezhu.jrc.web.server.support.WebMvcConfig;
import com.xiaojiezhu.jrc.web.server.support.exception.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * start web server
 * @author xiaojie.zhu
 */
@Import({WebMvcConfig.class, DataSourceConfiguration.class, MybatisConfiguration.class,GlobalExceptionHandler.class})
@SpringBootApplication(scanBasePackages = {"com.xiaojiezhu.jrc"})
public class WebServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServerApplication.class,args);
    }
}