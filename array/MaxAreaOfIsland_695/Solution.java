package array.MaxAreaOfIsland_695;

/**M
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

 Example 1:
 [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.

 Example 2:
 [[0,0,0,0,0,0,0,0]]
 Given the above grid, return 0.

 Note: The length of each dimension in the given grid does not exceed 50.
 */
public class Solution {

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int max = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    max = Math.max(max, maxAreaUtils(i, j, visited, grid));
                }
            }
        }

        return max;
    }

    private int maxAreaUtils(int row, int col, boolean[][] visited, int[][] grid) {
        if (row < 0 || row > grid.length-1 || col < 0 || col > grid[0].length-1 || grid[row][col] == 0 || visited[row][col]) {
            return 0;
        }

        visited[row][col] = true;

        return 1 + maxAreaUtils(row, col+1, visited, grid) +
                maxAreaUtils(row+1, col, visited, grid) +
                maxAreaUtils(row, col-1, visited, grid) +
                maxAreaUtils(row-1, col, visited, grid);
    }
}
