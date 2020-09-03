package com.dl.leetcode;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/9/1 16:22
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class FindMid {
    public class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // 保证nums1不是最长的，时间复杂度可转化为O(log(Min(m, n)))
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }

            int left = 0;
            int right = nums1.length;
            int halfLen = (nums1.length + nums2.length + 1) >> 1;

            while (left <= right) {
                int i = (left + right) >> 1; // nums1[i, nums1.length)为要分割的右半部分
                int j = halfLen - i; // nums2[j, nums2.length)为要分割的右半部分
                if (i < right && nums2[j - 1] > nums1[i]) { // nums1分割点此时需要右移
                    left++;
                } else if (i > left && nums1[i - 1] > nums2[j]) { // nums1 分割点此时需要左移
                    right--;
                } else {
                    int leftMax = (i == 0) ? nums2[j - 1] :
                            (j == 0 ? nums1[i - 1] : Math.max(nums1[i - 1], nums2[j - 1]));
                    if (((nums1.length + nums2.length) & 1) == 1) {
                        return leftMax * 1.0;
                    }
                    int rightMin = (i == nums1.length) ? nums2[j] :
                            (j == nums2.length ? nums1[i] : Math.min(nums1[i], nums2[j]));
                    return (leftMax + rightMin) / 2.0;
                }
            }
            return 0.0;
        }
    }

}
