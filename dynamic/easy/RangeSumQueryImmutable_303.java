package dynamic.easy;

// https://leetcode.com/problems/range-sum-query-immutable/

/** E
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 Example:
 Given nums = [-2, 0, 3, -5, 2, -1]
 sumRange(0, 2) -> 1
 sumRange(2, 5) -> -1
 sumRange(0, 5) -> -3

 Note:
 You may assume that the array does not change.
 There are many calls to sumRange function.
 */
public class RangeSumQueryImmutable_303 {

    public static void main(String[] args) {
        NumArray s = new NumArray(new int[] {-2,0,3,-5,2,-1});
        System.out.println(s.sumRange(2,5)); // -1
    }

    // O(n) - time for precomputation, O(1) - time successive call, O(n) - space
    private static class NumArray {
        private int[] dp;

        public NumArray(int[] nums) {
            dp = new int[nums.length + 1];
            // precomputation
            for (int i = 0; i < nums.length; i++) {
                dp[i+1] = dp[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return dp[j+1] - dp[i];
        }
    }

}
