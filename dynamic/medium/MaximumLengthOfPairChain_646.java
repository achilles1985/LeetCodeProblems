package dynamic.medium;

import java.util.Arrays;

/**M
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c.
 * Chain of pairs can be formed in this fashion.
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs.
 * You can select pairs in any order.
 *
 * Example 1:
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 * Note:
 * The number of given pairs will be in the range [1, 1000].
 */
public class MaximumLengthOfPairChain_646 {

    public static void main(String[] args) {
        MaximumLengthOfPairChain_646 s = new MaximumLengthOfPairChain_646();
        System.out.println(s.findLongestChain(new int[][]{new int[]{1,2}, new int[]{2,3}, new int[]{3,4}})); //2
    }

    // O(2^n) - time, space
    public int findLongestChainBF(int[][] pairs) {
        if (pairs == null || pairs.length == 0) {
            return 0;
        }
        Arrays.sort(pairs, (p1,p2)->p1[0]-p2[0]);
        return helper(pairs, Integer.MIN_VALUE, 0);
    }

    // O(n^2) - time, O(n) - space
    public int findLongestChain(int[][] pairs) {
        if (pairs == null) {
            return 0;
        }
        if (pairs.length < 2) {
            return pairs.length;
        }
        Arrays.sort(pairs, (p1,p2)->p1[0]-p2[0]);
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    private int helper(int[][] pairs, int prevVal, int curIdx) {
        if (curIdx == pairs.length) {
            return 0;
        }
        int taken = 0;
        if (pairs[curIdx][0] > prevVal ) {
            taken = 1 + helper(pairs, pairs[curIdx][1], curIdx+1);
        }
        int notTaken = helper(pairs, prevVal, curIdx+1);

        return Math.max(taken, notTaken);
    }
}
