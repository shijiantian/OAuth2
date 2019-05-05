package com.shijt.OAuth2.services.impl;

import com.shijt.OAuth2.services.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void getValue1() {
        Object result=redisService.getValue("testKey");
        System.out.println(null==result?"result is null":result.toString());
    }

    @Test
    public void setValue1() {
        redisService.setValue("testKey","hello");
    }
}