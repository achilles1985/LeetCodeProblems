package array.medium;

import java.util.HashMap;
import java.util.Map;

/** M [marked]
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 *
 * Example 1:
 * Input: nums = [1, -1, 5, -2, 3], k = 3
 * Output: 4
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 *
 * Example 2:
 * Input: nums = [-2, -1, 2, 1], k = 1
 * Output: 2
 * Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 *
 * Follow Up:
 * Can you do it in O(n) time?
 */
public class MaximumSizeSubarraySumEqualsK_325 {

    public static void main(String[] args) {
        MaximumSizeSubarraySumEqualsK_325 s = new MaximumSizeSubarraySumEqualsK_325();
        System.out.println(s.maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3)); //4
        System.out.println(s.maxSubArrayLen(new int[]{1, 0, -1}, -1)); //2
        System.out.println(s.maxSubArrayLen(new int[]{-2, -1, 2, 1}, 1)); //2
    }

    // O(n^2) - time, O(1) - space
    public int maxSubArrayLenBF(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }

        return max == Integer.MIN_VALUE ? 0 : max;
    }

    // O(n) - time, space
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                max = Math.max(max, i + 1);
            } else {
                if (map.containsKey(sum - k)) {
                    max = Math.max(max, i - map.get(sum-k));
                }
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
