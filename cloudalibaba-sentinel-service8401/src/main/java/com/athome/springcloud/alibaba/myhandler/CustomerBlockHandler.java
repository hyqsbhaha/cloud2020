package com.athome.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.athome.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult customerBlockHandler1(BlockException exception){
        return new CommonResult(404,"全局配置违规返回----1");
    }
    public static CommonResult customerBlockHandler2(BlockException exception){
        return new CommonResult(404,"全局配置违规返回----2");
    }
}
