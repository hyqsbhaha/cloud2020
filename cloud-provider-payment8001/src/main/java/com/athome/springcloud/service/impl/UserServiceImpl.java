package com.athome.springcloud.service.impl;

import com.athome.springcloud.dao.UserDao;
import com.athome.springcloud.entities.User;
import com.athome.springcloud.service.UserService;
import com.athome.springcloud.utils.SerializeUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private SerializeUtil serializeUtil;

    @Override
    public int create(User user) {
        return userDao.create(user);
    }

    @Override
    public User findByUserName(String userName) {
        System.out.println("查找数据库了");
        User user =  userDao.findByUserName(userName);
        System.out.println("-----保存到redis-----");
        redisTemplate.opsForValue().set(userName, serializeUtil.serizlize(user));
        return user;
    }
}
