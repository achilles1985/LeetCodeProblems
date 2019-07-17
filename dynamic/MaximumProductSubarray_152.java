package dynamic;

/** M
 Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

 Example 1:
 Input: [2,3,-2,4]
 Output: 6
 Explanation: [2,3] has the largest product 6.

 Example 2:
 Input: [-2,0,-1]
 Output: 0
 Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
// https://leetcode.com/problems/maximum-product-subarray/
public class MaximumProductSubarray_152 { // ???

    public static void main(String[] args) {
        MaximumProductSubarray_152 s = new MaximumProductSubarray_152();
        System.out.println(s.maxProduct(new int[] {2,3,-2,4})); // 6
        System.out.println(s.maxProductDynamic(new int[] {2,3,-2,4})); // 6
        System.out.println(s.maxProductDynamic2(new int[] {2,3,-2,4})); // 6

        System.out.println(s.maxProduct(new int[] {-2,0,-1})); // 0
        System.out.println(s.maxProductDynamic(new int[] {-2,0,-1})); // 0
        System.out.println(s.maxProductDynamic2(new int[] {-2,0,-1})); // 0
    }

    // O(n^2) - time, O(1) - space
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int local = 1;
            for (int j = i; j < nums.length; j++) {
                local *= nums[j];
                if (local > max) {
                    max = local;
                }
            }
        }

        return max;
    }

    // O(n) - time, space
    public int maxProductDynamic(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i]*dp[i-1]) {
                dp[i] = nums[i];
            } else {
                dp[i] = nums[i]*dp[i-1];
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    // O(n) - time, O(1) - space
    public int maxProductDynamic2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int localMax = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i]*localMax) {
                localMax = nums[i];
            } else {
                localMax = nums[i]*localMax;
            }
            max = Math.max(max, localMax);
        }

        return max;
    }
}
