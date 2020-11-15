package com.athome.springcloud.service;

import com.athome.springcloud.entities.Payment;
import com.athome.springcloud.entities.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    int create(User user);

    User findByUserName(String userName);
}
