package com.moon.store.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.moon.store.domain.Response;
import com.moon.store.domain.User;
import com.moon.store.test.nacos.ConfigExample2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/nacos")
public class NacosController {

    @Autowired
    ConfigExample2 configExample2;

    @ResponseBody
    @RequestMapping("/get")
    public String getConfig(String dataId){
        try {
            return configExample2.getConfig(dataId);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }




}
