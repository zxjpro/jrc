package com.xiaojiezhu.jrc.web.server.service.impl;

import com.xiaojiezhu.jrc.model.Dependency;
import com.xiaojiezhu.jrc.server.dao.mysql.DependencyDao;
import com.xiaojiezhu.jrc.web.server.service.DependencyService;
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
public class DependencyServiceImpl implements DependencyService{
    public final static Logger LOG = LoggerFactory.getLogger(DependencyServiceImpl.class);
    @Autowired
    private DependencyDao dependencyDao;

    @Override
    public int addDependency(int versionId, int dependencyVersionId) {
        boolean exist = dependencyDao.versionDependencyExist(versionId, dependencyVersionId);
        if(exist){
            LOG.warn("Add dependency fail,the versionId:" + versionId +  " ,and dependencyId:" + dependencyVersionId + " is exist");
            return 1;
        }else if(versionId == dependencyVersionId){
            LOG.warn("Add dependency fail,the versionId can not equals dependencyVersionId");
            return 2;
        }else {
            dependencyDao.addDependency(versionId,dependencyVersionId);
            return 0;
        }
    }

    @Override
    public List<Long> getDependencyVersionId(int versionId) {
        return dependencyDao.getDependencyVersionId(versionId);
    }

    @Override
    public LimitResult getDependencyList(int versionId, int index, int size) {
        int start = (index -1) * size;
        List<Dependency> dependencies = dependencyDao.getDependencyList(versionId,start,size);
        long count = dependencyDao.countDependency(versionId);
        return new LimitResult(count,dependencies);
    }

    @Override
    public int countAnOtherConfigDependency(int id) {
        return dependencyDao.countAnOtherConfigDependency(id);
    }

    @Override
    public void deleteDependencyInfo(int id) {
        dependencyDao.deleteDependencyInfo(id);
    }

    @Override
    public void removeDependency(int versionId, int dependencyId) {
        dependencyDao.removeDependency(versionId,dependencyId);
    }


}
