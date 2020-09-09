package com.dl.suanfa.find;

import java.util.Arrays;

public class ErfenFind {
    public static void main(String[] args) {
    int[] arr={12,33,23,89,18};
        Arrays.sort(arr);
        int i = find(18, arr);
        System.out.println(i);
    }

    public static int find(int key,int[] arr){
        int lo=0;
        int hi=arr.length-1;
        while (lo<hi){
            int mid=lo+(hi-lo)/2;
            if (key<arr[mid]){
                hi=mid-1;
            }else if (key>arr[mid]){
                lo=mid+1;
            }else if (key==arr[mid]){
                return mid;
            }
        }
        return -1;
    }
}
