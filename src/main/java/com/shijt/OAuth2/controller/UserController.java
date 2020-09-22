package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.dto.ControllerResult;
import com.shijt.OAuth2.dto.UserDetailsDto;
import com.shijt.OAuth2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = GlobalConsts.login_need)
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "getUserBaseInfo",method = RequestMethod.GET)
    public Object getUserBaseInfo(){
        Long uid=((UserDetailsDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return new ControllerResult(userService.getUserBaseInfoById(uid));
    }
}
