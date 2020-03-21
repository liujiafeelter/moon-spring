package com.moon.store.utils;

import java.util.regex.Pattern;

public class NumberUtils {

    public static boolean isNumber(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) {
        System.out.println(isNumber("1234"));
    }


}
