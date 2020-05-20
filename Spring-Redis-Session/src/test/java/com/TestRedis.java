package com;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate; // 处理字符串

    @Autowired
    private RedisTemplate redisTemplate; // 处理对象
    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("yoodb", "123");
        Assert.assertEquals("123", stringRedisTemplate.opsForValue().get("yoodb"));
    }
}