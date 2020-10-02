package dynamic.medium;

import java.util.HashMap;
import java.util.Map;

/**M [MARKED]
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 *
 * Example 1:
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * Example 2:
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 *
 * Example 3:
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 * Note:
 * You can assume that
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 */
public class CoinChange2 {

    public static void main(String[] args) {
        CoinChange2 s = new CoinChange2();
        System.out.println(s.changeMemoization(5, new int[]{1,2,5}));
        System.out.println(s.changeMemoization(3, new int[]{2}));
        System.out.println(s.changeMemoization(10, new int[]{10}));
    }

    // O(2^amount) - time , O(amount) - space
    public int changeBF(int amount, int[] coins) {
        return changeBF(amount, coins, 0);
    }

    private int changeBF(int amount, int[] coins, int i) {
        if (amount < 0) {
            return 0; // if amount is negative by which means not valid - 0
        }
        if (amount == 0) {
            return 1; // we found exact change
        }
        if (i == coins.length && amount > 0) {
            return 0; // means coins over and n>0 so no solution
        }
        return changeBF(amount - coins[i], coins, i) + changeBF(amount, coins, i + 1); // include + exclude
    }

    // O(coins*amount) - time, space
    public int changeMemoization(int amount, int[] coins) {
        Map<String, Integer> cache = new HashMap<>();
        return changeMemoization(amount, coins, cache, 0);
    }

    private int changeMemoization(int amount, int[] coins, Map<String, Integer> cache, int i) {
        if (amount < 0) {
            return 0;
        }
        if (amount == 0) {
            return 1;
        }
        if (i == coins.length && amount > 0) {
            return 0;
        }
        String key = i+":"+amount;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int left = changeMemoization(amount, coins, cache, i+1);
        int right = changeMemoization(amount-coins[i], coins, cache, i);
        cache.put(key, left+right);

        return cache.get(key);
    }

    // O(coins*amount) - time, space
    public int changeDP2(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int j = 1; j <= coins.length; j++) {
            dp[j][0] = 1; // number ways of selecting for amount zero is 1
            for (int i = 1; i <= amount; i++) {
                dp[j][i] = dp[j - 1][i]; // exclude current coin
                if (i - coins[j - 1] >= 0) { // check if amount  >= to the current i`th coin
                    dp[j][i] += dp[j][i - coins[j - 1]]; // include current coin
                }
            }
        }
        return dp[coins.length][amount];
    }

    // O(coins*amount) - time, O(amount) - space
    public int changeDP1(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1]; // default 0 initialized
        dp[0] = 1; // if nothing to change not select any coin is one way
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= amount; j++) { // iterate j for all coins[i]
                dp[j] += dp[j - coins[i]]; // selecting the coins[i - 1] for amount j
            }
        }

        return dp[amount];
    }
}
