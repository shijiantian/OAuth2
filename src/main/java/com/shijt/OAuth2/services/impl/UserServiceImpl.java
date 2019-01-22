package com.shijt.OAuth2.services.impl;

import com.shijt.OAuth2.dao.UserDao;
import com.shijt.OAuth2.dao.UserRoleDao;
import com.shijt.OAuth2.services.UserService;
import com.shijt.OAuth2.vo.User;
import com.shijt.OAuth2.vo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public String addUser(User user) {
        User existedUser=userDao.findUserByName(user.getName());
        if(null!=existedUser){
            return "用户名已存在";
        }else{
            try{
                saveUser(user);
                return "注册成功";
            }catch (Exception e){
                return "注册失败请稍后重试";
            }
        }
    }

    @Transactional
    private void saveUser(User user) {
        userDao.save(user);
        UserRole userRole=new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(1L);
        userRoleDao.save(userRole);
    }
}
