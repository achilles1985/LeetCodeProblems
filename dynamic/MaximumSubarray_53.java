package dynamic;

// https://leetcode.com/problems/maximum-subarray/

import java.util.ArrayList;
import java.util.List;

/** E
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 Example:
 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.

 Follow up:
 If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray_53 {

    public static void main(String[] args) {
        MaximumSubarray_53 s = new MaximumSubarray_53();
        System.out.println(s.maxSubArray(new int[] {1,2})); // [1] 1
        System.out.println(s.maxSubArray(new int[] {1,2,3,4})); // [4,-1,2,1] 6
        System.out.println(s.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4})); // [4,-1,2,1] 6
        System.out.println(s.maxSubArray2(new int[] {-2,1,-3,4,-1,2,1,-5,4})); // [4,-1,2,1] 6
        System.out.println(s.maxSubArrayDynamic(new int[] {-2,1,-3,4,-1,2,1,-5,4})); // [4,-1,2,1] 6
    }

    // O(n^2) - time, O(1) - space
    public int maxSubArray2(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            max = Math.max(max, sum);
            for (int j = i+1; j < nums.length; j++) {
                sum += nums[j];
                max = Math.max(max, sum);
            }
        }

        return max;
    }

    // O(n) time, O(1) - space
    public int maxSubArrayDynamic(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > sum + nums[i]) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }

            max = Math.max(max, sum);
        }

        return max;
    }

    // Incorrect, but useful traversing, Brute force, O(n^3) - time, O(1) - space
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int localSum = nums[i];
                max = Math.max(max, localSum);
                for (int k = i+1; k < j; k++) {
                    localSum += nums[k];
                }
                max = Math.max(max, localSum);
            }
        }
        return max;
    }
}
