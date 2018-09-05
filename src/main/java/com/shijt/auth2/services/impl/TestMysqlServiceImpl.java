package com.shijt.auth2.services.impl;

import com.shijt.auth2.dao.PermissionDao;
import com.shijt.auth2.dao.RolePermissionDao;
import com.shijt.auth2.dao.TestMysqlDao;
import com.shijt.auth2.services.TestMysqlService;
import com.shijt.auth2.vo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TestMysqlServiceImpl implements TestMysqlService {

    @Autowired
    private TestMysqlDao testMysqlDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public List<String> getUserName(){
        List<String> results=testMysqlDao.getAllUsers();
        permissionDao.findAll().iterator().forEachRemaining(vo->{
            results.add(vo.getUrl());
        });
        rolePermissionDao.findAll().iterator().forEachRemaining(vo->{
            results.add(""+vo.getPermissionId());
        });
        return results;
    }
}
