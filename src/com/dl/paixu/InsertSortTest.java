package com.dl.paixu;

import java.util.Arrays;

/**
 * 插入排序，从头取元素，放在合适的位置
 * 省去了交换时的中间变量，大的向右移动，最后空出位置就是该元素摆放的位置
 * 时间复杂度O(N平方)
 */
public class InsertSortTest {


    public static void main(String[] args) {
        int[] arr={2,34,12,25,7};
        insertSort(arr);
//        int[] ints = InsertSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
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

    public static int[] InsertSort2(int[] arr){
        for (int i=1;i<arr.length;i++){
            int insert=arr[i];
            int index=i-1;
            while (index>=0&&arr[index]>insert){
                arr[index+1]=arr[index];//右移
                index--;
            }
            arr[index+1]=insert;
        }
        return arr;
    }
}
