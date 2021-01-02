package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.dto.ControllerResult;
import com.shijt.OAuth2.dto.UserDetailsDto;
import com.shijt.OAuth2.services.UserService;
import com.shijt.OAuth2.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @RequestMapping(value = "validPasswd",method = RequestMethod.POST)
    public Object validPassword(@RequestBody Map<String,String> params){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        Long uid=((UserDetailsDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        User user=userService.findById(uid);
        if(encoder.matches(params.get("oldPasswd"),user.getPassword())){
            return new ControllerResult(true);
        }else{
            return new ControllerResult(false);
        }
    }
}
