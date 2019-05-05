package com.shijt.OAuth2.services;

public interface RedisService {
    public Object getValue(final String key);
    public void setValue(final String key ,final Object value);
}
