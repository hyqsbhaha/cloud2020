package com.athome.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.athome.springcloud.alibaba.dao"})
public class MabaitsConfig {
}
