package com.moon.store.controller;

import com.moon.store.domain.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 需要引入
 * commons-fileupload包
 */
@Controller
public class FileController {

    @ResponseBody
    @RequestMapping("/upload")
    public Object upload(@RequestParam("file") CommonsMultipartFile file){
        System.out.println("上传图片：" + file.getName());
        String filePath = "C:\\Users\\liujia54\\Desktop\\图片\\";
        String fileName = "aaa.jpg";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response(1, "成功", "数据");
    }

}
