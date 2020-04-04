package com.moon.store.test.nacos;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.stereotype.Service;

/**
 * Config service example
 * @author Nacos
 *
 */
@Service
public class ConfigExample2 {

    @NacosInjected
    private ConfigService configService;



}