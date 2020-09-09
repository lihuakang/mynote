package com.dl.suanfa;

public class BubbleSelcct {

    public static void main(String[] args) {
        int[] arr={23,13,22,89,6,35};
        bubble(arr);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }


    public static void bubble(int[] arr){
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
}
