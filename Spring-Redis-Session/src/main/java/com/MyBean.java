package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.redis.core.StringRedisTemplate;
@EnableDiscoveryClient
@SpringBootApplication
public class MyBean {
    @Autowired
    private StringRedisTemplate template;

    public static void main(String[] args) {

        SpringApplication.run(MyBean.class,args);
    }
}
