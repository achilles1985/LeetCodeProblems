package dynamic;

import java.util.Arrays;

/**
  You are given coins of different denominations and a total amount of money amount.
  Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

  Example 1:
  Input: coins = [1, 2, 5], amount = 11
  Output: 3
  Explanation: 11 = 5 + 5 + 1

  Example 2:
  Input: coins = [2], amount = 3
  Output: -1

  Note:
  You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange s = new CoinChange();
        System.out.println(s.makeChange(new int[] {1,2,5}, 12));
        System.out.println(s.makeChangeDynamicTopDown(new int[] {1,2,5}, 12));
        System.out.println(s.makeChangeDynamicButtomUp(new int[] {1,2,5}, 12));
    }

    // exponential time (C^A), C - number of coins, A = amount
    public int makeChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int coin: coins) {
            if (amount - coin >= 0) {
                int curMin = makeChange(coins, amount-coin);
                if (curMin < min) {
                    min = curMin;
                }
            }
        }
        return min + 1;
    }

    // O(amount*number of coins) - time, O(amount) - space
    public int makeChangeDynamicTopDown(int[] coins, int amount) {
        int[] cache = new int[amount+1];
        Arrays.fill(cache, -1);

        return makeChangeDynamicTopDownUtils(coins, amount, cache);
    }

    // O(amount*number of coins) - time, O(amount) - space
    public int makeChangeDynamicButtomUp(int[] coins, int amount) {
        int[] cache = new int[amount+1];
        Arrays.fill(cache, Integer.MAX_VALUE);
        cache[0] = 0;

        return makeChangeDynamicButtomUpUtils(coins, amount, cache);
    }

    private int makeChangeDynamicButtomUpUtils(int[] coins, int amount, int[] cache) {
        for (int i = 1; i <= amount; i++) {
            for (int coin: coins) {
                if ((i - coin) >= 0) {
                    int min = Math.min(cache[i-coin] + 1, cache[i]);
                    cache[i] = min;
                }
            }
        }

        return cache[amount];
    }

    private int makeChangeDynamicTopDownUtils(int[] coins, int amount, int[] cache) {
        if (amount == 0) {
            return 0;
        }

        if (cache[amount] != -1) {
            return cache[amount];
        }

        int min = Integer.MAX_VALUE;
        for (int coin: coins) {
            if (amount - coin >= 0) {
                int curMin = makeChange(coins, amount-coin);
                if (curMin < min) {
                    min = curMin;
                }
            }
        }
        cache[amount] = min + 1;

        return cache[amount];
    }
}
