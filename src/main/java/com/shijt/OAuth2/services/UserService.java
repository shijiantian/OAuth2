package com.shijt.OAuth2.services;

import com.shijt.OAuth2.vo.User;

public interface UserService {
    String addUser(User user);
    
    boolean updatePasswd(User user);

    Object getUserBaseInfoById(Long id);

    User findById(Long uid);
}
