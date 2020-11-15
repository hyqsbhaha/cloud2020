package com.athome.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.athome.springcloud.alibaba.myhandler.CustomerBlockHandler;
import com.athome.springcloud.entities.CommonResult;
import com.athome.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RateLimitController {

    @GetMapping(value = "/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试ok",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException exception){
        return new CommonResult(200,exception.getClass().getCanonicalName()+"\t服务不可用");
    }

    @GetMapping(value = "/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        //使用系统自带的默认的配置违规返回
        return new CommonResult(200,"按url限流测试ok",new Payment(2020L,"serial002"));
    }

    @GetMapping(value = "/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,//将fallback方法放在其他类中，代码解耦
            blockHandler = "customerBlockHandler2")
    public CommonResult customerBlockHandler(){
        //使用系统自带的默认的配置违规返回
        return new CommonResult(200,"按全局配置违规返回",new Payment(2020L,"serial003"));
    }
}
