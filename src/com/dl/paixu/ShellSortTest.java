package com.dl.paixu;

import java.util.Arrays;

/**
 * 希尔排序
 * 最开始增量时数组长度的一半，然后对各个分组进行插入排序，再讲增量除以2，再进行插入插叙，到最后为1时，在进行插入排序
 * 时间复杂度，最坏情况O(n的平方)
 */
public class ShellSortTest {
//    public static void shellSort(int[] arrays){
//        //每次增量除以二
//        for (int step= arrays.length/2;step>0;step/=2){
//            //从增量那组开始插入排序
//            for (int i=step;i<arrays.length;i++){
//                int j=i;
//                int temp=arrays[j];
//                //j-step代表与它同组隔壁的元素
//                while (j-step>=0 && arrays[j-step]>temp){
//                    arrays[j]=arrays[j-step];
//                    j=j-step;
//                }
//                arrays[j]=temp;
//            }
//        }
//        for (int a:arrays){
//            System.out.println(a);
//        }
//    }

    public static void main(String[] args) {
        int[] arr={2,45,23,1,25,8};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr){
        for (int gap=arr.length/2;gap>0;gap/=2){
        for (int i=gap;i<arr.length;i++){
            for (int j=i;j>gap-1;j-=gap){
                //插入排序
                if (arr[j]<arr[j-gap]) {
                    swap(arr, j, j - gap);
                }
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
