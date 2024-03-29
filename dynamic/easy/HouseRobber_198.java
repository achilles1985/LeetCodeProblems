package dynamic.easy;

/** E
 You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and
 it will automatically contact the police if two adjacent houses were broken into on the same night.
 Given a list of non-negative integers representing the amount of money of each house,
 determine the maximum amount of money you can rob tonight without alerting the police.

 Example 1:
 Input: [1,2,3,1]
 Output: 4
 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 Total amount you can rob = 1 + 3 = 4.

 Example 2:
 Input: [2,7,9,3,1]
 Output: 12
 Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class HouseRobber_198 {

    public static void main(String[] args) {
        HouseRobber_198 s = new HouseRobber_198();
        System.out.println(s.robMemoization(new int[] {2,1,1,2})); // 4
        System.out.println(s.robMemoization(new int[] {1,2,3,1,2,5})); // 9
        System.out.println(s.robMemoization(new int[] {1,2,3,1})); // 4
        System.out.println(s.robMemoization(new int[] {2,7,9,3,1})); // 12
    }

    // O(n) - time, space
    public int robMemoization(int[] nums) {
        return helper(nums, nums.length-1,new int[nums.length]);
    }

    // O(n) - time, space
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }

        return dp[nums.length-1];
    }

    // O(n) - time, O(1) - space
    public int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        int third = second;
        for (int i = 2; i < nums.length; i++) {
            third = Math.max(nums[i] + first, second);
            first = second;
            second = third;
        }
        return third;
    }

    private int helper(int[] nums, int i, int[] dp) {
        if (i < 0) {
            return 0;
        }
        if (i == 0) {
            return nums[i];
        }
        if (i == 1) {
            return Math.max(nums[0], nums[1]);
        }
        int rob = nums[i] + helper(nums, i-2, dp);
        int notRob = helper(nums, i-1, dp);

        dp[i] = Math.max(rob, notRob);

        return dp[i];
    }
}
