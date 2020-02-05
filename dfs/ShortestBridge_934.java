package dfs;

import java.util.LinkedList;
import java.util.Queue;

/**M
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not
 * connected to any other 1s.)
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 *
 * Example 1:
 * Input: [[0,1],[1,0]]
 * Output: 1
 *
 * Example 2:
 * Input: [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 *
 * Example 3:
 * Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 *
 * Note:
 *     1 <= A.length = A[0].length <= 100
 *     A[i][j] == 0 or A[i][j] == 1
 */
/*
    Do dfs to find first island, mark each cell of the island as -1.
    Find first cell of the second island, do bfs from it till meet -1 (first island).
 */
public class ShortestBridge_934 {

    public static void main(String[] args) {
        ShortestBridge_934 s = new ShortestBridge_934();
        System.out.println(s.shortestBridge(new int[][]{
                {1,1,1,1,1},
                {1,0,0,0,1},
                {1,0,1,0,1},
                {1,0,0,0,1},
                {1,1,1,1,1}})); //1
        System.out.println(s.shortestBridge(new int[][]{{0,1},{1,0}})); //1
        System.out.println(s.shortestBridge(new int[][]{{0,1,0},{0,0,0},{0,0,1}})); //2
        System.out.println(s.shortestBridge(new int[][]{
                {0,1,0,0,0,0},
                {0,1,1,1,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {1,1,0,0,0,0}})); //3
    }

    // O(n*m) - time, space
    public int shortestBridge(int[][] A) {
        int[] firstIsland = new int[2];
        boolean found = false;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 1 && !found) {
                    firstIsland = new int[]{i,j};
                    found = true;
                }
            }
        }

        dfs(firstIsland[0], firstIsland[1], A);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 1) {
                    min = Math.min(min, bfs(i,j,A));
                }
            }
        }
        return min;
    }

    private void dfs(int i, int j, int[][] matrix) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == 0 || matrix[i][j] == -1) {
            return;
        }
        matrix[i][j] = -1;
        dfs(i, j + 1, matrix);
        dfs(i + 1, j, matrix);
        dfs(i, j - 1, matrix);
        dfs(i - 1, j, matrix);
    }

    private int bfs(int i, int j, int[][] matrix) {
        int[][] directions = new int[][] {{0,1},{1,0},{0,-1},{-1,0}};
        Queue<int[]> neighbors = new LinkedList<>();
        neighbors.add(new int[]{i,j});
        int levels = -1;
        while (!neighbors.isEmpty()) {
            int size = neighbors.size();
            while (size-- > 0) {
                int[] cell = neighbors.poll();
                int row = cell[0];
                int col = cell[1];
                if (matrix[row][col] == -1) {
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

        return levels;
    }
}
