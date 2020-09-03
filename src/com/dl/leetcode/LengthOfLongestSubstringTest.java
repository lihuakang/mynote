package com.dl.leetcode;

import java.util.HashMap;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/9/1 15:06
 * 3 无重复字符串
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class LengthOfLongestSubstringTest {
    public static void main(String[] args) {
        int[] map=new int[256];
        map['p']=5;
        System.out.println('p'+0);
        System.out.println(map['p']);
    }
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s.length()==0) return 0;
            HashMap<Character, Integer> map = new HashMap<Character, Integer>();
            int max = 0;
            int left = 0;
            for(int i = 0; i < s.length(); i ++){
                if(map.containsKey(s.charAt(i))){
                    left = Math.max(left,map.get(s.charAt(i)) + 1);
                }
                map.put(s.charAt(i),i);
                max = Math.max(max,i-left+1);
            }
            return max;

        }
    }


}
