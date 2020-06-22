package com.dl.suanfa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 求两个数组的交集数组
 */
public class Test2 {
    static class Solution {
        public static int[] intersect(int[] nums1, int[] nums2) {
            HashMap<Integer,Integer> map=new HashMap();
            List<Integer> list=new ArrayList();
        for(int num:nums1){
            if (!map.containsKey(num)){
                map.put(num,1);
            }else {
                map.put(num,map.get(num)+1);
            }
        }
        for (int num:nums2){
            if (map.containsKey(num)){
                map.put(num,map.get(num)-1);
                if (map.get(num)==0){
                    map.remove(num);
                }
                list.add(num);
            }
        }
        int[] arr=new int[list.size()];
        for (int i=0;i<list.size();i++){
            arr[i]=list.get(i);
        }
        return arr;
        }
    }

    public static void main(String[] args) {
        int[] a={2,3,4,1,3};
        int[] b={2,3,5,9,33,11};
        int[] c=new int[2];
        int[] d=Solution.intersect(a,b);
        System.out.println(d.length);
    }
}
