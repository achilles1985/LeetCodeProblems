package dynamic.medium;

/**M
 * You have n dice and each die has k faces numbered from 1 to k.
 * Given three integers n, k, and target, return the number of possible ways (out of the kn total ways)
 * to roll the dice so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.
 *
 * Example 1:
 * Input: n = 1, k = 6, target = 3
 * Output: 1
 * Explanation: You throw one die with 6 faces.
 * There is only one way to get a sum of 3.
 *
 * Example 2:
 * Input: n = 2, k = 6, target = 7
 * Output: 6
 * Explanation: You throw two dice, each with 6 faces.
 * There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 *
 * Example 3:
 * Input: n = 30, k = 30, target = 500
 * Output: 222616187
 * Explanation: The answer must be returned modulo 109 + 7.
 *
 * Constraints:
 *     1 <= n, k <= 30
 *     1 <= target <= 1000
 */
public class NumberOfDiceRollsWithTargetSum_1155 {

    public static void main(String[] args) {
        NumberOfDiceRollsWithTargetSum_1155 s = new NumberOfDiceRollsWithTargetSum_1155();
        System.out.println(s.numRollsToTarget(1, 6, 3)); //1
        System.out.println(s.numRollsToTarget(2, 6, 7)); //6
        //System.out.println(s.numRollsToTarget(30,30,500)); //222616187
    }

    // O(n*target*k) - time, O(n*target) - space
    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];

        return helper(n, k, target, dp);
    }

    // O(n*target*k) - time, O(n*target) - space
    public int numRollsToTarget2(int n, int k, int target) {
        if (target > k * n) {
            return 0;
        }
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                int count = 0;
                for (int face = 1; face <= k; face++) {
                    if (j - face < 0) break;
                    count = (count + dp[i - 1][j - face]) % 1000_000_007;
                }
                dp[i][j] = count;
            }
        }
        return dp[n][target];
    }

   private int helper(int n, int k, int target, int[][] dp) {
        int mod = 1000_000_007;
        if (n == 0 && target == 0) {
            return 1;
        }
        if (n == 0 || target < 0) {
            return 0;
        }
        if (dp[n][target] != 0) {
            return dp[n][target];
        }
        int count = 0;
        for (int f = 1; f <= k; f++) {
            count = (count + helper(n - 1, k, target - f, dp)) % mod;
        }
        dp[n][target] = count;

        return count;
    }
}
