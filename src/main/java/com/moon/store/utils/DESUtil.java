package com.moon.store.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

public class DESUtil {

    private static String KEY = "949ad645e270d53d802d35a7be5c7886";//私钥

    /**
     * 加密
     * @param content
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String key) throws Exception{
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        kgen.init(128, random);
        SecretKey secretKey = kgen.generateKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptResult = cipher.doFinal(content.getBytes("utf-8"));

        String result = new BASE64Encoder().encode(encryptResult);
        return result;
    }

    /**
     * 解密
     * @param content
     * @return
     * @throws Exception
     */
    public static String decrypt(String content, String key) throws Exception{
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        kgen.init(128, random);
        SecretKey secretKey = kgen.generateKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptResult = cipher.doFinal(new BASE64Decoder().decodeBuffer(content));

        return new String(decryptResult,"utf-8");
    }
    //=================================================================================================================

    public static String encrypt2(String content, String key) throws Exception{
        SecureRandom e = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, securekey, e);
        byte[] encryptResult = cipher.doFinal(content.getBytes("utf-8"));
        String result = new BASE64Encoder().encode(encryptResult);
        return result;
    }

    public static String decrypt2(String content, String key) throws Exception{
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        byte[] decryptResult = cipher.doFinal(new BASE64Decoder().decodeBuffer(content));
        return new String(decryptResult,"utf-8");
    }


    public static void main(String[] args)throws Exception{
        System.out.println(encrypt("你好你好你好", KEY));
        System.out.println(decrypt(encrypt("你好你好你好", KEY), KEY));

        System.out.println("=======================");

        System.out.println(encrypt2("你好你好你好", KEY));
        System.out.println(decrypt2(encrypt2("你好你好你好", KEY), KEY));
    }

}
