package dynamic.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/** M
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid
 (marked 'Finish' in the diagram below).
 How many possible unique paths are there?

 Above is a 7 x 3 grid. How many possible unique paths are there?
 Note: m and n will be at most 100.

 Example 1:
 Input: m = 3, n = 2
 Output: 3
 Explanation:
 From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 1. Right -> Right -> Down
 2. Right -> Down -> Right
 3. Down -> Right -> Right

 Example 2:
 Input: m = 7, n = 3
 Output: 28
 */
public class UniquePaths_62 {

    public static void main(String[] args) {
        UniquePaths_62 s = new UniquePaths_62();
        System.out.println(s.uniquePathsTopDown(5,3)); //3
        System.out.println(s.uniquePathsBF(3,2)); //3

        System.out.println(s.uniquePathsDynamic2(3,4)); //10
        System.out.println(s.uniquePathsDynamic2(4,4)); //20
        System.out.println(s.uniquePathsDynamic2(3,2)); //3

        System.out.println(s.uniquePathsBF(3,2)); //3
        System.out.println(s.uniquePathsDynamic(3,2)); //3

        System.out.println(s.uniquePathsBF(7,3)); //28
        System.out.println(s.uniquePathsDynamic(7,3)); // 28
    }

    // O(2^m+n)- time, O(m+n) - space
    public int uniquePathsBF(int m, int n) {
        return helper(m-1, n-1);
    }

    // Optimized - O(m*n) - time, space
    public int uniquePathsTopDown(int m, int n) {
        if (m < 0 || n < 0) {
            return 0;
        }
        if (m == 0 || n == 0) {
            return 1;
        }

        return helper(m,n,0,0, new HashMap<>());
    }

    // O(m*n) - time, O(m*n) - space
    public int uniquePathsDP(int m, int n) {
        return helperDP(m-1, n-1, new HashMap<>());
    }

    // O(m*n) - time, space
    public int uniquePathsDynamic(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    // O(m*n) - time, O(n) - space
    // Optimized space complexity moved from matrix to 2 one-dimentional arrays because we work only with 2 rows at a time.
    public int uniquePathsDynamic2(int m, int n) {
        int[] odd = new int[n];
        int[] even = new int[n];
        Arrays.fill(odd, 1);
        Arrays.fill(even, 1);
        for (int row = 1; row < m; row++) {
            int[] current;
            int[] previous;
            if (row%2 == 1) {
                current = odd;
                previous = even;
            } else {
                current = even;
                previous = odd;
            }
            for (int col = 1; col < n; col++) {
                current[col] = current[col-1] + previous[col];
            }
        }
        return m%2 == 1 ? even[n-1] : odd[n-1];
    }

    private int helper(int m, int n, int i, int j, Map<String, Integer> cache) {
        String key = i + "," + j;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (i < 0 || i == m || j < 0 || j == n) {
            return 0;
        }
        if (i == m-1 && j == n-1) {
            return 1;
        }
        int left = helper(m,n,i,j+1,cache);
        int right = helper(m,n,i+1,j, cache);

        int res = left + right;
        cache.put(key, res);

        return res;
    }

    private int helper(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return 1;
        }

        return helper(i-1, j) + helper(i, j-1);
    }

    private int helperDP(int i, int j, Map<String, Integer> cache) {
        String key = i+","+j;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (i < 0 || j < 0) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return 1;
        }
        int count = helperDP(i+1, j, cache) + helperDP(i, j+1, cache);
        cache.put(key, count);

        return count;
    }

}
