package dynamic.medium;

/** M [marked]
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
/*
    Loop through the array, each time remember the max and min value for the previous product,
    the most important thing is to update the max and min value:
    we have to compare among max * A[i], min * A[i] as well as A[i], since this is product, a negative * negative could be positive.
 */
// https://leetcode.com/problems/maximum-product-subarray/
public class MaximumProductSubarray_152 {

    public static void main(String[] args) {
        MaximumProductSubarray_152 s = new MaximumProductSubarray_152();
        System.out.println(s.maxProduct(new int[] {-2,0,-1})); // 0
        System.out.println(s.maxProduct(new int[] {2,3,-2,4})); // 6

        System.out.println(s.maxProductDynamic(new int[] {2,3,-2,4})); // 6
        System.out.println(s.maxProductDynamic(new int[] {-2,3,-4})); // 24
        System.out.println(s.maxProductDynamic(new int[] {-2,0,-1})); // 0
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

    // O(n) - time, O(1) - space
    public int maxProductDynamic(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0], min = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            if (max > result) {
                result = max;
            }
        }
        return result;
    }

}
