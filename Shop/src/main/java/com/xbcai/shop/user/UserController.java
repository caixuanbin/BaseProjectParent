package com.xbcai.shop.user;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xbcai.entity.Order;
import com.xbcai.entity.Product;
import com.xbcai.entity.User;
import com.xbcai.shop.user.inter.ProductClient;
import com.xbcai.shop.user.inter.UserClient;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RefreshScope
@RestController
@RequestMapping("user")
public class UserController {
    @Value("${info.profile}")
    String content;
    @Autowired
    UserClient userClient;
    @Autowired
    ProductClient productClient;
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @GetMapping("getConfig")
    public String getConfig(){
        System.out.println("配置文件信息："+content);
        return content;
    }
    @GetMapping("createOrder")
    public String createOrder(){
        System.out.println("创建订单开始。。。。。。。。。。。。。。。。。。。");
        Order order = new Order();
        order.setOrderNO("010201805151030");
        order.setProductName("手机");
        order.setPrice("1560RMB");
        order.setCreateTime(new Date());
        this.rabbitTemplate.convertAndSend("order",order);
        System.out.println("创建订单结束。。。。。。。。。。。。。。。。。。。。");
        return "OK";
    }
    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("getShopUser")
    public User getShopUser(){
       System.out.println("shop------------------------------1"+content);
        return userClient.getUser();
    }
    @HystrixCommand(fallbackMethod = "fallProduct")
    @GetMapping("getProduct")
    public Product getProduct(){
        System.out.println("getProduct----------------------------");
        return productClient.getProduct();
    }
    public Product fallProduct(){
        System.out.println("fallProduct-----------------------被调用");
        Product product = new Product();
        product.setName("--");
        product.setPrice("--");
        return product;
    }
    public User fallback(){
        System.out.println("fallback-----------------------被调用");
        User user =new User();
        user.setName("无");
        user.setSex("无");
        user.setDept("无");
        user.setCompany("无");
        user.setJob("无");
        return user;
    }


}
