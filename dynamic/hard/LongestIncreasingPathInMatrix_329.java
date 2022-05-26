package dynamic.hard;

/** H
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 * From each cell, you can either move in four directions: left, right, up, or down.
 * You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 * Example 1:
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 *
 * Example 2:
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 *
 * Example 3:
 * Input: matrix = [[1]]
 * Output: 1
 *
 * Constraints:
 *     m == matrix.length
 *     n == matrix[i].length
 *     1 <= m, n <= 200
 *     0 <= matrix[i][j] <= 231 - 1
 */
public class LongestIncreasingPathInMatrix_329 {

    public static void main(String[] args) {
        LongestIncreasingPathInMatrix_329 s = new LongestIncreasingPathInMatrix_329();
        System.out.println(s.longestIncreasingPathBF(new int[][]{
                {3,4,5},
                {4,6,7},
                {5,7,8}})); //5
        System.out.println(s.longestIncreasingPath(new int[][]{
                {9,9,4},
                {6,6,8},
                {2,1,1}})); //4
        System.out.println(s.longestIncreasingPath(new int[][]{
                {3,4,5},
                {3,2,6},
                {2,2,1}})); //4
    }

    //O(2^n+m) - time, O(m*n) - space, max depth for recursion stack, it's m*n in a worth case
    public int longestIncreasingPathBF(int[][] matrix) {
        if (matrix == null | matrix.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dfs(matrix, i, j, Integer.MIN_VALUE));
            }
        }
        return max;
    }

    // Cache the result of the subproblem so that every subproblem calculated only once
    // O(m*n) - time, space
    public int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int max = 0;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dp[i][j] == 0) {
                    max = Math.max(max, dfs2(matrix, i, j, Integer.MIN_VALUE, dp));
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public int longestIncreasingPath2(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        if(cache[i][j] != 0) {
            return cache[i][j];
        }
        int max = 1;
        for (int[] dir: dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) {
                continue;
            }
            int len = 1 + dfs(matrix, x, y, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;

        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int prev) {
        if (i < 0 || i == matrix.length || j < 0 || j == matrix[0].length || prev >= matrix[i][j]) {
            return 0;
        }

        int right = dfs(matrix, i, j + 1, matrix[i][j]);
        int down = dfs(matrix, i+1, j, matrix[i][j]);
        int left = dfs(matrix, i, j-1, matrix[i][j]);
        int up = dfs(matrix, i-1, j, matrix[i][j]);

        int max1 = Math.max(left, up);
        int max2 = Math.max(right, down);

        return Math.max(max1, max2) + 1;
    }

    private int dfs2(int[][] matrix, int i, int j, int prev, int[][] dp) {
        if (i < 0 || i == matrix.length || j < 0 || j == matrix[0].length || prev >= matrix[i][j]) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int right = dfs2(matrix, i, j + 1, matrix[i][j],dp);
        int down = dfs2(matrix, i+1, j, matrix[i][j],dp);
        int left = dfs2(matrix, i, j-1, matrix[i][j],dp);
        int up = dfs2(matrix, i-1, j, matrix[i][j],dp);

        int max = Math.max(Math.max(left, up), Math.max(right, down)) + 1;
        dp[i][j] = max;

        return max;
    }
}
