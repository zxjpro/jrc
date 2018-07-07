package com.xiaojiezhu.jrc.web.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * index controller
 * @author xiaojie.zhu
 */
@Controller
public class IndexController {

    /**
     * index
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "/index.html";
    }
}
