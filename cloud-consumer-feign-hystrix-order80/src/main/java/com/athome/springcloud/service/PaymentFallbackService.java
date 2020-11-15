package com.athome.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "客户端paymentInfo_ok方法请求超时";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "客户端paymentInfo_timeout方法请求超时";
    }
}
