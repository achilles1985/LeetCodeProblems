package dfs;

import java.util.HashSet;
import java.util.Set;

/**M
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected
 * 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Count the number of distinct islands. An island is considered to be the same as another if and only if one island
 * can be translated (and not rotated or reflected) to equal the other.
 *
 * Example 1:
 * 11000
 * 11000
 * 00011
 * 00011
 *
 * Given the above grid map, return 1.
 *
 * Example 2:
 * 11011
 * 10000
 * 00001
 * 11011
 *
 * Given the above grid map, return 3.
 *
 * Notice that:
 * 11
 * 1
 *
 * and
 *  1
 * 11
 *
 * are considered different island shapes, because we do not consider reflection / rotation.
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
/*
   Shape uniqueness can be determined by its hash
 */
public class NumberOfDistinctIslands_694 {

    public static void main(String[] args) {
        NumberOfDistinctIslands_694 s = new NumberOfDistinctIslands_694();
        System.out.println(s.numDistinctIslands(new int[][]{
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}})); //1
        System.out.println(s.numDistinctIslands(new int[][]{
                {1,1,0,1,1},
                {1,0,0,0,0},
                {0,0,0,0,1},
                {1,1,0,1,1}})); //3
    }

    // O(n*m) - time, space
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid[0].length == 0) {
            return 0;
        }
        Set<String> distinct = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(i,j,sb,grid);
                    distinct.add(sb.toString());
                }
            }
        }
        return distinct.size();
    }

    private void dfs(int i, int j, StringBuilder sb, int[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            sb.deleteCharAt(sb.length()-1);
            return;
        }
        grid[i][j] = 0;
        dfs(i, j+1, sb.append("r"), grid);
        dfs(i+1, j, sb.append("d"), grid);
        dfs(i, j-1, sb.append("l"), grid);
        dfs(i-1, j, sb.append("u"), grid);
        sb.append("b"); // explored all ways, backtrack
    }

    private void dfs2(int i, int j, StringBuilder sb, int[][] grid) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
            grid[i][j] = 0;
            dfs(i, j + 1, sb.append("r"), grid);
            dfs(i + 1, j, sb.append("d"), grid);
            dfs(i, j - 1, sb.append("l"), grid);
            dfs(i - 1, j, sb.append("u"), grid);
        }
    }
}
