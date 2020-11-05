package com.moon.store.controller;

import com.moon.store.domain.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class TestController {

    @RequestMapping("/test")
    public Object getUserJson(HttpServletRequest request, HttpServletResponse response){
        try {
            FileInputStream in = new FileInputStream("C:\\Users\\liujia54\\Desktop\\图片\\aaabbb.jpg");
            byte[] data = new byte[in.available()];
            in.read(data);

            response.setHeader("Content-Type", "image/jpeg");
            response.getOutputStream().write(data);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return new Response(1, "成功", "数据");
        return null;
    }

}
