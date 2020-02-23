package com.moon.spring.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @ResponseBody
    @RequestMapping("/getUser")
    public String getUser(){
        System.out.println("nihao");
        return "nihao";
    }

}
