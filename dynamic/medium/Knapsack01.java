package dynamic.medium;

/*
  The 0/1 Knapsack Problem solved with dynamic programming.
  Given n items, each with a weight & value, pick a subset of the items that
  maximizes value and stays below a weight constraint.

  https://algorithmsandme.com/dynamic-programming-0-1-knapsack-problem/
*/
public class Knapsack01 {

    public static void main(String[] args) {
        Knapsack01 s = new Knapsack01();
        System.out.println(s.findMaxDynamicTopDown(new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5}, 7));
        System.out.println(s.findMaxBruteForceRecursion(new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5}, 7));
        System.out.println(s.findMaxDynamicBottonUp(new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5}, 7));
    }

    // O(2^n) - time, where n - number of items, O(n) - height of recursion tree (n - number of items)
    public int findMaxBruteForceRecursion(int[] profit, int[] weights, int capacity) {
        return findMaxBruteForceRecursionHelper(profit, weights, capacity, weights.length-1);
    }

    private int findMaxBruteForceRecursionHelper(int[] profit, int[] weights, int capacity, int itemsCount) {
        if (capacity == 0 || itemsCount == 0) {
            return 0;
        }
        int currItem = itemsCount - 1;
        // Filter out items whose weight exceeds capacity
        if (capacity < weights[currItem]) {
            return findMaxBruteForceRecursionHelper(profit, weights, capacity, itemsCount - 1);
        }
        int itemIncluded = profit[currItem] + findMaxBruteForceRecursionHelper(profit, weights, capacity - weights[currItem], itemsCount - 1);
        int itemExcluded = findMaxBruteForceRecursionHelper(profit, weights, capacity, itemsCount - 1);

        return Math.max(itemIncluded, itemExcluded);
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
