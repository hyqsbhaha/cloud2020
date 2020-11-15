package com.athome.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.BlockingDeque;

@RestController
public class FlowLimitController {
    /*限流控制*/
    @GetMapping(value = "/sentinel/testA")
    public String testA(){
        return "-------testA";
    }

    @GetMapping(value = "/sentinel/testB")
    public String testB(){
        return "-------testB";
    }

    @GetMapping(value = "/sentinel/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")//名称唯一即可
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2){

        return "-------testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "-------deal_testHotKey---------";
    }
}
