package com.controller;

import com.services.UserService;
import com.xbcai.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;
@RestController
public class TestController {
    @Autowired
    private UserService userService;
    @RequestMapping("uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
        //登录redis服务端，输入命令keys 'session*'，查看缓存是否成功。
    }
    @RequestMapping("getUser")
    public User getUser(){
        return userService.getUserInfo("caixuanbin", "赛程", "男",
                "开发部","城西元");
    }
}
