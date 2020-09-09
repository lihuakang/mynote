package com.dl.arithmetic;

import java.util.Arrays;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/8/31 14:41
 * 插入排序: 从头取元素插入在合适的位置
 * 向前遍历，比其小就交换
 * 时间复杂度：n^2
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr={12,33,25,64,6,34,88,13};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[] arr){
     for (int i=1;i<arr.length;i++){
         for (int j=i;j>0;j--){
             if (arr[j]<arr[j-1]){
                 swap(arr,j,j-1);
             }
         }
     }
    }
    public static void swap(int[] a,int i,int j){
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
}
