package com.dl.paixu;

import java.util.Arrays;

public class QuickSort2 {
    public static void main(String[] args) {
        int[] arr={12,2,3,56,6,10};
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int leftBound, int rightBound) {
        if (leftBound>=rightBound)return;
        int mid = partition(arr, leftBound, rightBound);
        sort(arr,leftBound,mid-1);
        sort(arr,mid+1,rightBound);
    }

    private static int partition(int[] arr, int leftBound, int rightBound) {
        int left=leftBound;
        int right=rightBound-1;
        int pivot=arr[rightBound];
        while (left<=right){
            while (left<=right && arr[left]<=pivot)left++;
            while (left<=right && arr[right]>pivot)right--;
            if (left<right){
                swap(arr,left,right);
            }
        }
        swap(arr,left,rightBound);
        return left;
    }

    private static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
