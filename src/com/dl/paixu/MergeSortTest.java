package com.dl.paixu;

import com.dl.jvmtest.A;

import java.util.Arrays;

/**
 * 归并排序采用分治法
 * 1 拆分原数组
 * 2 合并数组
 *
 * 每次创建一个临时数组
 */
public class MergeSortTest {
    public static void main(String[] args) {
        int[] arr={12,33,8,25,24,39};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr){
        sort(arr,0,arr.length-1);
    }

    private static void sort(int[] arr, int left, int right) {
        int mid=(left+right)/2;
        //当分到只剩到一个元素的时候，就会退出递归
        if (left>=right){
            return;
        }
        sort(arr,left,mid);
        sort(arr,mid+1,right);
        merge(arr,left,mid,right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int Actr=left;
        int Bctr=mid+1;
        int Cctr=0;
        int lenA=mid-left+1;
        int lenB=right-mid;
        //创建临时数组，长度为A和B的和
        int[] tmp=new int[right-left+1];
        //循环A  B中长度较短的长度次数的而二倍的次数，，长的剩下的不用循环
        while (Actr<=mid && Bctr<=right){
            if (arr[Actr]<arr[Bctr]){
                tmp[Cctr++]=arr[Actr];
                Actr++;
            }else {
                tmp[Cctr++]=arr[Bctr];
                Bctr++;
            }
        }
        //如果将左边的还有剩余，将左边剩余的递归
        while (Actr<=mid){
            tmp[Cctr++]=arr[Actr++];
        }
        //如果右边有剩余
        while (Bctr<=right){
            tmp[Cctr++]=arr[Bctr++];
        }
        //将临时数组更新到原来数组
        for (int i=0;i<tmp.length;i++){
            arr[left++]=tmp[i];
        }
    }
}
