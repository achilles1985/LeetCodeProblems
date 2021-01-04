package dynamic.hard;

/** H
 * On a 2-dimensional grid, there are 4 types of squares:
 *     1 represents the starting square.  There is exactly one starting square.
 *     2 represents the ending square.  There is exactly one ending square.
 *     0 represents empty squares we can walk over.
 *     -1 represents obstacles that we cannot walk over.
 *
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 *
 * Example 1:
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 *
 * Example 2:
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 *
 * Example 3:
 * Input: [[0,1],[2,0]]
 * Output: 0
 * Explanation:
 * There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 *
 * Note:
 *     1 <= grid.length * grid[0].length <= 20
 */
public class UniquePaths_III_980 {

    public static void main(String[] args) {
        UniquePaths_III_980 s = new UniquePaths_III_980();
        System.out.println(s.uniquePathsIII(new int[][]{
                {1,0,0,0},
                {0,0,0,0},
                {0,0,2,-1}})); //2
    }

    // O(3^n) - time, O(n) - space, where n - number of cells. 3 not 4 because we never explore the cell we come from.
    public int uniquePathsIII(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int x = 0, y = 0, empty = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    x = i;
                    y = j;
                }
                if (grid[i][j] == 0) {
                    empty++;
                }
            }
        }

        return helper(grid, x, y, empty);
    }

    private int helper(int[][] grid, int i, int j, int counter) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (i < 0 || i == rows || j < 0 || j == cols || grid[i][j] < 0) {
            return 0;
        }
        if (grid[i][j] == 2) {
            return counter == -1 ? 1 : 0;
        }
        grid[i][j] = -1;
        int right = helper(grid,i,j+1, counter-1);
        int down = helper(grid,i+1,j, counter-1);
        int left = helper(grid,i,j-1, counter-1);
        int up = helper(grid,i-1,j, counter-1);
        grid[i][j] = 0;

        return right + down + left + up;
    }
}
