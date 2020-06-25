package com.dl.paixu;

import java.util.Arrays;

/**
 * 单轴快排
 * 选一个轴，把数组分成两个数组，左边比轴小，右边比轴大,在对两部分进行递归
 */
public class QuickSortTest {
    public static void main(String[] args) {
        int[] arr={2,3,14,7,17,9,6};
        sort1(arr,0,arr.length-1);
        print(arr);
    }

    public static void sort1(int[] arr, int leftBound, int rightBound){
    if (leftBound>=rightBound)return;
        int mid = partition(arr, leftBound, rightBound);
        sort1(arr,leftBound,mid-1);
        sort1(arr,mid+1,rightBound);
    }

    private static int partition(int[] arr, int leftBound, int rightBound) {
        //定义元素最后一个元素为轴
        int pivot=arr[rightBound];
        int left=leftBound;
        int right=rightBound-1;
        while (left<=right){
            while (left<=right && arr[left]<=pivot){left++;}//到比pivot大的数就停止
            while (left<=right && arr[right]>pivot){right--;} //到比piot小的数就停止
            if (left<right){
                swap(arr,left,right);
            }
        }
        swap(arr,left,rightBound);
        //这个left指的是在中间的轴
        return left;
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public static void print(int[] arr){
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
