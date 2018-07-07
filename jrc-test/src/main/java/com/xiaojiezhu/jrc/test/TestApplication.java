package com.xiaojiezhu.jrc.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaojie.zhu
 */
@RestController
@SpringBootApplication
public class TestApplication {
    @RequestMapping
    public String a(){
        return "hello";
    }
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);
    }
}
