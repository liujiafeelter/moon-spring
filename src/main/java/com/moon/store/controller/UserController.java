package com.moon.store.controller;

import com.moon.store.domain.Response;
import com.moon.store.domain.User;
import com.moon.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/add")
    public int add(User user){
        return 1;
        //return userService.add(user);
    }

    @ResponseBody
    @RequestMapping("/delById")
    public int delById(int id){
        return userService.delById(id);
    }

    @ResponseBody
    @RequestMapping("/update")
    public int update(int id, String uname){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("uname", uname);
        return userService.update(map);
    }

    @ResponseBody
    @RequestMapping("/getById")
    public User getById(int id){
        return null;
        //return userService.getById(id);
    }


    //====================================================================

    @ResponseBody
    @RequestMapping("/getUserJson")
    public Object getUserJson(String name){
        System.out.println("nihao：" + "getUserJson");
        return new Response(1, "成功", "数据");
    }

    @RequestMapping(value = "/testHtml")
    public String goHome() {
        return "/resources/html/test";
    }

    @ResponseBody
    @RequestMapping("/testString")
    public String testString(){
        return "nihao";
    }

}
