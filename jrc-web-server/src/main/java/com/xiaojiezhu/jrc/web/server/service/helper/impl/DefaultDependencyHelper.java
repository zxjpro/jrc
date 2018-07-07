package com.xiaojiezhu.jrc.web.server.service.helper.impl;

import com.xiaojiezhu.jrc.server.dao.mysql.DependencyDao;
import com.xiaojiezhu.jrc.web.server.service.helper.DependencyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaojie.zhu
 */
@Service
public class DefaultDependencyHelper implements DependencyHelper {
    @Autowired
    private DependencyDao dependencyDao;





}
