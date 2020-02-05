package bfs;

import utils.SolutionUtils;

import java.util.LinkedList;
import java.util.Queue;

public class Matrix_01_542 {

    public static void main(String[] args) {
        Matrix_01_542 s = new Matrix_01_542();
        SolutionUtils.print(s.updateMatrix(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
        SolutionUtils.print(s.updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}}));
    }

    // O(n*m) - time, space
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = bfs(i,j,matrix);
                }
            }
        }

        return matrix;
    }

    private int bfs(int i, int j, int[][] matrix) {
        int[][] directions = new int[][]{{1,0},{-1,0},{0,-1},{0,1}};
        Queue<int[]> neighbors = new LinkedList<>();
        neighbors.add(new int[]{i,j});

        int levels = 0;
        while (!neighbors.isEmpty()) {
            int size = neighbors.size();
            for (int k = 0; k < size; k++) {
                int[] cell = neighbors.poll();
                int row = cell[0];
                int col = cell[1];
                if (matrix[row][col] == 0) {
                    return levels;
                }
                for (int[] direction: directions) {
                    int nextRow = row + direction[0];
                    int nextCol = col + direction[1];
                    if (nextRow >= 0 && nextRow < matrix.length && nextCol >= 0 && nextCol < matrix[0].length) {
                        neighbors.add(new int[]{nextRow, nextCol});
                    }
                }
            }
            levels++;
        }

        return matrix[i][j];
    }
}
