package com.athome.springcloud.controller;

import com.athome.springcloud.entities.CommonResult;
import com.athome.springcloud.entities.Payment;
import com.athome.springcloud.entities.User;
import com.athome.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;


    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        //openfeign-ribbon,客户端一般默认等待一秒钟
        return paymentFeignService.paymentFeignTimeOut();
    }

    @PostMapping(value = "/consumer/user/login")
    public CommonResult userLogin(@RequestBody User user){
        return paymentFeignService.userLogin(user);
    }
}
