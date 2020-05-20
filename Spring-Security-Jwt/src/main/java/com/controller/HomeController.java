package com.controller;

import com.bean.Msg;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String index(Model model){
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        return "index";
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/admin/test1")
    public String adminTest1(){
        return "ROLE_USER";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin/test2")
    public String adminTest2(){
        return "ROLE_ADMIN";
    }
}
