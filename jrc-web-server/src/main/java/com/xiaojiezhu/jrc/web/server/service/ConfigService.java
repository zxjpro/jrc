package com.xiaojiezhu.jrc.web.server.service;

import com.xiaojiezhu.jrc.common.config.Config;
import com.xiaojiezhu.jrc.model.Unit;
import com.xiaojiezhu.jrc.model.Version;
import com.xiaojiezhu.jrc.web.server.support.model.LimitResult;

/**
 * @author xiaojie.zhu
 */
public interface ConfigService {

    /**
     * Add a unit
     * @param unit unit define
     * @return 0 success, 1 unit exist
     */
    int addUnit(Unit unit);

    /**
     * list unit
     * @param index
     * @param size
     * @param unitName
     * @return
     */
    LimitResult listUnit(int index, int size, String group,String unitName);


    /**
     * Add a version
     * @param version
     * @return
     */
    int addVersion(Version version);


    /**
     * list version
     * @param index
     * @param size
     * @param version
     * @param unitId
     * @param profile
     * @return
     */
    LimitResult listVersion(int index, int size,int unitId, String version,String profile);

    /**
     * Find version config content by id
     * @param versionId version table id
     * @return
     */
    Config findConfigContentByVersionId(int versionId);


    /**
     * Set version config data
     * @param versionId
     * @param configContent
     */
    void updateVersionConfigContent(int versionId, String configContent);

    /**
     * list unitVersion
     * @param group
     * @param unit
     * @param version
     * @param profile
     * @param index
     * @param size
     * @return
     */
    LimitResult listUnitVersion(String group, String unit, String version, String profile, int index, int size);

    /**
     * delete the unit config , if it has not a version config,if it has a version config , it cant not delete it
     * @param id the unit table id
     * @return 0 success 1 has version config
     */
    int deleteUnitConfig(int id);

    /**
     * delete the version config
     * @param id version table id
     * @return 0 success , 1 has dependency can't delete
     */
    int deleteVersionConfig(int id);
}
