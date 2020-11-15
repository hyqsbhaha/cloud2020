package com.athome.springcloud.service.impl;

import com.athome.springcloud.service.IMessageProviderService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

//@Service 和rabbit打交道不需要这个注解
@EnableBinding(Source.class)//定义消息的推送管道
public class IMessageProviderServiceImpl implements IMessageProviderService {
    @Resource
    private MessageChannel output;//消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("serail:"+serial);
        return null;
    }
}
