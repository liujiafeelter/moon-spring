package com.moon.spring.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @ResponseBody
    @RequestMapping("/getUser")
    public String getUser(String name){
        System.out.println("nihao：" + name);
        return "nihao";
    }

}
