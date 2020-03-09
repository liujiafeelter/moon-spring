package com.moon.spring.test.controller;

import org.springframework.stereotype.Controller;
import com.moon.spring.test.domain.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

    @ResponseBody
    @RequestMapping("/getUser")
    public Object getUser(String name){
        System.out.println("nihao：" + name);
        return "nihao";
    }

    @ResponseBody
    @RequestMapping("/getUserJson")
    public Object getUserJson(String name){
        System.out.println("nihao：" + "getUserJson");
        return new Response(1, "成功", "数据");
    }


}
