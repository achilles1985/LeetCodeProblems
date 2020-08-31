package array.easy;

import java.util.Arrays;

// https://leetcode.com/problems/array-partition-i/
/** E [TODO]
 Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn)
 which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

 Example 1:
 Input: [1,4,3,2]
 Output: 4
 Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).

 Note:
 n is a positive integer, which is in the range of [1, 10000].
 All the integers in the array will be in the range of [-10000, 10000].
 */
/*
    Mistakes:
    1. Take care of duplicates
 */
public class ArrayPartitionI_561 {

    public static void main(String[] args) {
        ArrayPartitionI_561 s = new ArrayPartitionI_561();
        System.out.println(s.arrayPairSum2(new int[]{1,1})); // 1
        System.out.println(s.arrayPairSum2(new int[]{1,4,3,2})); // 4
        System.out.println(s.arrayPairSum2(new int[]{4,-4,3,2,-1,-2})); // -2
    }

    // O(n*log(n)) - time, O(1) - space
    public int arrayPairSumBF(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i+=2) {
            sum += nums[i];
        }

        return sum;
    }

    public int arrayPairSum2(int[] nums) {
        int limit = 10_000;
        int[] arr = new int[2*10000 + 1];
        Arrays.fill(arr, Integer.MIN_VALUE);
        for (int num: nums) {
            arr[num + limit] = num;
        }
        int prev = Integer.MIN_VALUE;
        int cur = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != Integer.MIN_VALUE) {
                prev = cur;
                cur = arr[i];
            }
            if (prev != Integer.MIN_VALUE && cur != Integer.MIN_VALUE) {
                sum += prev;
                prev = Integer.MIN_VALUE;
                cur = Integer.MIN_VALUE;
            }
        }

        return sum;
    }

    // O(n) - time, space (does not handle duplicates)
    public int arrayPairSum(int[] nums) {
        int[] arr = new int[20001];
        int lim = 10000;
        for (int num: nums) {
            arr[num + lim]++;
        }
        int d = 0, sum = 0;
        for (int i = -10000; i <= 10000; i++) {
            sum += (arr[i + lim] + 1 - d) / 2 * i;
            d = (2 + arr[i + lim] - d) % 2;
        }

        return sum;
    }
}
