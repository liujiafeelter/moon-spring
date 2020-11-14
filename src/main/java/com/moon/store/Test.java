package com.moon.store;

import org.apache.commons.lang3.StringUtils;

/**
 * 1、25482152,25482163,25482329,25482296,25482299
 * 2、undefined,undefined,undefined,undefined
 * 3、undefined,undefined,undefined,25482303,25482316
 * 4、25482303,25482303,25482303,undefined,undefined
 * 5、undefined,undefined,25482303,25482303,undefined
 */
public class Test {

    public final static String PIC_URL = "http://fbcheck.58.com/fbMainInfo/viewPic?infoId=";

    public static void main(String[] args) {
        //System.out.println(getImageUrl("42442890211362", "25482152,25482163,25482329,25482296,25482299"));
        //System.out.println(getImageUrl("42442890211362", "undefined,undefined,undefined,undefined"));
        //System.out.println(getImageUrl("42442890211362", "undefined,undefined,undefined,25482303,25482316"));
        //System.out.println(getImageUrl("42442890211362", "25482303,25482303,25482303,undefined,undefined"));
        //System.out.println(getImageUrl("42442890211362", "undefined,undefined,25482303,25482303,undefined"));
        System.out.println(getImageUrl("42442890211362", "undefined"));
        System.out.println(getImageUrl("42442890211362", "25482303"));
    }

    public static String getImageUrl(String infoId, String imageIds){
        if(StringUtils.isEmpty(imageIds)){
            return null;
        }
        String[] imageIdArr = imageIds.split(",");
        if(imageIdArr == null){
            return null;
        }

        String preUrl = PIC_URL + infoId + "&type=1" + "&imageId=";
        StringBuilder imageUrlBuilder = new StringBuilder();
        for(int i=0; i<imageIdArr.length; i++){
            if("undefined".equals(imageIdArr[i])){
                continue;
            }
            imageUrlBuilder.append(preUrl).append(imageIdArr[i]).append("|");
        }
        String imageUrl = imageUrlBuilder.toString();
        if(imageUrl.endsWith("|")){
            imageUrl = imageUrl.substring(0, imageUrl.length()-1);
        }
        return imageUrl;
    }

}


