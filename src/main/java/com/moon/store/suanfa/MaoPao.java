package com.moon.store.suanfa;

/**
 * 冒泡算法
 * 相邻两个数比较
 * 第一趟，最大的数冒泡到最后
 * 第二趟，次大的数冒泡到倒数第二个
 * 依次类推
 */
public class MaoPao {

    //a,b,c,d,e,f
    public static void sort(int[] arr){
        int temp = 0;
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length-j-1; j++) {
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }


}
