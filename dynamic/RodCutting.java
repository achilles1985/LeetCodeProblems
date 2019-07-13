package dynamic;

/**
 * Given a rod of length n inches and a table of prices pi, i=1,2,…,n, write an algorithm to find the maximum revenue rn obtainable by cutting up the rod and selling the pieces.
 * Example:
 * length: 1,2,3,4
 * price: 2,5,7,8
 */
public class RodCutting {

    public static void main(String[] args) {
        RodCutting s = new RodCutting();
        System.out.println(s.profitBruteForce(new int[] {2,5,7,8}, 4));
        System.out.println(s.profitDynamicBottomUp(new int[] {2,5,7,8}, 4));
    }

    public int profitBruteForce(int[] prices, int length) {
        if (length <= 0) {
            return 0;
        }

        int max = 0;
        for (int i = 0; i < length; i++) {
            int localMax = profitBruteForce(prices, length-i-1) + prices[i];
            if (localMax > max) {
                max = localMax;
            }
        }

        return max;
    }

    public int profitDynamicBottomUp(int[] prices, int length) {
        int[][] res = new int[length+1][prices.length+1];
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= prices.length; j++) {
                if (j < i) {
                    res[i][j] = res[i-1][j];
                } else {
                    if (j%i == 0) {
                        res[i][j] = Math.max(res[i-1][j], (j/i)*prices[i-1]);
                    } else {
                        res[i][j] = Math.max(res[i-1][j], prices[i-1] + res[i-1][j-i]);
                    }
                }
            }
        }

        return res[length][prices.length];
    }

}
