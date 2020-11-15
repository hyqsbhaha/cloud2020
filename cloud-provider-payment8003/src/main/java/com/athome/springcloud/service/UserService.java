package com.athome.springcloud.service;

import com.athome.springcloud.entities.User;

public interface UserService {

    int create(User user);

    User findByUserName(String userName);
}
