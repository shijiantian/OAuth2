package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.services.TestMysqlService;
import com.shijt.OAuth2.vo.ControllerResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "登录接口测试")
@RestController
@RequestMapping(GlobalConsts.login_need)
public class AuthTestController {

    @Autowired
    private TestMysqlService mysqlService;
    @Autowired

    @ApiOperation(value = "登录测试",notes = "测试登录并返回所以用户信息")
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public Object helloWorld(){
        List<String> studentName=mysqlService.getUserName();
        return new ControllerResult(studentName);
    }
}
