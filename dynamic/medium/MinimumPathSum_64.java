package dynamic.medium;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

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
        final int down = dfs(grid, i + 1, j);
        final int right = dfs(grid, i, j + 1);

        return grid[i][j] + Math.min(down, right);
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

    // O((n+m)*(n+m)log(n+m)) - time, O(m+n) - space
    public int minPathSumBFS(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = new int[][]{{0,1}, {1,0}};
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));
        minHeap.add(new int[]{0,0,grid[0][0]});
        while (!minHeap.isEmpty()) {
            int[] item = minHeap.poll();
            for (int[] direction: directions) {
                int i = item[0] + direction[0];
                int j = item[1] + direction[1];
                if (i >= 0 && j >= 0 && i < rows && j < cols && !visited[i][j]) {
                    int[] next = new int[]{i,j,item[2] + grid[i][j]};
                    if (i == rows - 1 && j == cols-1) {
                        return next[2];
                    }
                    minHeap.add(next);
                    visited[i][j] = true;
                }
            }
        }
        return grid[0][0];
    }
}
