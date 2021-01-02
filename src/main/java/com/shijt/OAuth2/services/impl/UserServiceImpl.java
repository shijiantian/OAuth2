package com.shijt.OAuth2.services.impl;

import com.shijt.OAuth2.dao.UserBaseInfoDao;
import com.shijt.OAuth2.dao.UserDao;
import com.shijt.OAuth2.dao.UserRoleDao;
import com.shijt.OAuth2.services.UserService;
import com.shijt.OAuth2.vo.User;
import com.shijt.OAuth2.vo.UserBaseInfo;
import com.shijt.OAuth2.vo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private UserBaseInfoDao userBaseInfoDao;

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

    @Override
    public Object getUserBaseInfoById(Long id) {
        UserBaseInfo ubi=userBaseInfoDao.findById(id).orElse(null);
        return ubi;
    }

    @Override
    public User findById(Long uid) {
        return userDao.findById(uid).orElse(null);
    }

    @Transactional
    private void saveUser(User user) {
        userDao.save(user);
        UserRole userRole=new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(2L);
        userRoleDao.save(userRole);
    }
}
