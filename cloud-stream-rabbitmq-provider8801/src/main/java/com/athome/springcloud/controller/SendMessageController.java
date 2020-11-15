package com.athome.springcloud.controller;

import com.athome.springcloud.service.IMessageProviderService;
import com.athome.springcloud.service.impl.IMessageProviderServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource
    private IMessageProviderService iMessageProviderService;

    @GetMapping(value = "/sendMessage")
    public String sendMessage(){
        return iMessageProviderService.send();
    }
}
