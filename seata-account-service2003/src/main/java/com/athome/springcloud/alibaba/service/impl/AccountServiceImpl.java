package com.athome.springcloud.alibaba.service.impl;

import com.athome.springcloud.alibaba.dao.AccountDao;
import com.athome.springcloud.alibaba.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;
    @Override
    public void decrease(Long userId, BigDecimal money) {
        BigDecimal a = new BigDecimal (100);
        //try {
            if(money.compareTo(a) == 0){
                throw new IllegalArgumentException("---");
            }
       // } catch (IllegalArgumentException e) {
       //     e.printStackTrace();
       // }
        accountDao.decrease(userId,money);
    }
}
