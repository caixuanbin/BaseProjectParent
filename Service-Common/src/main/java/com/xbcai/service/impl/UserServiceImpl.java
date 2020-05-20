package com.xbcai.service.impl;

import com.xbcai.entity.User;
import com.xbcai.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(String id) {
        User user = new User();
        user.setJob("码农");
        user.setCompany("IT公司");
        user.setDept("事业部");
        user.setSex("男");
        user.setName("小刚");
        return user;
    }
}
