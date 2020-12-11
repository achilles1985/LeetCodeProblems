package dynamic.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/** M[marked]
  You are given coins of different denominations and a total amount of money amount.
  Write a function to compute the fewest number of coins that you need to make up that amount.
 If that amount of money cannot be made up by any combination of the coins, return -1.

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
public class CoinChange_322 {

    public static void main(String[] args) {
        CoinChange_322 s = new CoinChange_322();
        System.out.println(s.coinChange(new int[] {2,5}, 3)); //-1

        System.out.println(s.makeChangeDynamicButtomUp(new int[] {2}, 3));

        System.out.println(s.makeChangeBF(new int[] {1,2,5}, 8));
        System.out.println(s.makeChangeDynamicTopDown(new int[] {1,2,5}, 12));
        System.out.println(s.makeChangeDynamicButtomUp(new int[] {1,2,5}, 12));
    }

    // O(amount*coins) - time, ?O(amount) - space
    public int coinChange(int[] coins, int amount) {
        if (amount == 0 || coins.length == 0) {
            return 0;
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(amount);
        visited.add(amount);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int currAmount = queue.poll();
                if (currAmount == 0) {
                    return level;
                }
                for (int coin: coins) {
                    int newAmount = currAmount - coin;
                    if (newAmount >= 0 && !visited.contains(newAmount)) {
                        queue.add(newAmount);
                        visited.add(newAmount);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    // exponential time (A^C), C - number of coins, A = amount
    public int makeChangeBF(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int coin: coins) {
            if (amount - coin >= 0) {
                int curMin = makeChangeBF(coins, amount-coin);
                min = Math.min(min, curMin);
            }
        }
        return min + 1;
    }

    public int makeChangeT(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int[] cache = new int[amount+1];
        Arrays.fill(cache, -1);
        return makeChangeTHelper(coins, amount, cache);
    }

    private int makeChangeTHelper(int[] coins, int amount, int[] cache) {
        if (amount == 0) {
            return 0;
        }
        if (cache[amount] != -1) {
            return cache[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (amount - coins[i] < 0) {
                continue;
            }
            int localMin = makeChangeTHelper(coins, amount - coins[i], cache);
            min = Math.min(localMin, min);
        }
        cache[amount] = (min == Integer.MAX_VALUE || min == -1) ? -1 : (min + 1);
        return cache[amount];
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
        Arrays.fill(cache, amount+1);
        cache[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin: coins) {
                if ((i - coin) >= 0) {
                    cache[i] = Math.min(cache[i-coin] + 1, cache[i]);
                }
            }
        }

        return cache[amount] > amount ? -1 : cache[amount];
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
                int curMin = makeChangeDynamicTopDownUtils(coins, amount-coin, cache);
                if (curMin < min) {
                    min = curMin;
                }
            }
        }
        cache[amount] = (min == Integer.MAX_VALUE || min == -1) ? -1 : (min + 1);

        return cache[amount];
    }

}
