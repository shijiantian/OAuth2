package com.shijt.auth2.services.impl;

import com.shijt.auth2.dao.TestMysqlDao;
import com.shijt.auth2.services.TestMysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TestMysqlServiceImpl implements TestMysqlService {

    @Autowired
    private TestMysqlDao testMysqlDao;

    @Override
    public List<String> getStudentName(){
        List<String> results=testMysqlDao.getAllStudents();
        return results;
    }
}
