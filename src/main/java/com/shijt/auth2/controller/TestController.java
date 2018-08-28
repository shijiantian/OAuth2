package com.shijt.auth2.controller;

import com.shijt.auth2.commons.GlobalConsts;
import com.shijt.auth2.vo.ControllerResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = GlobalConsts.login_no_need)
public class TestController {

    @RequestMapping(value = "helloWorld",method = RequestMethod.GET)
    public Object test(){

        return new ControllerResult("hello world!");
    }
}
