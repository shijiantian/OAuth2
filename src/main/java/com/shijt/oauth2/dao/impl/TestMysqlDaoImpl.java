package com.shijt.oauth2.dao.impl;

import com.shijt.oauth2.dao.TestMysqlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TestMysqlDaoImpl implements TestMysqlDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getAllUsers() {
        String sql="select * from test.user";
        List<Map<String,Object>> sqlResults=jdbcTemplate.queryForList(sql);
        List<String> nameList=sqlResults.stream().map(m->m.get("name").toString()).collect(Collectors.toList());
        return nameList;
    }
}
