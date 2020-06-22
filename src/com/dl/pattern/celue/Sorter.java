package com.dl.pattern.celue;

public class Sorter {

    public static void sort(int[] arr){
        for (int i=0;i<arr.length;i++){
            int minIndex=i;
            for (int j=i+1;j<arr.length;j++){
                if (arr[j]<arr[minIndex]){
                    minIndex=j;
                }
            }
            if (i!=minIndex){
                swap(arr,i,minIndex);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
