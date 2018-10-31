package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.vo.ControllerResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GlobalConsts.login_need)
public class AuthTestController {

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public Object helloWorld(){
        return new ControllerResult("login succeed");
    }
}
