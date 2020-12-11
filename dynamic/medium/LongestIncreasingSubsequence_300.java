package dynamic.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/** M [marked]
 Given an unsorted array of integers, find the length of longest increasing subsequence.
 Example:
 Input: [10,9,2,5,3,7,101,18]
 Output: 4
 Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

 Note:
 There may be more than one LIS combination, it is only necessary for you to return the length.
 Your algorithm should run in O(n2) complexity.
 Follow up: Could you improve it to O(n log n) time complexity?
 */
/*
Strategy: max(take, notake)
 */
public class LongestIncreasingSubsequence_300 {

    public static void main(String[] args) {
        LongestIncreasingSubsequence_300 s = new LongestIncreasingSubsequence_300();
        System.out.println(s.lengthOfLISDynamicBottomUp(new int[]{1,2,3,4})); // 4
        System.out.println(s.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18})); // 4
        System.out.println(s.lengthOfLISDynamicTopDown(new int[]{10,9,2,5,3,7,101,18})); // 4
        System.out.println(s.lengthOfLISDynamicBottomUp(new int[]{10,9,2,5,3,7,101,18})); // 4

        System.out.println(s.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 4})); // 3
        System.out.println(s.lengthOfLISDynamicBottomUp(new int[]{-1, 3, 4, 5, 2, 2,2,2})); // 5
    }

    // Brute Force, O(2^n), O(n) - space, recursion stack
    public int lengthOfLIS(int[] nums) {
        return lengthOfLIS(nums, Integer.MIN_VALUE, 0);
    }

    // O(n^2) - time, O(n^2) - space (memo[prevIdx][curIdx])
    public int lengthOfLISDynamicTopDown(int[] nums) {
        Map<String, Integer> cache = new HashMap<>();
        return lengthOfLISDynamicTopDownUtils(nums, Integer.MIN_VALUE, 0, cache);
    }

    // O(n^2) - time, O(n) - space
    public int lengthOfLISDynamicBottomUp(int[] nums) {
        int max = 1;
        int[] cache = new int[nums.length];
        Arrays.fill(cache, 1);
        for (int j = 1; j < nums.length; j++) {
            for (int i = 0; i < j; i++) {
                if (nums[j] > nums[i]) {
                    cache[j] = Math.max(cache[j], cache[i] + 1);
                }
                max = Math.max(cache[j], max);
            }
        }

        return max;
    }

    // O(n*log(n)) - time, O(n) - space
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    private int lengthOfLIS(int[] nums, int prevValue, int currIdx) {
        if (currIdx == nums.length) {
            return 0;
        }
        int taken = 0;
        if (prevValue < nums[currIdx]) {
            taken = 1 + lengthOfLIS(nums, nums[currIdx], currIdx + 1);
        }
        int nottaken = lengthOfLIS(nums, prevValue, currIdx + 1);

        return Math.max(taken, nottaken);
    }

    private int lengthOfLISDynamicTopDownUtils(int[] nums, int prevValue, int currIdx, Map<String, Integer> map) {
        String key = prevValue + ":" + currIdx;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (currIdx == nums.length) {
            return 0;
        }

        int taken = 0;
        if (prevValue < nums[currIdx]) {
            taken = 1 + lengthOfLISDynamicTopDownUtils(nums, nums[currIdx], currIdx+1, map);
        }
        int notaken = lengthOfLISDynamicTopDownUtils(nums, prevValue, currIdx+1, map);

        int res = Math.max(taken, notaken);
        map.put(key, res);

        return map.get(key);
    }

}
