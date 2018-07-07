package com.xiaojiezhu.jrc.web.server.service.helper;

import com.xiaojiezhu.jrc.common.config.Config;
import com.xiaojiezhu.jrc.model.Unit;
import com.xiaojiezhu.jrc.model.Version;

/**
 * @author xiaojie.zhu
 */
public interface ConfigHelper {
    /**
     * The unit is exist
     * @param unit
     * @return If true,then exist
     */
    boolean isExistUnit(Unit unit);

    /**
     * Add unit to database
     * @param unit
     */
    void addUnit(Unit unit);

    /**
     * version is exists
     * @param version
     * @return
     */
    boolean isExistVersion(Version version);


    /**
     * add version
     * @param version
     */
    void addVersion(Version version);

    /**
     * Get the real config data
     * @param versionId version table id
     * @return
     */
    Config getRealConfigByVersionId(int versionId);

    /**
     * update version table cnofig data
     * @param versionId
     * @param configContent
     */
    void updateVersionConfigContent(int versionId, String configContent);
}
