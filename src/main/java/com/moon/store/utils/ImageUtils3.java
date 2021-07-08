package com.moon.store.utils;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtils3 {


    public static byte[] image2byte(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    public static byte[] imageUrl2byte(String imageUrl) {
        try{
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, numBytesRead);
            }

            byte[] byteArr = outputStream.toByteArray();
            outputStream.close();
            inputStream.close();
            connection.disconnect();
            return byteArr;
        }catch (Exception e) {
            return null;
        }
    }

    
    public static void ByteToFile(byte[] bytes) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try {
            BufferedImage bi1 = ImageIO.read(bais);
            File w2 = new File("C:\\Users\\liujia54\\Desktop\\img\\test.jpg");//可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                bais.close();
            }catch (Exception e){
            }
        }
    }


}
