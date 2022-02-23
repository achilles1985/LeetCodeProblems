package dynamic.medium;

/** M
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 * Input: prices = [1,3,2,8,4,9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[3] = 8
 * - Buying at prices[4] = 4
 * - Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * Example 2:
 * Input: prices = [1,3,7,5,10,3], fee = 3
 * Output: 6
 *
 * Constraints:
 *     1 <= prices.length <= 5 * 104
 *     1 <= prices[i] < 5 * 104
 *     0 <= fee < 5 * 104
 */
public class BestTimeToBuyAndSellStockWithTransactionFee_714 {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithTransactionFee_714 s = new BestTimeToBuyAndSellStockWithTransactionFee_714();
        System.out.println(s.maxProfit(new int[]{1,3,2,8,4,9},2)); //8
        System.out.println(s.maxProfit(new int[]{1,3,7,5,10,3},3)); //6
    }

    /*
   Reccurance relation:
   State: dp(i, h), i - day, h - buy or sell
   At each step we have 2 choices: do nothing or buy/sell. For buy we can buy that day or do nothing, for sell is the same.
   Buy: dp(i,0)=dp(i+1,0) - price[i] or dp(i+1,0) in case do nothing
   Sell: dp(i,1)=dp(i+1,1) + price[i] or dp(i+1,1) in case do nothing
   dp(i,h)=max(doNothing, doSomething)
 */
    // O(n*k) - time, space
    public int maxProfit(int[] prices, int fee) {
        return helper(fee, prices, new int[prices.length][2], 0, 0);
    }

    private int helper(int fee, int[] prices, int[][] dp, int i, int type) {
        if (i == prices.length) {
            return 0;
        }
        if (dp[i][type] != 0) {
            return dp[i][type];
        }
        int doNothing = helper(fee, prices, dp, i+1, type);
        int doSomething;
        if (type == 0) { //buy
            doSomething = helper(fee, prices, dp, i+1, 1) - prices[i];
        } else { //sell
            doSomething = helper(fee, prices, dp, i+1, 0) + prices[i] - fee;
        }
        dp[i][type] = Math.max(doNothing, doSomething);

        return dp[i][type];
    }
}
