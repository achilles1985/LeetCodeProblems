package graph.NumberOfIslands_200;

/** M
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.

 Example 1:
 Input:
 11110
 11010
 11000
 00000
 Output: 1

 Example 2:
 Input:
 11000
 11000
 00100
 00011
 Output: 3
 */
public class Solution {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int counter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    dfs(visited, i, j, grid);
                    counter++;
                }
            }
        }

        return counter;
    }

    private void dfs(boolean[][] visited, int row, int col, char[][] grid) {
        if (row < 0 || col < 0 || row == grid.length || col == grid[0].length || grid[row][col] != 1 || visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        dfs(visited, row, col+1, grid);
        dfs(visited, row+1, col, grid);
        dfs(visited, row, col-1, grid);
        dfs(visited, row-1, col, grid);
    }
}
