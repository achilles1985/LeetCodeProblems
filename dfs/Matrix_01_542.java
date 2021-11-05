package dfs;

import utils.SolutionUtils;

import java.util.LinkedList;
import java.util.Queue;

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

    // O(n*m) - time, O(n*m) space, when all cells are zero
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[]{i,j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        while (!queue.isEmpty()) {
            int[] polled = queue.poll();
            for (int[] dir: dirs) {
                int newX = polled[0] + dir[0];
                int newY = polled[1] + dir[1];
                if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length && matrix[newX][newY] > matrix[polled[0]][polled[1]] + 1) {
                    queue.add(new int[]{newX, newY});
                    matrix[newX][newY] = matrix[polled[0]][polled[1]] + 1;
                }
            }
        }

        return matrix;
    }

}
