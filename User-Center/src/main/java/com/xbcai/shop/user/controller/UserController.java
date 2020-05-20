package com.xbcai.shop.user.controller;

import com.xbcai.entity.User;
import com.xbcai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    UserService userServiceImpl;
    @GetMapping("/getUser")
    public User getUser(){
          /*try {
            Thread.sleep(5000L);//为了测试服务降级故加了此段代码
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("user-center------------------------------------1");
        return userServiceImpl.getUserById("1");
    }

}
