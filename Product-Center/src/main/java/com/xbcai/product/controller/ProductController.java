package com.xbcai.product.controller;

import com.xbcai.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("getProduct")
    public Product getProduct(){
        Product product =new Product();
        product.setName("葡萄酒");
        product.setPrice("$99");
        return product;
    }
}
