package com.shijt.auth2.controller;

import com.shijt.auth2.commons.GlobalConsts;
import com.shijt.auth2.services.TestMysqlService;
import com.shijt.auth2.services.impl.TestMysqlServiceImpl;
import com.shijt.auth2.vo.ControllerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(GlobalConsts.login_need)
public class AuthTestController {

    @Autowired
    private TestMysqlService mysqlService;
    @Autowired

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public Object helloWorld(){
        List<String> studentName=mysqlService.getUserName();
        return new ControllerResult(studentName);
    }
}
