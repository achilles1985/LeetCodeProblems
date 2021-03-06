package dfs;

import utils.SolutionUtils;

/** M
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 *
 * Example 1:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 * Example 2:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,1,1]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,2,1]]
 *
 *
 * Note:
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 */
public class Matrix_01_542 {

    public static void main(String[] args) {
        Matrix_01_542 s = new Matrix_01_542();
        SolutionUtils.print(s.updateMatrix(new int[][]{
                {0,0,0},
                {0,1,0},
                {1,1,1}}));
        SolutionUtils.print(s.updateMatrix(new int[][]{
                {0,0,0},
                {0,1,0},
                {0,0,0}}));
    }

    // O(n*m) - time, space
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = dfs(i,j,matrix, visited);
                }
            }
        }

        return matrix;
    }

    private int dfs(int i, int j, int[][] matrix, boolean[][] visited) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || visited[i][j]) {
            return 0;
        }
        if (matrix[i][j] == 0) {
            return 1;
        }
        visited[i][j] = true;
        int right = dfs(i, j+1, matrix, visited);
        int down = dfs(i+1, j, matrix, visited);
        int left = dfs(i, j-1, matrix, visited);
        int up = dfs(i-1, j, matrix, visited);

        visited[i][j] = false;

        return Math.min(Math.min(right, down), Math.min(left, up)) + 1;
    }
}
