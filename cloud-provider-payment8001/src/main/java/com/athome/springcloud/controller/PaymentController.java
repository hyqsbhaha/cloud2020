package com.athome.springcloud.controller;

import com.alibaba.druid.util.StringUtils;
import com.athome.springcloud.entities.CommonResult;
import com.athome.springcloud.entities.Payment;
import com.athome.springcloud.entities.User;
import com.athome.springcloud.service.PaymentService;
import com.athome.springcloud.service.UserService;
import com.athome.springcloud.utils.RedisUtils;
import com.athome.springcloud.utils.SerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private UserService userService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private SerializeUtil serializeUtil;

    @PostMapping(value = "/payment/create")
    public CommonResult creat(@RequestBody  Payment payment){
        int result = paymentService.create(payment);
        if(result>0)
            return new CommonResult(200,"插入数据成功"+serverPort,result);
        return new CommonResult(404,"插入数据失败",null);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment != null)
            return new CommonResult(200,"查找数据成功"+serverPort,payment);
        return new CommonResult(404,"查找数据失败",null);
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println("element"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());

        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @PostMapping(value = "/user/regist")
    public CommonResult userRegist(@RequestBody User user){
        int result = userService.create(user);
        if(result >0){
            return new CommonResult(200,"插入数据成功"+serverPort,result);
        }
        return new CommonResult(404,"插入数据失败"+serverPort,null);
    }

    @PostMapping(value = "/user/login")
    public CommonResult userLogin(@RequestBody User user){
        if(StringUtils.isEmpty(user.getPassword())){
            return new CommonResult(404,"密码不能为空"+serverPort);
        }
        if(StringUtils.isEmpty(user.getUserName())){
            return new CommonResult(404,"用户名不能为空"+serverPort);
        }
        byte [] o = (byte[])redisTemplate.opsForValue().get(user.getUserName());
        User result = new User();
        if(null != o){
            result = (User)serializeUtil.deserialize(o);
            System.out.println("---redis查到数据了！---"+result);
        }

        if(null == o){
            result = userService.findByUserName(user.getUserName());
        }
        if(null != result){
            if(!result.getPassword().equals(user.getPassword())){
                return new CommonResult(404,"密码错误"+serverPort,result);
            }
            return new CommonResult(200,"登陆成功"+serverPort,result);
        }
        return new CommonResult(404,"账号不存在"+serverPort,null);
    }
}
