package dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
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
public class LongestIncreasingSubsequence_300 {

    public static void main(String[] args) {
        LongestIncreasingSubsequence_300 s = new LongestIncreasingSubsequence_300();
        System.out.println(s.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // 4
        System.out.println(s.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 4})); // 3
        System.out.println(s.lengthOfLISDynamicTopDown(new int[]{10, 9, 2, 5, 3, 4})); // 3
        System.out.println(s.lengthOfLISDynamicBottomUp(new int[]{-1, 3, 4, 5, 2, 2,2,2})); // 5
    }

    // Brute Force, O(2^n), O(n^2) - space
    public int lengthOfLIS(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int localMax = lengthOfLIS(nums, i, i+1);
            max = Math.max(max, localMax);
        }
        return max;
    }

    // O(n^2) - time, O(n^2) - space
    public int lengthOfLISDynamicTopDown(int[] nums) {
        int max = 0;
        Map<Key, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int localMax = lengthOfLISDynamicTopDownUtils(nums, i, i+1, map);
            max = Math.max(max, localMax);
        }
        return max;
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

    private int lengthOfLISDynamicTopDownUtils(int[] nums, int i, int j, Map<Key, Integer> map) {
        if (j == nums.length) {
            return 1;
        }
        Key key = new Key(i, j);
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int taken = 0;
        if (nums[j] > nums[i]) {
            taken = 1 + lengthOfLISDynamicTopDownUtils(nums, j, j+1, map);
        }
        int notaken = lengthOfLISDynamicTopDownUtils(nums, i, j+1, map);

        int res = Math.max(taken, notaken);
        map.put(key, res);

        return map.get(key);
    }

    private int lengthOfLIS(int[] nums, int i, int j) {
        if (j == nums.length) {
            return 1;
        }

        int taken = 0;
        if (nums[j] > nums[i]) {
            taken = 1 + lengthOfLIS(nums, j, j+1);
        }
        int nottaken = lengthOfLIS(nums, i, j+1);

        return Math.max(taken, nottaken);
    }

    private static class Key {
        int i;
        int j;

        public Key(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (i != key.i) return false;
            return j == key.j;
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }
}
