package com.xiaojiezhu.jrc.web.server.service.impl;

import com.xiaojiezhu.jrc.common.config.Config;
import com.xiaojiezhu.jrc.model.Unit;
import com.xiaojiezhu.jrc.model.UnitVersion;
import com.xiaojiezhu.jrc.model.Version;
import com.xiaojiezhu.jrc.server.dao.mysql.UnitDao;
import com.xiaojiezhu.jrc.server.dao.mysql.VersionDao;
import com.xiaojiezhu.jrc.web.server.service.ConfigService;
import com.xiaojiezhu.jrc.web.server.service.DependencyService;
import com.xiaojiezhu.jrc.web.server.service.helper.ConfigHelper;
import com.xiaojiezhu.jrc.web.server.support.model.LimitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaojie.zhu
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    public final static Logger LOG = LoggerFactory.getLogger(ConfigServiceImpl.class);
    @Autowired
    private ConfigHelper configHelper;
    @Autowired
    private UnitDao unitDao;
    @Autowired
    private VersionDao versionDao;
    @Autowired
    private DependencyService dependencyService;

    @Override
    public int addUnit(Unit unit) {
        boolean exist = configHelper.isExistUnit(unit);
        if(exist){
            LOG.warn("group:" + unit.getGroup() + ",unit:" + unit.getUnit() + " is exist");
            return 1;
        }else {
            //default enable config
            unit.setEnable(true);
            configHelper.addUnit(unit);
            return 0;
        }
    }

    @Override
    public LimitResult listUnit(int index, int size, String group,String unitName) {
        int start = (index - 1) * size;
        List<Unit> units = unitDao.listUnit(start, size, group,unitName);
        long count = unitDao.countUnit(group,unitName);
        return new LimitResult(count,units);
    }

    @Override
    public int addVersion(Version version) {
        boolean exist = configHelper.isExistVersion(version);
        if(exist){
            LOG.warn("group:" + version.getGroup() + ",unit:" + version.getUnit() + ",version:" + version.getVersion() + ",profile:" + version.getProfile() + " is exist");
            return 1;
        }else{
            //default enable
            version.setEnable(true);
            configHelper.addVersion(version);
            return 0;
        }
    }

    @Override
    public LimitResult listVersion(int index, int size,int unitId, String version,String profile) {
        int start = (index - 1) * size;
        List<Version> versions = versionDao.listVersion(start,size,unitId,version,profile);
        long count = versionDao.countVersion(unitId,version,profile);
        return new LimitResult(count,versions);
    }

    @Override
    public Config findConfigContentByVersionId(int versionId) {
        return configHelper.getRealConfigByVersionId(versionId);
    }

    @Override
    public void updateVersionConfigContent(int versionId, String configContent) {
        configHelper.updateVersionConfigContent(versionId,configContent);
    }

    @Override
    public LimitResult listUnitVersion(String group, String unit, String version, String profile, int index, int size) {
        int start = (index - 1) * size;
        List<UnitVersion> data = versionDao.listUnitVersion(group,unit,version,profile,start,size);
        long total = versionDao.countUnitVersion(group,unit,version,profile);
        return new LimitResult(total, data);
    }

    @Override
    public int deleteUnitConfig(int id) {
        //query it has version config
        int countVersion = versionDao.countVersionById(id);
        //if not have,will be delete
        if(countVersion > 0){
            LOG.warn("can't delete unit config " + id + " it has version config");
            return 1;
        }
        LOG.info("delete unit config");
        unitDao.deleteUnitConfig(id);
        return 0;
    }

    @Override
    public int deleteVersionConfig(int id) {
        //if has an other config dependency it,can't delete
        int count = dependencyService.countAnOtherConfigDependency(id);
        if(count > 0){
            LOG.warn("it has an other version config dependency it,you can't delete it");
            return 1;
        }else{
            LOG.info("delete version config");
            //delete this config dependency info
            dependencyService.deleteDependencyInfo(id);
            versionDao.deleteVersionConfig(id);
            return 0;
        }
    }
}
