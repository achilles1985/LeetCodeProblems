package dynamic.medium;

/**M
 * You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
 *     Choose one integer x from either the start or the end of the array nums.
 *     Add multipliers[i] * x to your score.
 *     Remove x from the array nums.
 * Return the maximum score after performing m operations.
 *
 * Example 1:
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 *
 * Example 2:
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 *
 * Constraints:
 *     n == nums.length
 *     m == multipliers.length
 *     1 <= m <= 10^3
 *     m <= n <= 10^5
 *     -1000 <= nums[i], multipliers[i] <= 1000
 */
public class MaximumScoreFromPerformingMultiplicationOperations_1770 {

    public static void main(String[] args) {
        MaximumScoreFromPerformingMultiplicationOperations_1770 s = new MaximumScoreFromPerformingMultiplicationOperations_1770();
        System.out.println(s.maximumScore(new int[]{1,2,3}, new int[]{3,2,1})); //14
        System.out.println(s.maximumScore(new int[]{-5,-3,-3,-2,7,1}, new int[]{-10,-5,3,4,6})); //102
    }

    // O(m^2) - time, O(m^2) - space
    public int maximumScore(int[] nums, int[] multipliers) {
        int[][] dp = new int[multipliers.length][multipliers.length];
        return helper(dp, nums, multipliers, 0, nums.length-1, 0);
    }

    private int helper(int[][] map, int[] nums, int[] multipliers, int left, int right, int i) {
        if (i == multipliers.length) {
            return 0;
        }
        if (map[left][i] != 0) {
            return map[left][i];
        }

        int leftM = nums[left]*multipliers[i] + helper(map, nums, multipliers, left+1, right, i+1);
        int rightM = nums[right]*multipliers[i] + helper(map, nums, multipliers, left, right-1, i+1);
        int res = Math.max(leftM, rightM);
        map[left][i] = res;

        return res;
    }
}
