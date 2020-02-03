package bfs;

import java.util.*;

/**M
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 *
 * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k
 * such that:
 *
 *     Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or
 *     corner)
 *     C_1 is at location (0, 0) (ie. has value grid[0][0])
 *     C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 *     If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
 *
 * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist,
 * return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: [[0,1],[1,0]]
 *
 *
 * Output: 2
 *
 * Example 2:
 *
 * Input: [[0,0,0],[1,1,0],[1,1,0]]
 *
 *
 * Output: 4
 *
 * Note:
 *     1 <= grid.length == grid[0].length <= 100
 *     grid[r][c] is 0 or 1
 */
public class ShortestPathInBinaryMatrix_1091 {

    public static void main(String[] args) {
        ShortestPathInBinaryMatrix_1091 s = new ShortestPathInBinaryMatrix_1091();
        System.out.println(s.shortestPathBinaryMatrix(new int[][]{
                {1,0,0},
                {1,1,0},
                {1,1,0}})); //-1
        System.out.println(s.shortestPathBinaryMatrix(new int[][]{
                {0,0,0},
                {1,1,0},
                {1,1,0}})); //4
        System.out.println(s.shortestPathBinaryMatrix(new int[][]{
                {0,1},
                {1,0}})); //2
        System.out.println(s.shortestPathBinaryMatrix(new int[][]{{0}})); //1

        //[[1,0,0],[1,1,0],[1,1,0]]
    }

    // O(n*m) - time, space
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] != 0 || grid[grid.length-1][grid[0].length-1] != 0) {
            return -1;
        }
        int[][] directions = new int[][]{
                {0,1},{1,1},{1,0},{1,-1},
                {0,-1},{-1,-1},{-1,0},{-1,1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        grid[0][0] = 1;
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];
                    if (row == grid.length-1 && col == grid[0].length-1) {
                        return count;
                    }
                for (int[] direction: directions) {
                    int nextRow = row + direction[0];
                    int nextCol = col + direction[1];
                    if (nextRow < 0 || nextRow >= grid.length || nextCol < 0 || nextCol >= grid[0].length || grid[nextRow][nextCol] != 0) {
                        continue;
                    }
                    queue.add(new int[]{nextRow, nextCol});
                    grid[nextRow][nextCol] = 1;
                }
            }
            count++;
        }

        return -1;
    }

}
