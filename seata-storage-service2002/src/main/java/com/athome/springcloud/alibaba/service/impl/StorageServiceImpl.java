package com.athome.springcloud.alibaba.service.impl;

import com.athome.springcloud.alibaba.dao.StorageDao;
import com.athome.springcloud.alibaba.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageDao storageDao;
    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId,count);
    }
}
