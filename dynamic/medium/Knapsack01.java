package dynamic.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
  The 0/1 Knapsack Problem solved with dynamic programming.
  Given n items, each with a weight & value, pick a subset of the items that
  maximizes value and stays below a weight constraint.

  https://algorithmsandme.com/dynamic-programming-0-1-knapsack-problem/
*/
public class Knapsack01 {

    public static void main(String[] args) {
        Knapsack01 s = new Knapsack01();
        System.out.println(s.findMaxBF(new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5}, 7)); //9
        System.out.println(s.findMax2(new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5}, 7)); //9

        System.out.println(s.findMaxDynamicTopDown(new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5}, 7)); //9
        System.out.println(s.findMaxDynamicBottonUp(new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5}, 7));
    }

    // O(2^n) - time, where n - number of items, O(n) - height of recursion tree (n - number of items)
    public int findMaxBF(int[] profit, int[] weights, int capacity) {
        return findMaxBF(profit, weights, capacity, 0);
    }

    private int findMaxBF(int[] profit, int[] weights, int capacity, int i) {
        if (i == weights.length) {
            return 0;
        }
        // Filter out items whose weight exceeds capacity
        if (weights[i] > capacity) {
            return findMaxBF(profit, weights, capacity, i+1);
        }
        int include = findMaxBF(profit, weights, capacity - weights[i], i+1) + profit[i];
        int exclude = findMaxBF(profit, weights, capacity, i+1);

        return Math.max(include, exclude);
    }

    // O(items*capacity) - time, space
    public int findMax2(int[] profit, int[] weights, int capacity) {
        Map<String, Integer> cache = new HashMap<>();

        return findMax2(profit, weights, capacity, 0, cache);
    }

    private int findMax2(int[] profit, int[] weights, int capacity, int i, Map<String, Integer> cache) {
        if (i == weights.length) {
            return 0;
        }
        String key = i+":"+capacity;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (weights[i] > capacity) {
            int temp = findMax2(profit, weights, capacity, i+1, cache);
            cache.put(key, temp);
            return cache.get(key);
        }
        int include = findMax2(profit, weights, capacity - weights[i], i+1, cache) + profit[i];
        int exclude = findMax2(profit, weights, capacity, i+1, cache);
        cache.put(key, Math.max(include, exclude));

        return cache.get(key);
    }

    // O(n*m) - time and space, n - capacity, m - number of items
    public int findMaxDynamicTopDown(int[] profits, int[] weights, int capacity) {
        int[][] cache = new int[weights.length+1][capacity+1];
        return findMaxDynamicTopDownUtils(profits, weights, capacity, weights.length, cache);
    }

    private int findMaxDynamicTopDownUtils(int[] profits, int[] weights,  int capacity, int totalItems, int[][] cache) {
        if (capacity == 0 || totalItems == 0) {
            return 0;
        }
        int currItemIdx = totalItems - 1;
        if (cache[totalItems][capacity] != 0) {
            return cache[totalItems][capacity];
        }
        if (weights[currItemIdx] > capacity) {
            cache[totalItems][capacity] = findMaxDynamicTopDownUtils(profits, weights, totalItems - 1, capacity, cache);
            return cache[totalItems][capacity];
        }
        int itemIncluded = profits[currItemIdx] + findMaxDynamicTopDownUtils(profits, weights, capacity - weights[currItemIdx], totalItems - 1, cache);
        int itemExcluded = findMaxDynamicTopDownUtils(profits, weights, capacity, totalItems - 1, cache);
        cache[totalItems][capacity] = Math.max(itemIncluded, itemExcluded);

        return cache[totalItems][capacity];
    }

    // O(n*m) - time and space, n - capacity, m - number of items
    public int findMaxDynamicBottonUp(int[] profits, int[] weights, int capacity) {
        int[][] m = new int[profits.length + 1][capacity + 1];
        for (int i = 1; i <= profits.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] > j) {
                    m[i][j] = m[i - 1][j];
                } else {
                    m[i][j] = Math.max(m[i - 1][j], profits[i - 1] + m[i - 1][j - weights[i - 1]]);
                }
            }
        }
        return m[profits.length][capacity];
    }

}
