package com.xiaojiezhu.jrc.server.common.impl;

import com.xiaojiezhu.jrc.common.cache.Cache;
import com.xiaojiezhu.jrc.common.exception.UnSupportConfigException;
import com.xiaojiezhu.jrc.common.resolve.DefaultConfigResolve;
import com.xiaojiezhu.jrc.model.Version;
import com.xiaojiezhu.jrc.server.common.JrcConfigService;
import com.xiaojiezhu.jrc.server.dao.mysql.DependencyDao;
import com.xiaojiezhu.jrc.server.dao.mysql.VersionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * @author xiaojie.zhu
 */
@Service
public class DefaultJrcConfigService implements JrcConfigService {

    public final static Logger LOG = LoggerFactory.getLogger(DefaultJrcConfigService.class);

    @Autowired
    private DependencyDao dependencyDao;
    @Autowired
    private VersionDao versionDao;


    @Override
    public Map<String, String> getGlobalVersionConfig(int versionId) {
        DefaultConfigResolve resolve = new DefaultConfigResolve();
        resolveConfig(resolve,versionId);
        Map<String, String> configContent = resolve.resolve();
        return configContent;
    }

    @Override
    public Map<String, ?> getGlobalVersionConfig(String group, String unit, String version, String profile) {
        Version dbVersion = versionDao.findVersion(group, unit, version, profile);
        if(dbVersion == null){
            throw new NullPointerException("can not find pointer , group:" + group + " , unit:"+ unit + " , version:" + version + " , unit:" + unit);
        }
        Map<String, String> globalVersionConfig = this.getGlobalVersionConfig(dbVersion.getId());
        return globalVersionConfig;
    }



    /**
     * repeat read config dependency config data
     * @param configResolve
     * @param versionId
     */
    private void resolveConfig(DefaultConfigResolve configResolve,int versionId){
        String content = dependencyDao.getVersionConfigContent(versionId);
        if(content != null){
            try {
                configResolve.addConfig(content);
            } catch (UnSupportConfigException e) {
                LOG.error("error config data format,it is:" + content);
            }
        }

        List<Integer> dependencyIds = dependencyDao.getDependencyId(versionId);
        if(dependencyIds != null && dependencyIds.size() > 0){
            for(int i = 0 ; i < dependencyIds.size() ; i ++){
                Integer dependencyId = dependencyIds.get(i);
                if(dependencyId != null){
                    resolveConfig(configResolve,dependencyId);
                }

            }
        }

    }
}
