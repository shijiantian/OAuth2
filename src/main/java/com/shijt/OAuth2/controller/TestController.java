package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.dto.ControllerResult;
import com.shijt.OAuth2.utils.SerializeUtil;
import com.shijt.OAuth2.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "非登录接口测试")
@RestController
@RequestMapping(value = GlobalConsts.login_no_need)
public class TestController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @ApiOperation(value = "返回hello world",notes = "接口测试，返回hello world")
    @RequestMapping(value = "helloWorld",method = RequestMethod.GET)
    public Object test(){
        redisTemplate.opsForValue().set("redistest","1238998");
        User user1=new User();
        user1.setName("vialian");
        User user2=new User();
        user2.setName("kitty");
        List<User> list=new ArrayList<>();
        list.add(user1);
        list.add(user2);
        redisTemplate.opsForValue().set("list", SerializeUtil.serialize(list));
        String result=(String)redisTemplate.opsForValue().get("redistest");
        byte[] bytes=(byte[]) redisTemplate.opsForValue().get("list");
        List<String> list2= (List<String>) SerializeUtil.deserialize(bytes);
        return new ControllerResult("hello world!");
    }
}
