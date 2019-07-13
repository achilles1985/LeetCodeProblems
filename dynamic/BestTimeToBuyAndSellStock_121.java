package dynamic;

public class BestTimeToBuyAndSellStock_121 {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock_121 s = new BestTimeToBuyAndSellStock_121();
        System.out.println(s.maxProfit(new int[] {7,1,5,3,6,4})); // 5
        System.out.println(s.maxProfit(new int[] {7,6,4,3,1})); // 0
    }

    // O(n) - time, O(1) - space
    public int maxProfit(int[] prices) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                max = Math.max(max, prices[i] - min);
            }
        }

        return max;
    }
}
