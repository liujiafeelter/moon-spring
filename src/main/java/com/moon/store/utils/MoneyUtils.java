package com.moon.store.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyUtils {

    public static String formatDecimal(String pattern, BigDecimal decimal){
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(decimal);
    }

    public static String formatTwoDecimal(BigDecimal decimal){
        return formatDecimal("#.00", decimal);
    }

    public static String stripTrailingZeros(BigDecimal decimal){
        return decimal.stripTrailingZeros().toPlainString();
    }

    public static void main(String[] args) {
        System.out.println(formatTwoDecimal(new BigDecimal("2.256")));//2.26
        System.out.println(formatTwoDecimal(new BigDecimal("2.25")));//2.25
        System.out.println(formatTwoDecimal(new BigDecimal("2.0"))); //2.00
        System.out.println(formatTwoDecimal(new BigDecimal("2")));//2.00

        System.out.println(stripTrailingZeros(new BigDecimal("2.00")));
        System.out.println(stripTrailingZeros(new BigDecimal("2.22")));
    }

}
