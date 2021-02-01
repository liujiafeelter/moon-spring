package com.moon.store;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        String cqNumber = "鲁德房权证齐字第022374号371425000000000000006100";
        //String cqNumber = "使用证号250号";

        /*System.out.println("123asdas123".replaceAll(".*[^\\d](?=(\\d+))",""));
        System.out.println("asdas321".replaceAll(".*[^\\d](?=(\\d+))",""));
        System.out.println("123asdad".replaceAll(".*[^\\d](?=(\\d+))",""));*/

        Pattern pattern = Pattern.compile("\\d+$");
        Matcher matcher1 = pattern.matcher("123asdas123");
        if(matcher1.find()){
            System.out.println(matcher1.group());
        }

        Matcher matcher2 = pattern.matcher("asdas321");
        if(matcher2.find()){
            System.out.println(matcher2.group());
        }

        Matcher matcher3 = pattern.matcher("123asdad");
        if(matcher3.find()){
            System.out.println(matcher3.group());
        }


    }


}


