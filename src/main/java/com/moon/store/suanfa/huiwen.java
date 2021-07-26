package com.moon.store.suanfa;

/**
 * 双层遍历，取出所有子串
 * 每个子串判断是不是回文
 * 字符串翻转，如何和原串一样，就是回文
 */
public class huiwen {

    public static void main(String[] args) {
        String aaa = "aaabaaa你好的啊mmmnnmmm";
        getHuiWen(aaa);
    }

    private static void getHuiWen(String str){
        String maxHuiWen = "";
        for(int i=0; i<str.length(); i++){
            for(int j=i; j<str.length()+1; j++) {
                String subStr = str.substring(i, j);
                if(isHuiWen(subStr) && subStr.length()>maxHuiWen.length()){
                    System.out.println(subStr);
                    maxHuiWen = subStr;
                }
            }
        }
        System.out.println("最长回文:" + maxHuiWen);
    }

    private static boolean isHuiWen(String str){
        String newStr = reverse(str);
        if(str.equals(newStr)){
            return true;
        }
        return false;
    }

    private static String reverse(String str){
        String newStr = "";
        for(int i=str.length()-1; i>=0; i--){
            newStr += str.charAt(i);
        }
        return newStr;
    }


}
