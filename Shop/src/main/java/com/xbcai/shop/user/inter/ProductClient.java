package com.xbcai.shop.user.inter;

import com.xbcai.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("product-center")
public interface ProductClient {
    @GetMapping("getProduct")
    public Product getProduct();
}
