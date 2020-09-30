package dynamic.medium;

/** M
 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 Note: You can only move either down or right at any point in time.

 Example:
 Input:
 [
 [1,3,1],
 [1,5,1],
 [4,2,1]
 ]
 Output: 7
 Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
// https://leetcode.com/problems/minimum-path-sum/
public class MinimumPathSum_64 {

    public static void main(String[] args) {
        MinimumPathSum_64 s = new MinimumPathSum_64();
        int[][] grid = new int[][] {{1,3,1}, {1,5,1}, {4,2,1}};

        System.out.println(s.minPathSumBF(grid)); // 7
        System.out.println(s.minPathSum(new int[][] {{1}})); // 1
        System.out.println(s.minPathSum(new int[][] {{}})); // 0
    }

    // O(2^(n+m)) - time, O(n+m) - space
    public int minPathSumBF(int[][] grid) {
        return dfs(grid, 0, 0);
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i == grid.length || j == grid[0].length) {
            return Integer.MAX_VALUE;
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }
        return grid[i][j] + Math.min(dfs(grid, i + 1, j), dfs(grid, i, j + 1));
    }

    // O(m*n) - time, O(1) - space
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int columns = grid[0].length;
        for (int i = 1; i < rows; i++) {
            grid[i][0] += grid[i-1][0];
        }
        for (int i = 1; i < columns; i++) {
            grid[0][i] += grid[0][i-1];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }

        return grid[rows-1][columns-1];
    }
}
