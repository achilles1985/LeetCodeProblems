package array.NumberOfDistinctIslands_860;

import java.util.HashSet;
import java.util.Set;

/** M
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical). You may assume all four edges of the grid are surrounded by water.
 Count the number of distinct islands. An island is considered to be the same as another if and only if one island has the same shape as another island (and not rotated or reflected).
 Notice that:
 11
 1
 and

 1
 11
 are considered different island, because we do not consider reflection / rotation.

 Example 1:
 Input:
 [
 [1,1,0,0,1],
 [1,0,0,0,0],
 [1,1,0,0,1],
 [0,1,0,1,1]
 ]
 Output: 3
 Explanation:
 11   1    1
 1        11
 11
 1
 Example 2:

 Input:
 [
 [1,1,0,0,0],
 [1,1,0,0,0],
 [0,0,0,1,1],
 [0,0,0,1,1]
 ]
 Output: 1
 */
public class Solution {

    public int numDistinctIslands(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Set<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    StringBuilder builder = new StringBuilder();
                    dfs(i, j, visited, grid, i, j, builder);
                    set.add(builder.toString());
                }
            }
        }

        return set.size();
    }

    private void dfs(int row, int col, boolean[][] visited, int[][] grid, int x0, int y0, StringBuilder builder) {
        if (row < 0 || row > grid.length-1 || col < 0 || col > grid[0].length-1 || grid[row][col] == 0 || visited[row][col]) {
            return;
        }

        visited[row][col] = true;
        builder.append((row-x0) + "_" + (col-y0) + "|");

        dfs(row, col+1, visited, grid, x0, y0, builder);
        dfs(row+1, col, visited, grid, x0, y0, builder);
        dfs(row, col-1, visited, grid, x0, y0, builder);
        dfs(row-1, col, visited, grid, x0, y0, builder);
    }
}
