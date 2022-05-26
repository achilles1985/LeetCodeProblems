package dynamic.medium;

/**M
 * You are given an array of binary strings strs and two integers m and n.
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 * A set x is a subset of a set y if all elements of x are also elements of y.
 *
 * Example 1:
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 * Output: 4
 * Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
 * Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
 * {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
 *
 * Example 2:
 * Input: strs = ["10","0","1"], m = 1, n = 1
 * Output: 2
 * Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 *
 * Constraints:
 *     1 <= strs.length <= 600
 *     1 <= strs[i].length <= 100
 *     strs[i] consists only of digits '0' and '1'.
 *     1 <= m, n <= 100
 */
public class OnesAndZeroes_474 {

    public static void main(String[] args) {
        OnesAndZeroes_474 s = new OnesAndZeroes_474();
        System.out.println(s.findMaxFormDP(new String[]{"10","0001","111001","1","0"},5,3)); //4
        System.out.println(s.findMaxFormDP(new String[]{"10","0","1"},1,1)); //2
    }

    // O(2^n) - time, O(n) - space
    public int findMaxForm(String[] strs, int m, int n) {
        return helper(strs,0,m,n);
    }

    // O(l*m*n) - time, space, l-arr length,m-zeros,n-ones
    public int findMaxFormDP(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length][m+1][n+1];

        return helperDP(strs,0,m,n,dp);
    }

    private int helperDP(String[] strs, int i, int zeros, int ones, int[][][] dp) {
        if (i == strs.length) {
            return 0;
        }
        if (dp[i][zeros][ones] != 0) {
            return dp[i][zeros][ones];
        }
        int taken = 0;
        int[] zerosOnes = countZerosAndOnes(strs[i]);
        if (zeros-zerosOnes[0] >= 0 && ones-zerosOnes[1] >= 0) {
            taken = helperDP(strs,i+1,zeros-zerosOnes[0],ones-zerosOnes[1],dp) + 1;
        }
        int notTaken = helperDP(strs,i+1,zeros,ones,dp);
        int res = Math.max(taken,notTaken);
        dp[i][zeros][ones] = res;

        return res;
    }

    private int helper(String[] strs, int i, int zeros, int ones) {
        if (i == strs.length) {
            return 0;
        }
        int taken = 0;
        int[] zerosOnes = countZerosAndOnes(strs[i]);
        if (zeros-zerosOnes[0] >= 0 && ones-zerosOnes[1] >= 0) {
            taken = helper(strs,i+1,zeros-zerosOnes[0],ones-zerosOnes[1]) + 1;
        }
        int notTaken = helper(strs,i+1,zeros,ones);

        return Math.max(taken,notTaken);
    }

    private int[] countZerosAndOnes(String str) {
        int[] arr = new int[2];
        for (char c: str.toCharArray()) {
            arr[c-'0']++;
        }
        return arr;
    }
}
