package com.xbcai.shop.user.inter;

import com.xbcai.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("user-center")
public interface UserClient {
    @GetMapping("getUser")
    public User getUser();
}
