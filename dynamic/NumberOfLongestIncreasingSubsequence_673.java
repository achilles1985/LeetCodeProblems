package dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * M [TODO]
 * Given an unsorted array of integers, find the number of longest increasing subsequence.
 * <p>
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 * <p>
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is
 * 1, so output 5.
 * <p>
 * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 */
/*
    1. Max length of an array (for answer to be fit in 32-bit int)?
    2. increasing means 2,2 or 2,3? probably 2,2
 */
public class NumberOfLongestIncreasingSubsequence_673 {

    public static void main(String[] args) {
        NumberOfLongestIncreasingSubsequence_673 s = new NumberOfLongestIncreasingSubsequence_673();
        System.out.println(s.findNumberOfLIS(new int[]{1,3,5,4,7})); //2
        System.out.println(s.findNumberOfLIS(new int[]{2,2,2,2,2})); //5

        System.out.println(s.findNumberOfLIS2(new int[]{1,3,5,4,7})); //2
    }

    // incorrect
    public int findNumberOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0;
        int counter = 0;
        for (int i = 1; i <= nums.length; i++) {
            int[] maxToCount = new int[2];
            helper(nums, Integer.MIN_VALUE, 0, i, new HashMap<>(), maxToCount);
            int local = maxToCount[0];
            if (local > max) {
                max = local;
                counter = maxToCount[1];
            } else if (local == max) {
                counter += maxToCount[1];
            }
        }

        return counter;
    }

    private int helper(int[] nums, int prev, int curIdx, int endIdx, Map<String, Integer> cache, int[] maxToCount) {
        String key = prev + "," + curIdx;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (curIdx == endIdx) {
            return 0;
        }
        int taken = 0;
        if (prev <= nums[curIdx]) {
            taken = 1 + helper(nums, nums[curIdx], curIdx+1, endIdx, cache, maxToCount);
        }
        int nontaken = helper(nums, prev, curIdx+1, endIdx, cache, maxToCount);

        int max = Math.max(taken, nontaken);
        if (maxToCount[0] == 0) {
            maxToCount[0] = max;
            maxToCount[1] = 1;
        } else if (max > maxToCount[0]) {
            maxToCount[0] = max;
            maxToCount[1] = 1;
        } else if (max == maxToCount[0]) {
            maxToCount[1]++;
        }
        cache.put(key, max);

        return  max;
    }

    // O(n^2) - time, O(n) - space
    public int findNumberOfLIS(int[] nums) {
        int N = nums.length;
        if (N <= 1) {
            return N;
        }
        int[] lengths = new int[N]; //lengths[i] = length of longest ending in nums[i]
        int[] counts = new int[N]; //count[i] = number of longest ending in nums[i]
        Arrays.fill(counts, 1);

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < i; ++j)
                if (nums[i] > nums[j]) {
                    if (lengths[j] >= lengths[i]) {
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    } else if (lengths[j] + 1 == lengths[i]) {
                        counts[i] += counts[j];
                    }
                }
        }

        int longest = 0;
        for (int length : lengths) {
            longest = Math.max(longest, length);
        }
        int ans = 0;
        for (int i = 0; i < N; ++i) {
            if (lengths[i] == longest) {
                ans += counts[i];
            }
        }
        return ans;
    }
}
