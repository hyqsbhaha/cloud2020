package com.athome.springcloud.alibaba.service.impl;

import com.athome.springcloud.alibaba.dao.OrderDao;
import com.athome.springcloud.alibaba.domain.Order;
import com.athome.springcloud.alibaba.service.AccountService;
import com.athome.springcloud.alibaba.service.OrderService;
import com.athome.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "hyq-create-order",rollbackFor = Exception.class)
    //若超时，则全部回滚
    public void create(Order order) {
        System.out.println("-----------开始创建订单-----------");
        orderDao.create(order);

        System.out.println("-----------调用库存接口-----------");
        storageService.decrease(order.getProductId(),order.getCount());

        System.out.println("-----------调用余额接口-----------");
        accountService.decrease(order.getUserId(),order.getMoney());

        System.out.println("-----------调修改订单状态-----------");
        orderDao.update(order.getUserId(),0);

        System.out.println("-----------完成-----------");
    }

}
