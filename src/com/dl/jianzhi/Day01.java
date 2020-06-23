package com.dl.jianzhi;

/**
 * 给定一个二位数组，从左到右从上到下，依次增大，然后给定一个数看是否包含在数组中。
 *
 * 思路：去跟右上角的数比较，比他大x++,比他小y--
 */
public class Day01 {
    private static int[][] array={
            {1,2,8,9},
            {2,4,9,12},
            {4,7,10,13},
            {6,8,11,15}
    };

    private static boolean contains(int key,int[][] array){
        int x=0;
        int y=array[0].length-1;
        int value=array[x][y];
        while (value!=key){
            if (x>=array.length|| y<=0){
                return false;
            }
            if (key>value){
                x++;
            }
            if (key<value){
                y--;
            }
            value=array[x][y];
        }
        if (value==key){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        boolean b = contains(7, array);
        System.out.println(b);
    }
}
