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
        if (A == null || A.length == 0) {
            return -1;
        }
        int rows = A.length;
        int cols = A[0].length;
        Queue<int[]> queue = new LinkedList<>();
        findFirstIsland(A, queue); // find first island and mark all cells as visited and add them to the queue
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        int level = 0;
        while (!queue.isEmpty()) { // bfs from all cells of the second island at once till find second island
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int[] direction: directions) {
                    int newX = curr[0] + direction[0];
                    int newY = curr[1] + direction[1];
                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && A[newX][newY] != -1) {
                        if (A[newX][newY] == 1) {
                            return level;
                        }
                        queue.add(new int[]{newX,newY});
                        A[newX][newY] = -1;
                    }
                }
            }
            level++;
        }
        return -1;
    }

    private void findFirstIsland(int[][] grid, Queue<int[]> queue) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid,queue,i,j);
                    return;
                }
            }
        }
    }

    private void dfs(int[][] grid, Queue<int[]> queue, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (i < 0 || i == rows || j < 0 || j == cols || grid[i][j] == -1 || grid[i][j] == 0) {
            return;
        }
        queue.add(new int[]{i,j});
        grid[i][j] = -1;
        dfs(grid,queue,i,j+1);
        dfs(grid,queue,i+1,j);
        dfs(grid,queue,i,j-1);
        dfs(grid,queue,i-1,j);
    }
}
