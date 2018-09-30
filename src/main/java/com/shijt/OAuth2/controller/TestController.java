package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.vo.ControllerResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "非登录接口测试")
@RestController
@RequestMapping(value = GlobalConsts.login_no_need)
public class TestController {

    @ApiOperation(value = "返回hello world",notes = "接口测试，返回hello world")
    @RequestMapping(value = "helloWorld",method = RequestMethod.GET)
    public Object test(){

        return new ControllerResult("hello world!");
    }
}
