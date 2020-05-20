package com.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/index")
    public String index(){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("a.getAuthorities()--》"+a.getAuthorities());
        System.out.println("a.getCredentials()--》"+a.getCredentials());
        System.out.println("a.getDetails()--》"+a.getDetails());
        System.out.println("a.getName()--》"+a.getName());
        System.out.println("a.getClass()--》"+a.getClass());
        String s =a.getAuthorities()+"|"+a.getCredentials()+"|"+a.getDetails()+"|"+a.getPrincipal()+"|"+a.getName()+"|"+a.getClass();
        return s;
    }
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("page")
    public String page(){
        return "page";
    }
    @GetMapping("bottom")
    public String bottom(){
        return "bottom";
    }

}
