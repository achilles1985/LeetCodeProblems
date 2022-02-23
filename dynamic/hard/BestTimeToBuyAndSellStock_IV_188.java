package dynamic.hard;

/**H
 * Say you have an array for which the i-th element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 * Example 1:
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 *
 * Example 2:
 * Input: [3,2,6,5,0,3], k = 2
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 *              Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */
// https://www.youtube.com/watch?v=Pw6lrYANjz4
public class BestTimeToBuyAndSellStock_IV_188 {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock_IV_188 s = new BestTimeToBuyAndSellStock_IV_188();
        System.out.println(s.maxProfit3(2, new int[]{5,11,3,50,60,90})); //93
        System.out.println(s.maxProfit3(2, new int[]{2,4,1})); //2
        System.out.println(s.maxProfit3(3, new int[]{3,2,6,5,0,3})); //7
    }

    // O(n*k) - time, space
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int[][] dp = new int[k+1][n];
        for (int i = 1; i <= k; i++) {
            int localMax = dp[i-1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j-1],  prices[j] + localMax);
                localMax = Math.max(localMax, dp[i-1][j] - prices[j]);
            }
        }
        return dp[k][n-1];
    }

    // O(n*k) - time, O(n) - space
    // Since we use only 2 rows, it's reasonable to replace matrix with that 2 arrays.
    public int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        int[] oddProfile = new int[prices.length];
        int[] evenProfile = new int[prices.length];
        for (int i = 1; i <= k; i++) {
            int[] currentProfit;
            int[] previousProfit;
            if (i % 2 == 1) {
                currentProfit = oddProfile;
                previousProfit = evenProfile;
            } else {
                currentProfit = evenProfile;
                previousProfit = oddProfile;
            }
            int localMax = previousProfit[0] - prices[0];
            for (int j = 1; j < n; j++) {
                currentProfit[j] = Math.max(currentProfit[j-1],  prices[j] + localMax);
                localMax = Math.max(localMax, previousProfit[j-1] - prices[j]);
            }
        }
        return k%2 == 0 ? evenProfile[prices.length-1] : oddProfile[prices.length-1];
    }

    /*
       Reccurance relation:
       State: dp(i, k, h), i - day, k - transactions left, h - buy or sell
       At each step we have 2 choices: do nothing or buy/sell. For buy we can buy that day or do nothing, for sell is the same.
       Buy: dp(i,k,0)=dp(i+1,k,0) - price[i] or dp(i+1,k,0) in case do nothing
       Sell: dp(i,k,1)=dp(i+1,k-1,1) + price[i] or dp(i+1,k,1) in case do nothing
       dp(i,k,h)=max(doNothing, doSomething)
     */
    // O(n*k) - time, space
    public int maxProfit3(int k, int[] prices) {
        return helper(k, prices, new int[prices.length][k+1][2], 0, 0);
    }

    private int helper(int k, int[] prices, int[][][] dp, int i, int type) {
        if (k == 0 || i == prices.length) {
            return 0;
        }
        if (dp[i][k][type] != 0) {
            return dp[i][k][type];
        }
        int doNothing = helper(k, prices, dp, i+1, type);
        int doSomething;
        if (type == 0) { //buy
            doSomething = helper(k, prices, dp, i+1, 1) - prices[i];
        } else { //sell
            doSomething = helper(k-1, prices, dp, i+1, 0) + prices[i];
        }
        dp[i][k][type] = Math.max(doNothing, doSomething);

        return dp[i][k][type];
    }
}
