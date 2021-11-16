package array.medium;

/** M [!marked]
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.

 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
/*
    Cadenesh algorithm
 */
public class MaxContiguousSubarray_53 {

    public static void main(String[] args) {
        MaxContiguousSubarray_53 s = new MaxContiguousSubarray_53();
        System.out.println(s.maxSubArrayBF2(new int[]{5,4,-1,7,8})); // 23
        System.out.println(s.maxSubArrayBF2(new int[]{-2,1,-3,4,-1,2,1,-5,4})); // 6
        System.out.println(s.maxSubArrayBF2(new int[]{1})); // 1
        System.out.println(s.maxSubArrayBF2(new int[]{-1})); // -1

        System.out.println(s.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})); // 6
        System.out.println(s.maxSubArray(new int[]{1})); // 1
        System.out.println(s.maxSubArray(new int[]{-1})); // -1
    }

    // O(n) - time, O(1) - space
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int maxSum = sum;
        for (int i = 1; i < nums.length; i++) {
            if (sum + nums[i] > nums[i]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            maxSum = Math.max(sum, maxSum);
        }

        return maxSum;
    }

    // O(n^2) - time, O(1) - space
    public int maxSubArrayBF(int[] nums) {
        int maxSum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    // O(n^3) - time, O(1) - space
    public int maxSubArrayBF2(int[] nums) {
        int maxSum = nums[0];
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= nums.length - i; j++) {
                int sum = 0;
                for (int k = j; k < j + i; k++) {
                    sum += nums[k];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }
}
