package com.shijt.OAuth2.services.impl;

import com.shijt.OAuth2.dao.RoleDao;
import com.shijt.OAuth2.dao.RoleResourceDao;
import com.shijt.OAuth2.dao.UserDao;
import com.shijt.OAuth2.dao.UserRoleDao;
import com.shijt.OAuth2.dto.UserDetailsDto;
import com.shijt.OAuth2.vo.Role;
import com.shijt.OAuth2.vo.RoleResource;
import com.shijt.OAuth2.vo.User;
import com.shijt.OAuth2.vo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userDao.findUserByName(username);
        if(user==null)
            throw new UsernameNotFoundException("未找到该用户！");
        List<Role> authorities=new ArrayList<>();
        List<UserRole> userRoles=userRoleDao.findByUserID(user.getId());
        Iterable<Long> roleIds=userRoles.stream().map(vo->vo.getRoleId()).collect(Collectors.toList());
        Iterable<Role> roles=roleDao.findAllById(roleIds);
        roles.forEach(role->{
            List<RoleResource> roleResources=roleResourceDao.findByRoleId(role.getId());
            Set<String> resourceIds=roleResources.stream().map(vo->""+vo.getResourceId()).collect(Collectors.toSet());
            role.setResourceIds(resourceIds);
            authorities.add(role);
        });

        return new UserDetailsDto(user.getName(),user.getPassword(),authorities,user.getId());
    }
}
