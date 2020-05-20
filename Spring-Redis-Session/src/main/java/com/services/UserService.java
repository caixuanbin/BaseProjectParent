package com.services;

import com.xbcai.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    /**
     * value的值就是缓存到redis中的key，此key是需要自己在进行增加缓存信息时定义的key，用于标识唯一性的。
     */
    @Cacheable(value = "redis-key")
    public User getUserInfo(String name, String company, String sex, String dept,String job) {
        System.out.println("无缓存时调用----数据库查询--"+Thread.currentThread().getName());
        User u = new User();
        u.setName(name);
        u.setCompany(company);
        u.setSex(sex);
        u.setDept(dept);
        u.setJob(job);
        return u;
    }
}