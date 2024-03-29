package array.easy;

/** M
 Say you have an array for which the ith element is the price of a given stock on day i.
 Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

 Example 1:
 Input: [7,1,5,3,6,4]
 Output: 7
 Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.

 Example 2:
 Input: [1,2,3,4,5]
 Output: 4
 Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 engaging multiple transactions at the same time. You must sell before buying again.

 Example 3:
 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */

public class BestTimeToBuyAndSellStock_II_122 {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock_II_122 s = new BestTimeToBuyAndSellStock_II_122();
        System.out.println(s.maxProfit(new int[]{1,2,3,4,5})); //4
        System.out.println(s.maxProfit(new int[]{7,1,3,5,6,4})); //5
        System.out.println(s.maxProfitBF(new int[]{7,1,5,3,6,4})); //7
        System.out.println(s.maxProfit(new int[]{7,6,4,3,1})); //0
    }

    // O(n) - time, O(1) - space
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }

        return profit;
    }

    // All possible subsets of transactions
    // O(n^n) - time, O(n) - space, depth of recursion
    public int maxProfitBF(int[] prices) {
        return calculate(prices, 0);
    }

    private int calculate(int prices[], int s) {
        if (s >= prices.length) {
            return 0;
        }
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int maxprofit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    maxprofit = Math.max(maxprofit, profit);
                }
            }
            max = Math.max(max, maxprofit);
        }
        return max;
    }
}
