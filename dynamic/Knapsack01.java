package dynamic;

import java.util.HashMap;
import java.util.Map;

public class Knapsack01 {

    public static void main(String[] args) {
        Knapsack01 s = new Knapsack01();
        System.out.println(s.findMaxBruteForceRecursion(new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5}, 7));
        System.out.println(s.findMaxDynamicBottonUp(new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5}, 7));
        System.out.println(s.findMaxDynamicTopDown(new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5}, 7));
    }

    public int findMaxBruteForceRecursion(int[] profits, int[] weights, int capacity) {
        return findMaxBruteForceRecursionUtils(profits, weights, capacity, 0, capacity);
    }

    // O(2^n) - time, where n - number of items, O(n) - height of recursion tree (n - number of items)
    private int findMaxBruteForceRecursionUtils(int[] profits, int[] weights, int capacity, int currentItem, int remainingWeigth) {
        if (currentItem >= profits.length || remainingWeigth <= 0) {
            return 0;
        }

        if (weights[currentItem] > remainingWeigth) {
            return findMaxBruteForceRecursionUtils(profits, weights, capacity, currentItem+1, remainingWeigth);
        } else {
            return Math.max(findMaxBruteForceRecursionUtils(profits, weights, capacity, currentItem+1, remainingWeigth-weights[currentItem]) + profits[currentItem],
                    findMaxBruteForceRecursionUtils(profits, weights, capacity, currentItem+1, remainingWeigth));
        }
    }

    // O(n*m) - time and space, n - capacity, m - number of items
    public int findMaxDynamicTopDown(int[] profits, int[] weights, int capacity) {
        Map<Key, Integer> cache = new HashMap<>();
        return findMaxDynamicTopDownUtils(profits, weights, capacity, 0, capacity, cache);
    }

    private int findMaxDynamicTopDownUtils(int[] profits, int[] weights, int capacity, int currentItem, int remainingWeight, Map<Key, Integer> cache) {
        if (currentItem >= profits.length || remainingWeight <= 0) {
            return 0;
        }
        int remainingItems = profits.length - currentItem;
        Key key = new Key(remainingWeight, remainingItems);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int max;
        if (weights[currentItem] > remainingWeight) {
            max = findMaxDynamicTopDownUtils(profits, weights, capacity, currentItem+1, remainingWeight, cache);
        } else {
            max = Math.max(profits[currentItem] + findMaxDynamicTopDownUtils(profits, weights, capacity, currentItem+1, remainingWeight-weights[currentItem], cache),
                    findMaxDynamicTopDownUtils(profits, weights, capacity, currentItem+1, remainingWeight, cache));
        }

        cache.put(key, max);

        return max;
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

    public static class Key {
        int remainingWeight;
        int remainingItems;

        public Key(int remainingWeight, int remainingItems) {
            this.remainingWeight = remainingWeight;
            this.remainingItems = remainingItems;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (remainingWeight != key.remainingWeight) return false;
            return remainingItems == key.remainingItems;
        }

        @Override
        public int hashCode() {
            int result = remainingWeight;
            result = 31 * result + remainingItems;
            return result;
        }
    }

 ////////////////// from byteBtByte ////////////////////
    public static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public int knapsack(Item[] items, int W) {
        return knapsack(items, W, 0);
    }

    private int knapsack(Item[] items, int W, int i) {
        if (i == items.length) {
            return 0;
        }
        if (W - items[i].weight < 0) {
            return knapsack(items, W, i + 1);
        }
        return Math.max(
                knapsack(items, W - items[i].weight, i + 1) + items[i].value,
                knapsack(items, W, i + 1));
    }
}
