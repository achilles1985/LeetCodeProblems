package dynamic.medium;

import java.util.HashMap;
import java.util.Map;

/** M
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 Now consider if some obstacles are added to the grids. How many unique paths would there be?

 An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 Note: m and n will be at most 100.

 Example 1:
 Input:
 [
 [0,0,0],
 [0,1,0],
 [0,0,0]
 ]
 Output: 2
 Explanation:
 There is one obstacle in the middle of the 3x3 grid above.
 There are two ways to reach the bottom-right corner:
 1. Right -> Right -> Down -> Down
 2. Down -> Down -> Right -> Right
 */
/*
    1. What if rows-1 && cols-1 is an obstacle?
    2. max size of the grid?
 */
// https://leetcode.com/problems/unique-paths-ii/submissions/
public class UniquePaths_II_63 {

    public static void main(String[] args) {
        int[][] max1 = new int[][]{{1,2,3}};
        int[][] max2 = new int[][]{{1},{2},{3}};

        UniquePaths_II_63 s = new UniquePaths_II_63();
        int[][] grid = new int[][] {{0,0,0}, {0,1,0}, {0,0,0}};

        System.out.println(s.uniquePathsWithObstacles(grid)); // 2
        System.out.println(s.uniquePathsWithObstaclesDynamic(grid)); // 2

        System.out.println(s.uniquePathsWithObstaclesDynamic(new int[][] {{1,0}})); // 0
        System.out.println(s.uniquePathsWithObstaclesDynamic(new int[][] {{0,1}})); // 0
        System.out.println(s.uniquePathsWithObstaclesDynamic(new int[][] {{1}, {0}})); // 0
    }

    // O(m*n) - time, space
    public int uniquePathsWithObstaclesDP(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        return helper(obstacleGrid, 0, 0, new HashMap<>());
    }

    private int helper(int[][] obstacleGrid, int i, int j, Map<String, Integer> cache) {
        String key = i + "," + j;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        if (i < 0 || i == obstacleGrid.length || j < 0 || j == obstacleGrid[0].length || obstacleGrid[i][j] == 1) {
            return 0;
        }
        if (i == rows-1 && j == cols-1) {
            return 1;
        }
        int left = helper(obstacleGrid, i, j+1, cache);
        int right = helper(obstacleGrid, i+1, j, cache);

        int res = left + right;
        cache.put(key, res);

        return res;
    }

    // O(2^m+n)) - time, O(m+n) - space
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return uniquePathsWithObstacles(obstacleGrid, obstacleGrid.length, obstacleGrid[0].length, 0, 0);
    }

    // O(n*m) - time, O(1) - space
    public int uniquePathsWithObstaclesDynamic(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int columns = obstacleGrid[0].length;

        // if obstacle st start, no way to go
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        obstacleGrid[0][0] = 1;
        for (int i = 1; i < rows; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i-1][0] == 1) ? 1 : 0;
        }
        for (int i = 1; i < columns; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i-1] == 1) ? 1 : 0;
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                }
            }
        }

        return obstacleGrid[rows-1][columns-1];
    }

    private int uniquePathsWithObstacles(int[][] obstacleGrid, int m, int n, int i, int j) {
        if (i < 0 || i > m-1 || j < 0 || j > n-1 || obstacleGrid[i][j] == 1) {
            return 0;
        }
        if (i == m-1 && j == n-1) {
            return 1;
        }
        int right = uniquePathsWithObstacles(obstacleGrid, m, n, i+1, j);
        int down = uniquePathsWithObstacles(obstacleGrid, m, n, i, j+1);

        return right + down;
    }
}
