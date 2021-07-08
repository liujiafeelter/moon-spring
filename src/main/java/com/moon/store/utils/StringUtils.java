package com.moon.store.utils;

public class StringUtils {

    public static boolean contains(String all, String compare){
        all = "," + all + ",";
        compare = "," + compare + ",";
        if(all.contains(compare)){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(contains("1,2,23,34", "2"));
        System.out.println(contains("1,2,23,34", "22"));
    }

}
