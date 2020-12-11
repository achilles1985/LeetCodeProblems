package dynamic.medium;

import java.util.Arrays;

/** M
 * Given a rod of length n inches and a table of prices pi, i=1,2,…,n,
 * write an algorithm to find the maximum revenue rn obtainable by cutting up the rod and selling the pieces.
 *
 * Example:
 * length: 1,2,3,4
 * price: 2,5,7,8
 */
public class RodCutting {

    public static void main(String[] args) {
        RodCutting s = new RodCutting();
        System.out.println(s.profitBF(new int[] {2,5,7,8}, 4)); //10

        System.out.println(s.profitBF(new int[] {2,5,7,8}, 4)); //10
        System.out.println(s.profitTopDown(new int[] {2,5,7,8}, 4)); //10
        System.out.println(s.profitDynamicBottomUp(new int[] {2,5,7,8}, 4)); //10
    }

    // O(length^length) - time, O(length) - space
    public int profitBF(int[] prices, int length) {
        if (length <= 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < length; i++) {
            int localMax = profitBF(prices, length-i-1) + prices[i];
            max = Math.max(max, localMax);
        }
        return max;
    }

    // O(length^2) - time, O(length) - space
    public int profitTopDown(int[] prices, int length) {
        int[] cache = new int[length+1];
        cache[0] = 0;
        Arrays.fill(cache, -1);
        return profitTopDownHelper(prices, length, cache);
    }

    private int profitTopDownHelper(int[] prices, int length, int[] cache) {
        if (length < 0) {
            return 0;
        }
        if (cache[length] != -1) {
            return cache[length];
        }
        int max = 0;
        for (int i = 0; i < length; i++) {
            int localMax = profitTopDownHelper(prices, length-i-1, cache) + prices[i];
            max = Math.max(max, localMax);
        }
        cache[length] = max;

        return cache[length];
    }

    // O(length^2) - time, O(length) - space
    public int profitDynamicBottomUp(int[] prices, int length) {
        int max[] = new int[length+1];
        for(int i=1; i <= prices.length; i++){
            for(int j=i; j <= prices.length; j++){
                max[j] = Math.max(max[j], max[j-i] + prices[i-1]);
            }
        }
        return max[prices.length];
    }

    /*    public int profitBFS(int[] prices, int length) {
            Set<Integer> visited = new HashSet<>();
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{length, 0});
            int max = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size-- > 0) {
                    int[] entry = queue.poll();
                    int len = entry[0];
                    int profit = entry[1];
                    max = Math.max(profit, max);
                    for (int i = 0; i < length; i++) {
                        int newLen = len - i + 1;
                        int newProfit = profit + prices[i];
                        if (!visited.contains(newLen) && newLen >= 0) {
                            queue.add(new int[]{newLen, newProfit});
                            visited.add(newLen);
                        }
                    }
                }
            }
            return max;
        }*/
}
