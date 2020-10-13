package com.moon.store.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class Base64Util {

    /**
     * Base64
     */
    public static void base64(String str) {
        System.out.println("Base 64 编码前：" + str);

        //Base64 加密
        String encoded = Base64.getEncoder().encodeToString(str.getBytes());
        System.out.println("Base 64 编码后：" + encoded);

        //Base64 解密
        byte[] decoded = Base64.getDecoder().decode(encoded);
        System.out.println("Base 64 解码后：" + new String(decoded));
    }

    public static void main(String[] args) {
        base64("你好你好你好");
    }

}
