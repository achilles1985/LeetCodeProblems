package dynamic;

import java.util.HashMap;
import java.util.Map;

/** M
 https://leetcode.com/problems/target-sum/
 You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 Find out how many ways to assign symbols to make sum of integers equal to target S.

 Example 1:
 Input: nums is [1, 1, 1, 1, 1], S is 3.
 Output: 5
 Explanation:

 -1+1+1+1+1 = 3
 +1-1+1+1+1 = 3
 +1+1-1+1+1 = 3
 +1+1+1-1+1 = 3
 +1+1+1+1-1 = 3

 There are 5 ways to assign symbols to make the sum of nums be target 3.

 Note:
 The length of the given array is positive and will not exceed 20.
 The sum of elements in the given array will not exceed 1000.
 Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class TargetSum_494 {

    public static void main(String[] args) {
        TargetSum_494 s = new TargetSum_494();
        System.out.println(s.findTargetSumWaysBruteForce(new int[] {1,1,1,1,1}, 3));
        System.out.println(s.findTargetSumWaysDynamicTopDown(new int[] {1,1,1,1,1}, 3));
    }

    // O(2^n) - time, n - size of nums, O(n) - space, the depth of the recursion tree ca go up to n.
    public int findTargetSumWaysBruteForce(int[] nums, int S) {
        return findTargetSumWaysBruteForce(nums, S, 0, 0);
    }

    public int findTargetSumWaysDynamicTopDown(int[] nums, int S) {
        Map<Key, Integer> map = new HashMap<>();
        return findTargetSumWaysDynamicTopDown(nums, S, 0, 0, map);
    }

    // O(n*target sum) - time, space
    private int findTargetSumWaysDynamicTopDown(int[] nums, int target, int i, int sum, Map<Key, Integer> map) {
        if (i == nums.length) {
            return target == sum ? 1 : 0;
        }
        Key key = new Key(i, sum);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int r1 = findTargetSumWaysDynamicTopDown(nums, target, i+1, sum+nums[i], map);
        int r2 = findTargetSumWaysDynamicTopDown(nums, target, i+1, sum-nums[i], map);
        map.put(key, r1+r2);

        return map.get(key);
    }

    private int findTargetSumWaysBruteForce(int[] nums, int target, int i, int sum) {
        if (i == nums.length) {
            return sum == target ? 1 : 0;
        }

        int s1 = findTargetSumWaysBruteForce(nums, target, i+1, sum + nums[i]);
        int s2 = findTargetSumWaysBruteForce(nums, target, i+1, sum - nums[i]);

        return s1 + s2;
    }

    private static class Key {
        int index;
        int sum;

        public Key(int index, int remainder) {
            this.index = index;
            this.sum = remainder;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (index != key.index) return false;
            return sum == key.sum;
        }

        @Override
        public int hashCode() {
            int result = index;
            result = 31 * result + sum;
            return result;
        }
    }
}
