package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.dto.ControllerResult;
import com.shijt.OAuth2.dto.UserDto;
import com.shijt.OAuth2.services.UserService;
import com.shijt.OAuth2.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = GlobalConsts.login_no_need)
public class OpenUserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "userRegister",method = RequestMethod.POST)
    public Object addUser(@RequestBody UserDto userDto){
        User newUser=new User(userDto);
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        newUser.setPassword(encoder.encode(userDto.getPassword()));
        return new ControllerResult(userService.addUser(newUser));
    }
}
