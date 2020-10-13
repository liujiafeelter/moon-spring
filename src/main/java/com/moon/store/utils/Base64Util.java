package com.moon.store.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 *  Base 64主要用途不是加密，而是编码
 *  把一些二进制数转成普通字符，方便在网络上传输
 *  比如网络中图片的传输。
 */
public class Base64Util {

    /**
     * BASE64Encoder
     * BASE64Decoder
     */
    public static void BASE64Encoder(String str) {
        System.out.println("BASE64Encoder 编码前：" + str);

        String encoded = new BASE64Encoder().encode(str.getBytes());
        System.out.println("BASE64Encoder 编码后：" + encoded);

        try {
            byte[] decoded = new BASE64Decoder().decodeBuffer(encoded);
            System.out.println("BASE64Encoder 解码后：" + new String(decoded));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Base64
     */
    public static void base64(String str) {
        System.out.println("Base64 编码前：" + str);

        //Base64 加密
        String encoded = Base64.getEncoder().encodeToString(str.getBytes());
        System.out.println("Base64 编码后：" + encoded);

        //Base64 解密
        byte[] decoded = Base64.getDecoder().decode(encoded);
        System.out.println("Base64 解码后：" + new String(decoded));
    }

    public static void main(String[] args) {
        base64("你好你好你好");
        System.out.println("========================");
        BASE64Encoder("你好你好你好");
    }

}
