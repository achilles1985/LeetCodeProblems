package dynamic.medium;

/** M
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 *
 * Example 2:
 * Input: prices = [1]
 * Output: 0
 *
 * Constraints:
 *     1 <= prices.length <= 5000
 *     0 <= prices[i] <= 1000
 */
public class BestTimeToBuyAndSellStockWithCooldown_309 {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithCooldown_309 s = new BestTimeToBuyAndSellStockWithCooldown_309();
        System.out.println(s.maxProfit(new int[]{1,2,3,0,2})); //3
        System.out.println(s.maxProfit(new int[]{1})); //0
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
    public int maxProfit(int[] prices) {
        return helper(prices, new int[prices.length][2], 0, 0);
    }

    private int helper(int[] prices, int[][] dp, int i, int type) {
        if (i >= prices.length) {
            return 0;
        }
        if (dp[i][type] != 0) {
            return dp[i][type];
        }
        int doNothing = helper(prices, dp, i+1, type);
        int doSomething;
        if (type == 0) { //buy
            doSomething = helper(prices, dp, i+1, 1) - prices[i];
        } else { //sell
            doSomething = helper(prices, dp, i+2, 0) + prices[i];
        }
        dp[i][type] = Math.max(doNothing, doSomething);

        return dp[i][type];
    }
}
