package com.dl.suanfa;

import java.util.*;

/*找出数组中的幸运数 显示英文描述
        在整数数组中，如果一个整数的出现频次和它的数值大小相等，我们就称这个整数为「幸运数」。

        给你一个整数数组 arr，请你从中找出并返回一个幸运数。

        如果数组中存在多个幸运数，只需返回 最大 的那个。
        如果数组中不含幸运数，则返回 -1 。


        示例 1：

        输入：arr = [2,2,3,4]
        输出：2
        解释：数组中唯一的幸运数是 2 ，因为数值 2 的出现频次也是 2 。
        示例 2：

        输入：arr = [1,2,2,3,3,3]
        输出：3
        解释：1、2 以及 3 都是幸运数，只需要返回其中最大的 3 。
        示例 3：

        输入：arr = [2,2,2,3,3]
        输出：-1
        解释：数组中不存在幸运数。
        示例 4：

        输入：arr = [5]
        输出：-1
        示例 5：

        输入：arr = [7,7,7,7,7,7,7]
        输出：7

 */
public class Test4 {
    static class Solution {
        public static int findLucky(int[] arr) {
            Set set=new HashSet();
            List<Integer> list=new ArrayList();
        Map<Integer,Integer> map=new HashMap<>();
        map.put(11111,0);
        for (int i=0;i<arr.length;i++){
            Iterator<Integer> iterator = map.keySet().iterator();
            if (iterator.hasNext()){
                Integer next = iterator.next();
                if (next==arr[i]){
                    map.put(arr[i],map.get(arr[i])+1);
                }else {
                    map.put(arr[i],0);
                }
            }
        if (map.get(arr[i])==arr[i]){
            list.add(arr[i]);
        }
        }
        if (list==null||list.equals(null)||list.size()==0){
            return -1;
        }else if (list.size()==1){
            return list.get(0);
        }
        else{
            Integer max = Collections.max(list);
            return max;
        }
        }
    }

    public static void main(String[] args) {
        int[] arr={1,2,2,3,3,3};
        int lucky = Solution.findLucky(arr);
        System.out.println(lucky);
    }
}
