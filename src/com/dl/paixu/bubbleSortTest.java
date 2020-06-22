package com.dl.paixu;

import java.util.Arrays;

/**
 * 冒泡排序、
 * 每次循环出一个最大值
 */
public class bubbleSortTest {

    public static void bubbleSort(int[] arr){
    if (arr==null||arr.length<=1){return;}

        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j]>arr[j+1]){
                    int temp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                }
            }
        }

    }


    public static void main(String[] args) {
        int[] arr={2,3,89,68,17,60,70,89};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
