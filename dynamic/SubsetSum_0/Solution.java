package dynamic.SubsetSum_0;

import java.util.Map;

public class Solution {

    // Brute force recursive solution, O(2^n) - time, O(n) - space
    public boolean subsetSum(int[] arr, int n, int sum) {
        if (sum == 0) {
            return true;
        }
        if (sum < 0 || n < 0) {
            return false;
        }

        boolean include = subsetSum(arr, n - 1, sum - arr[n]);
        boolean exclude = subsetSum(arr, n - 1, sum);
        int a= 0;

        return include || exclude;
    }

    // Dynamic Top-Down approach with memoization, O(n*sum) - time
    public boolean subsetSum2(int[] arr, Map<String, Boolean> lookup, int n, int sum) {
        if (sum == 0) {
            return true;
        }
        if (sum < 0 || n < 0) {
            return false;
        }

        String key = n + "|" + sum;

        if (!lookup.containsKey(key)) {
            boolean include = subsetSum2(arr, lookup, n - 1, sum - arr[n]);
            boolean exclude = subsetSum2(arr, lookup, n - 1, sum);

            lookup.put(key, include || exclude);
        }

        return lookup.get(key);
    }

    // Bottom-Up approach, O(n*sum) - time
    public boolean subsetSum3(int[] arr, int sum) {
        boolean[][] m = new boolean[arr.length + 1][sum + 1];
        for (int i = 0; i < m.length; i++) {
            m[i][0] = true;
        }

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if ((j - arr[i-1]) < 0) {
                    m[i][j] = m[i-1][j];
                } else {
                    m[i][j] = m[i-1][j] || m[i-1][j - arr[i-1]];
                }
            }
        }

        return m[arr.length][sum];
    }
}
