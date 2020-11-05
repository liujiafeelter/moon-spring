package com.moon.store.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class SentinelServiceImpl implements SentinelService{

    @Override
    @SentinelResource(value = "HelloWorld", blockHandler = "blockHandler", fallback = "fallback")
    public void sentinel(){
        System.out.println("sentinel");
    }

    /**
     * 限流或降级时的处理逻辑
     * 注意: 方法参数、返回值要与原函数保持一致
     * @return
     */
    public void blockHandler(BlockException e) {
        System.out.println("blockHandler");
    }

    /**
     * 抛出异常提供fallback处理
     * 注意: 方法参数、返回值要与原函数保持一致
     * @return
     */
    public void fallback(Throwable e) {
        System.out.println("fallback");
    }

}
