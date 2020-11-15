package com.athome.springcloud.alibaba.controller;

import com.athome.springcloud.alibaba.domain.CommonResult;
import com.athome.springcloud.alibaba.domain.Order;
import com.athome.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;
@PostMapping(value = "/order/create")
public CommonResult create(@RequestBody Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
        }
        }
