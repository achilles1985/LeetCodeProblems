package backtracking;

import java.util.HashMap;
import java.util.Map;

/**M
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 *
 * Note:
 *     The length of the array is in range [1, 20,000].
 *     The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
/*
    Sliding window cannot be used because an array might contain negative numbers, e.x. ([-1,-1,1], 1) - will return 0, but 1 must the the answer.
 */
public class SubarraySumEqualsK_560 {

    public static void main(String[] args) {
        SubarraySumEqualsK_560 s = new SubarraySumEqualsK_560();
        System.out.println(s.subarraySum2(new int[]{1,3,2,1,4,5}, 4)); //2 (1,3) (4)
        System.out.println(s.subarraySum(new int[]{-1,-1,1}, 1)); //1
        System.out.println(s.subarraySum(new int[]{1,2,1,2,1}, 3)); //4 (1,2), (2,1), (1,2), (2,1)
        System.out.println(s.subarraySum(new int[]{1}, 0)); //0
        System.out.println(s.subarraySum(new int[]{-1,-1,1}, 1)); //1
        System.out.println(s.subarraySum(new int[]{1,1,1}, 2)); //2 (1,1), (1,1)
    }

    // O(n^2) - time, O(1) - space
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // O(n) - time, space
    public int subarraySum2(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // Wrong since sliding window cannot be used here.
    public int subarraySum3(int[] nums, int k) {
        if (k == 0 && nums.length == 1) {
            return nums[0] == k ? 1 : 0;
        }
        int count = 0;
        int sum = 0;
        int right = 0;
        int left = 0;
        while (right < nums.length && left < nums.length) {
            while (right < nums.length && sum < k) {
                sum += nums[right++];
            }
            while (left < nums.length && sum > k) {
                sum -= nums[left++];
            }
            if (sum == k) {
                count++;
                sum -= nums[left++];
            }
        }
        return count;
    }
}
