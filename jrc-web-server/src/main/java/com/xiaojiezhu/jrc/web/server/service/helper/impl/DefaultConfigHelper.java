package com.xiaojiezhu.jrc.web.server.service.helper.impl;

import com.xiaojiezhu.jrc.common.config.Config;
import com.xiaojiezhu.jrc.common.config.ConfigType;
import com.xiaojiezhu.jrc.common.config.ConfigUtil;
import com.xiaojiezhu.jrc.common.exception.UnSupportConfigException;
import com.xiaojiezhu.jrc.model.Unit;
import com.xiaojiezhu.jrc.model.Version;
import com.xiaojiezhu.jrc.server.dao.mysql.UnitDao;
import com.xiaojiezhu.jrc.server.dao.mysql.VersionDao;
import com.xiaojiezhu.jrc.web.server.service.helper.ConfigHelper;
import com.xiaojiezhu.jrc.web.server.support.exception.ex.NoticeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaojie.zhu
 */
@Service
public class DefaultConfigHelper implements ConfigHelper {

    @Autowired
    private UnitDao unitDao;
    @Autowired
    private VersionDao versionDao;

    @Override
    public boolean isExistUnit(Unit unit) {
        Unit db = unitDao.findUnit(unit.getGroup(), unit.getUnit());
        return db != null;
    }

    @Override
    public void addUnit(Unit unit) {
        boolean r = unitDao.insertUnit(unit);
    }

    @Override
    public boolean isExistVersion(Version version) {
        Version db = versionDao.findVersion(version.getGroup(),version.getUnit(), version.getVersion(),version.getProfile());
        return db != null;
    }

    @Override
    public void addVersion(Version version) {
        versionDao.insertVersion(version);
    }

    @Override
    public Config getRealConfigByVersionId(int versionId) {
        Version version = versionDao.findVersionById(versionId);
        if(version == null){
            throw new NullPointerException("versionId:" + version + " can not find version info");
        }
        if(version.getContent() == null){
            return null;
        }
        ConfigType configType = Config.getConfigTypeByCode(version.getConfigType());

        return new Config(configType,version.getContent());
    }

    @Override
    public void updateVersionConfigContent(int versionId, String configContent) {
        int configTypeCode = -1;
        try {
            ConfigType configType = ConfigUtil.getConfigType(configContent);
            configTypeCode = Config.getCodeByConfigType(configType);
        } catch (UnSupportConfigException e) {
            throw new NoticeException("config data format error",e);
        }
        int n = versionDao.updateVersionConfigContent(versionId,configContent,configTypeCode);
        if(n == 0){
            throw new NoticeException("update version config fail,may be the version not exists , versionId:" + versionId);
        }
    }

}
