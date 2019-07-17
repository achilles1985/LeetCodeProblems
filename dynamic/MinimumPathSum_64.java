package dynamic;

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

        System.out.println(s.minPathSum(grid)); // 7
        System.out.println(s.minPathSum(new int[][] {{1}})); // 1
        System.out.println(s.minPathSum(new int[][] {{}})); // 0
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
