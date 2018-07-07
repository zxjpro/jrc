package com.xiaojiezhu.jrc.server.controller;

import com.alibaba.fastjson.JSON;
import com.xiaojiezhu.jrc.common.cache.Cache;
import com.xiaojiezhu.jrc.kit.JrcConstant;
import com.xiaojiezhu.jrc.kit.StateConfig;
import com.xiaojiezhu.jrc.server.common.JrcConfigService;
import com.xiaojiezhu.jrc.server.service.ConfigService;
import com.xiaojiezhu.jrc.server.util.RequestKit;
import com.xiaojiezhu.jrc.web.server.support.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xiaojie.zhu
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
    public final static Logger LOG = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private ConfigService configService;
    @Autowired
    private Cache cache;

    /**
     * flush config cache
     */
    @ResponseBody
    @RequestMapping("/flushCache")
    public void flushCache(){
        cache.flushDb();
    }

    /**
     * get version config
     * @return
     */
    @ResponseBody
    @RequestMapping("/getConfig")
    public StateConfig getConfig(){
        String content = RequestKit.get();
        Map<String,String> data = JSON.parseObject(content, Map.class);
        StateConfig globalVersionConfig = configService.getGlobalVersionConfig(data.get(JrcConstant.GROUP),data.get(JrcConstant.UNIT),
                data.get(JrcConstant.VERSION),data.get(JrcConstant.PROFILE));
        return globalVersionConfig;
    }
}
