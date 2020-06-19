package com.moon.store.test.nacos;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.stereotype.Service;

/**
 * Config service example
 * @author Nacos
 */
@Service
public class ConfigExample2 {

    @NacosInjected
    private ConfigService configService;

    public String getConfig(String dataId) throws NacosException {
        return configService.getConfig(dataId, "DEFAULT_GROUP", 1000);
    }

    @NacosConfigListener(dataId = "test")
    public void onMessage(String config) {
        System.out.println("dataId-test数据变化:" + config);
    }



}