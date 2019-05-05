package com.shijt.OAuth2.services.impl;

import com.shijt.OAuth2.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Object getValue(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setValue(final String key, final Object value) {
        redisTemplate.opsForValue().set(key,value);
        redisTemplate.expire(key,60, TimeUnit.SECONDS);
    }
}
