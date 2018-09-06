package com.shijt.OAuth2.services.impl;

import com.shijt.OAuth2.dao.ResourceDao;
import com.shijt.OAuth2.dao.RoleResourceDao;
import com.shijt.OAuth2.dao.TestMysqlDao;
import com.shijt.OAuth2.services.TestMysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TestMysqlServiceImpl implements TestMysqlService {

    @Autowired
    private TestMysqlDao testMysqlDao;
    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public List<String> getUserName(){
        List<String> results=testMysqlDao.getAllUsers();
        resourceDao.findAll().iterator().forEachRemaining(vo->{
            results.add(vo.getUrl());
        });
        roleResourceDao.findAll().iterator().forEachRemaining(vo->{
            results.add(""+vo.getResourceId());
        });
        return results;
    }
}
