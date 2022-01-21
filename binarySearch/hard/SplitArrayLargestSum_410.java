package binarySearch.hard;

import java.util.concurrent.atomic.AtomicInteger;

/** H [marked*]
 Given an array which consists of non-negative integers and an integer m, you can split the array
 into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

 Note:
 If n is the length of array, assume the following constraints are satisfied:
 1 ≤ n ≤ 1000
 1 ≤ m ≤ min(50, n)

 Examples:
 Input:
 nums = [7,2,5,10,8]
 m = 2
 Output:
 18

 Explanation:
 There are four ways to split nums into two subarrays.
 The best way is to split it into [7,2,5] and [10,8],
 where the largest sum among the two subarrays is only 18.
 */
/*
    Given searching range and a target value, it is natural to apply Binary Search.
    Finding lower bound
 */
public class SplitArrayLargestSum_410 {

    public static void main(String[] args) {
        SplitArrayLargestSum_410 s = new SplitArrayLargestSum_410();
        System.out.println(s.splitArrayBF2(new int[]{7,2,5,4,3,1}, 3)); // 7

        System.out.println(s.splitArrayBF2(new int[]{7,2,5,4,3,1}, 6)); // 7
        System.out.println(s.splitArrayBF2(new int[]{7,2,5,10,8}, 3)); // 14
        System.out.println(s.splitArrayBF2(new int[]{1,2147483647}, 2)); // 2147483647
    }

    public int splitArrayBF2(int[] nums, int m) {
        if (nums == null || nums.length == 0 || m < 0) {
            return 0;
        }
        AtomicInteger result = new AtomicInteger(Integer.MAX_VALUE);
        helper(nums, 0, 0, 0, m, 0, result);

        return result.intValue();
    }

    // O(n^m) - time, O(n) - space
    /* We can use depth-first search to generate all possible splitting plan. For each element in the array,
        we can choose to append it to the previous subarray or start a new subarray starting with that element (if the number of subarrays does not exceed m).
        The sum of the current subarray can be updated at the same time.*/
    private void helper(int[] nums, int i, int subArrays, int sum, int m, int maxSum, AtomicInteger min) {
        if (i == nums.length && subArrays == m) {
            if (maxSum < min.intValue()) {
                min.set(maxSum);
            }
            return;
        }
        if (i == nums.length) {
            return;
        }
        if (i > 0 ) {
            helper(nums, i + 1, subArrays, sum + nums[i], m, Math.max(sum + nums[i], maxSum), min);
        }
        if (subArrays < m) {
            helper(nums, i+1, subArrays+1, nums[i], m, Math.max(nums[i], maxSum), min);
        }
    }

    // O(n^m) - time, O(n) - space
    public int splitArrayBF(int[] nums, int m) {
        int n = nums.length;
        int[] presum = new int[n+1];

        for (int i = 1; i <= n; i++) {
            presum[i] += nums[i-1] + presum[i-1];
        }
        return dfs(0, m, nums, presum);
    }

    private int dfs(int start, int m, int[] nums, int[] presum) {
        if (m == 1) {
            return presum[nums.length] - presum[start];
        }
        int maxSum = Integer.MAX_VALUE;
        for (int i = start; i < nums.length-1; i++) {
            int l = presum[i+1] - presum[start];
            int rightIntervalMax = dfs(i+1, m-1, nums, presum);
            maxSum = Math.min(maxSum, Math.max(l, rightIntervalMax));
        }

        return maxSum;
    }

    // O(n*log(sum(n)-max(n))) - time, O(1) - space
    public int splitArray(int[] nums, int m) {
        int left = 0;
        int right = 0;
        for (int num: nums) {
            left = Math.max(left, num);
            right += num;
        }

        while (left < right) {
            int mid = left + (right-left)/2;
            if (isPossible(nums, m, mid)) {
                right = mid;
            } else {
                left = mid+1;
            }
        }

        return right;
    }

    private boolean isPossible(int[] nums, int m, int mid) {
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curSum + nums[i] <= mid) {
                curSum += nums[i];
            } else {
                m--;
                curSum = nums[i];
            }
            if (m <= 0) {
                return false;
            }
        }
        return true;
    }
}
