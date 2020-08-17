package com.dl.zuochengyun;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/8/17 14:22
 *
 * 给定一个不含有重复值的数组arr，找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置。返回所有位置相应的信息。
 * 1表示不存在。所以上面的结果表示在arr中，0位置左边和右边离0位置最近且值比arr[0]小的位置是-1和2；1位置左边和右边离1位置最近且值比arr[1]小的位置是0和2；2位置左边和右边离2位置最近且值比arr[2]小的位置是-1和-1……
 */
public class Demo08 {
    public int[][] rightWay(int[] arr){
        int[][] res=new int[arr.length][2];
        for (int i=0;i<arr.length;i++){
            int leftLessIndex=-1;
            int rightLessIndex=-1;
            int cur=i-1;
            while (cur >=0){
                if (arr[cur]<arr[i]){
                    leftLessIndex=cur;
                    break;
                }
                cur--;
            }
            cur++;
            while (cur<arr.length){
                if (arr[cur]<arr[i]){
                    rightLessIndex=cur;
                    break;
                }
                cur++;
            }
            res[i][0]=leftLessIndex;
            res[i][1]=rightLessIndex;
        }
        return res;
    }
}
