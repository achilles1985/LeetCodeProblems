package array.easy;

/** E
 Say you have an array for which the ith element is the price of a given stock on day i.
 If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 Note that you cannot sell a stock before you buy one.

 Example 1:
 Input: [7,1,5,3,6,4]
 Output: 5
 Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 Not 7-1 = 6, as selling price needs to be larger than buying price.

 Example 2:
 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeToBuyAnsSellStock_121 {

    public static void main(String[] args) {
        BestTimeToBuyAnsSellStock_121 s = new BestTimeToBuyAnsSellStock_121();
        System.out.println(s.maxProfit(new int[]{7,1,5,3,6,4})); //5
        System.out.println(s.maxProfit(new int[]{7,6,4,3,1})); // 0

        System.out.println(s.maxProfitDP(new int[]{7,1,5,3,6,4})); //5
        System.out.println(s.maxProfitDP(new int[]{7,6,4,3,1})); // 0
    }

    // O(n^2) - time, O(1) - space
    public int maxProfitBF(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            int min = prices[i];
            int max = min;
            for (int j = i+1; j < prices.length; j++) {
                max = Math.max(max, prices[j]);
            }
            profit = Math.max(profit, max-min);
        }

        return profit;
    }

    // O(n) - time, O(1) - space
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }

    // O(n) - time, O(n) - space
    public int maxProfitDP(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int size = prices.length-1;
        int[] dp = new int[prices.length];
        dp[size] = prices[size];

        for (int i = size-1; i >= 0; i--) {
            dp[i] = Math.max(prices[i], dp[i+1]);
        }
        int maxProfit = 0;
        for (int i = 0; i <= size; i++) {
            int profit = dp[i] - prices[i];
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }

}
