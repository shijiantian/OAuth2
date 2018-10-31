package com.shijt.oauth2.services.impl;

import com.shijt.oauth2.dao.TestMysqlDao;
import com.shijt.oauth2.services.TestMysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TestMysqlServiceImpl implements TestMysqlService {

    @Autowired
    private TestMysqlDao testMysqlDao;

    @Override
    public List<String> getUserName(){
        List<String> results=testMysqlDao.getAllUsers();
        return results;
    }
}
