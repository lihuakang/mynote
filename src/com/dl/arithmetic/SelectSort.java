package com.dl.arithmetic;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/8/31 14:25
 * 选择排序，
 * 找到最小的和0位置交换，在找到最小的和1位置交换。。。
 * 时间复杂度  n^2
 */
public class SelectSort {
    public static void main(String[] args) throws InterruptedException {
    int[] arr={12,33,25,64,6,34,88,13};
    Thread.sleep(100000);
    sort(arr);
    for (int i=0;i<arr.length;i++){
        System.out.println(arr[i]);
    }
    }
    public static void sort(int[] arr){
        for (int i=0;i<arr.length-1;i++){
            int minIndex=i;

            for (int j=i+1;j<arr.length;j++){
                if (arr[j]<arr[minIndex]){

                    minIndex=j;
                }
            }
            if (i!=minIndex) {
                swap(arr, i, minIndex);
            }
        }
    }

    private static void swap(int[] arr, int i, int minIndex) {
        int temp=arr[i];
        arr[i]=arr[minIndex];
        arr[minIndex]=temp;
    }
}
