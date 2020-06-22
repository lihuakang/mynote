package com.dl.paixu;

import java.util.Arrays;

/**
 * 选择排序
 * 从0元素，找到最小值，放在0元素位置上
 * 从1元素开始后面所有元素比较 ，找到最小的，放在1元素上
 */
public class xuanzeTest {

    public static void xuanze(int[] arr){
        for (int i=0;i<arr.length-1;i++){
            int min=i;
            for (int j=i+1;j<arr.length;j++){
                if (arr[j]<arr[min]){
                    min=j;
                }
            }
            if (i!=min){
                swap(arr,i,min);
            }
        }
    }
    public static void swap(int[] arr,int a,int b){
        int temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }

    public static void main(String[] args) {
        int[] arr={1,34,56,2,15,37};
        xuanze(arr);
        System.out.println(Arrays.toString(arr));
    }
}
