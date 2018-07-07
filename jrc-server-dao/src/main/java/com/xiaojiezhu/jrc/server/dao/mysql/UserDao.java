package com.xiaojiezhu.jrc.server.dao.mysql;

import com.xiaojiezhu.jrc.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiaojie.zhu
 */
public interface UserDao {

    @Select("select * from user")
    List<User> findUsers();

}
