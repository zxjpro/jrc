package com.xiaojiezhu.jrc.web.server.controller;

import com.xiaojiezhu.jrc.model.User;
import com.xiaojiezhu.jrc.server.dao.mysql.UserDao;
import com.xiaojiezhu.jrc.web.server.model.ServerStatus;
import com.xiaojiezhu.jrc.web.server.support.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * jrc cluster server report
 * @author xiaojie.zhu
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private UserDao userDao;

    /**
     * return server status
     * @return server status
     */
    @ResponseBody
    @RequestMapping("/getServerStatus")
    public ServerStatus getServerStatus(){
        List<User> users = userDao.findUsers();
        System.out.println(users);
        ServerStatus serverStatus = new ServerStatus(1,10,12);
        return serverStatus;
    }

}
