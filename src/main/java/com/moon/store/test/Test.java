package com.moon.store.test;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

    }

    public List<String> findSimilarImage(String targetImage, List<String> imageList){
        List<String> returnList = new ArrayList<>();
        int numTop = 0;

        for(String image : imageList){
            //1、计算海明距离
            int num = 0;//不同的个数
            for(int i=0; i<image.length(); i++){
                char imageChar = image.charAt(i);
                char targetImageChar = targetImage.charAt(i);
                if(imageChar != targetImageChar){
                    num ++;
                }
            }

            //2、海明距离最小的返回
            if(numTop == 0){
                returnList.add(image);
                numTop = num;
            }else if(num < numTop){
                returnList.clear();
                returnList.add(image);
                numTop = num;
            }else if(num == numTop){
                returnList.add(image);
            }
        }
        return returnList;
    }

}
