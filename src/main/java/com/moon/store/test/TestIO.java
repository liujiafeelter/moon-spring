package com.moon.store.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestIO {

    public static void main(String[] args) throws Exception{
        FileInputStream in = new FileInputStream("D:\\java.txt");
        in.read();
    }


}
