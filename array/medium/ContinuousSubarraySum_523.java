package array.medium;

import java.util.HashMap;
import java.util.Map;

/** M
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
 *
 * Example 1:
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 *
 * Example 2:
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 *
 * Constraints:
 *     The length of the array won't exceed 10,000.
 *     You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
/*
 Questions: k can be 0 or negative?
 */
public class ContinuousSubarraySum_523 {

    public static void main(String[] args) {
        ContinuousSubarraySum_523 s = new ContinuousSubarraySum_523();
        System.out.println(s.checkSubarraySum2(new int[]{0,0}, 0)); // true
        System.out.println(s.checkSubarraySum2(new int[]{23,2,6,4,7}, 6)); // true
        System.out.println(s.checkSubarraySum2(new int[]{23,2,4,6,7}, 6)); // true
    }

    // O(n^2) - time, O(1) - space
    public boolean checkSubarraySum(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k || (k != 0 && sum%k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // O(n) - time, space
    public boolean checkSubarraySum2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1 ) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }
}
