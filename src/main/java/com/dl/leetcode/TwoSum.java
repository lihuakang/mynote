package com.dl.leetcode;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;

/**
 * 从一个数组中选出两个数相加等于目标值，返回两个数的下标
 * 给定 nums = [2, 7, 11, 15], target = 9
 * //
 * //因为 nums[0] + nums[1] = 2 + 7 = 9
 * //所以返回 [0, 1]
 */
public class TwoSum {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int[] res=new int[2];
        if (nums==null|| nums.length<=1)return null;
            HashMap<Integer,Integer> map=new HashMap<>();
            for (int i=0;i<nums.length;i++){
                int num=nums[i];
                int val=target-num;
                if (map.containsKey(val)){
                    res[0]=i;
                    res[1]=map.get(val);
                    return res;
                }else map.put(num,i);
            }
            return res;
        }
    }
}
