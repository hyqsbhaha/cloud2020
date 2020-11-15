package com.athome.springcloud.dao;

import com.athome.springcloud.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    int create(User user);

    User findByUserName(@Param("userName") String userName);
}
