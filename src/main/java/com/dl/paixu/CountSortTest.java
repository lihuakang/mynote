package com.dl.paixu;

import sun.plugin.javascript.navig.Array;

import java.util.Arrays;

public class CountSortTest {
    public static void main(String[] args) {
        int[] arr={0,2,9,7,8,9,4,3,5,0,1,1};//取值范围0-9
        int[] result = sort(arr);
        System.out.println(Arrays.toString(result));
    }

    public static int[] sort(int[] arr){
        int[] result=new int[arr.length];
        int[] count=new int[10];
        for (int i=0;i<arr.length;i++){
            count[arr[i]]++;
        }
        System.out.println(Arrays.toString(count));
        for (int i=0,j=0;i<count.length;i++){
            while (count[i]-->0){
                result[j++]=i;
            }
        }
        return result;
    }
}
