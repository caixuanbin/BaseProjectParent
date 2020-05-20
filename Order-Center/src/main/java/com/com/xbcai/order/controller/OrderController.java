package com.com.xbcai.order.controller;

import com.xbcai.entity.Order;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RabbitListener(queues = "order")
public class OrderController {
    @RabbitHandler
    public void dealOrder(Order order) {
        System.out.println("订单处理业务开始。。。。。。。。。。。。");
        System.out.println(order);
    }
}
