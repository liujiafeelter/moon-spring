package com.moon.store;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());

        long currentTimeMillis = 1601189155370l;
        Long infoid = 43672187859225L;
        System.out.println(infoid + System.currentTimeMillis());
    }

    /**
     * 获取文件大小
     *
     * @param filePath
     * @return
     */
    public static long getFileSizeKb(String filePath) {
        try {
            File file = new File(filePath);
            long length = file.length();
            return length/1000;
        }catch (Exception e){
            e.printStackTrace();
            return -1l;
        }
    }


}
