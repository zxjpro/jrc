package com.xiaojiezhu.jrc.web.server.controller;

import com.xiaojiezhu.jrc.client.JrcConfigCache;
import com.xiaojiezhu.jrc.client.util.FlushUtil;
import com.xiaojiezhu.jrc.common.BeanUtil;
import com.xiaojiezhu.jrc.common.config.Config;
import com.xiaojiezhu.jrc.kit.JrcUtil;
import com.xiaojiezhu.jrc.model.Unit;
import com.xiaojiezhu.jrc.model.Version;
import com.xiaojiezhu.jrc.web.server.annotation.FlushCache;
import com.xiaojiezhu.jrc.web.server.model.PostConfigData;
import com.xiaojiezhu.jrc.web.server.service.ConfigService;
import com.xiaojiezhu.jrc.web.server.support.ResponseBody;
import com.xiaojiezhu.jrc.web.server.support.exception.ex.NoticeException;
import com.xiaojiezhu.jrc.web.server.support.model.LimitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xiaojie.zhu
 */
@RequestMapping("/config")
@Controller
public class ConfigController {
    public final static Logger LOG = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private ConfigService configService;

    /**
     * add a unit
     * @param unit
     * @return 0 success, 1 exist
     */
    @FlushCache
    @ResponseBody
    @RequestMapping("/addUnit")
    public int addUnit(@RequestBody()Unit unit){
        BeanUtil.ValidateResult validateResult = BeanUtil.validateBean(unit);
        if(validateResult.getErrorNum() > 0){
            LOG.warn(validateResult.getErrorInfo());
            throw new NoticeException(validateResult.getErrorInfo());
        }
        int resultCode = configService.addUnit(unit);
        return resultCode;
    }

    @ResponseBody
    @RequestMapping("/listUnit")
    public LimitResult listUnit(@RequestParam("index")int index, @RequestParam("size")int size,@RequestParam("group")String group, @RequestParam("unit")String unitName){
        if(size > 30){
            throw new RuntimeException("out query size");
        }
        return configService.listUnit(index,size,group,unitName);
    }


    /**
     * Add a config version
     * @param version
     * @return 0 success , 1 exist
     */
    @FlushCache
    @ResponseBody
    @RequestMapping("/addVersion")
    public int addVersion(@RequestBody Version version){
        BeanUtil.ValidateResult validateResult = BeanUtil.validateBean(version);
        if(validateResult.getErrorNum() > 0){
            LOG.warn(validateResult.getErrorInfo());
            throw new NoticeException(validateResult.getErrorInfo());
        }else{
            int resultCode = configService.addVersion(version);
            return resultCode;
        }
    }

    @ResponseBody
    @RequestMapping("/listVersion")
    public LimitResult listVersion(@RequestParam("index")int index, @RequestParam("size")int size,
                                   @RequestParam("unitId")int unitId,@RequestParam("version")String version,
                                   @RequestParam("profile")String profile){
        if(size > 30){
            throw new RuntimeException("out query size");
        }
        return configService.listVersion(index,size,unitId,version,profile);
    }

    /**
     * Find version config content
     * @return
     */
    @ResponseBody
    @RequestMapping("/findConfigContentByVersionId")
    public Config findConfigContentByVersionId(@RequestParam("versionId")int versionId){
        return configService.findConfigContentByVersionId(versionId);
    }

    /**
     * Set a version config data
     * @param configContent
     * @param versionId
     */
    @ResponseBody
    @FlushCache
    @RequestMapping("/updateVersionConfigContent")
    public void updateVersionConfigContent(@RequestBody()PostConfigData configContent, @RequestParam("versionId")int versionId){
        configService.updateVersionConfigContent(versionId,configContent.getContent());
    }

    /**
     * List unitVersion
     * @param group
     * @param unit
     * @param version
     * @param profile
     * @param index
     * @param size
     * @return
     */
    @ResponseBody
    @RequestMapping("/listUnitVersion")
    public LimitResult listUnitVersion(@RequestParam("group")String group, @RequestParam("unit")String unit,
                                             @RequestParam("version")String version, @RequestParam("profile")String profile,
                                             @RequestParam("index")int index, @RequestParam("size")int size){
        return configService.listUnitVersion(group,unit,version,profile,index,size);
    }


    /**
     * delete the unit config , if it has not a version config,if it has a version config , it cant not delete it
     * @param id the unit table id
     * @return 0 success 1 has version config
     */
    @ResponseBody
    @RequestMapping("/deleteUnitConfig")
    public int deleteUnitConfig(@RequestParam("id")int id){
        int result = configService.deleteUnitConfig(id);
        if(result == 0){
            FlushUtil.flushCache();
        }
        return result;
    }


    /**
     * delete the version config
     * @param id version table id
     * @return 0 success , 1 has dependency can't delete
     */
    @ResponseBody
    @RequestMapping("/deleteVersionConfig")
    public int deleteVersionConfig(@RequestParam("id")int id){
        int result = configService.deleteVersionConfig(id);
        if(result == 0){
            FlushUtil.flushCache();
        }
        return result;
    }
}
