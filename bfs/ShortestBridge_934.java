package bfs;

import java.util.LinkedList;
import java.util.Queue;

/** M [marked]
 * n a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 *
 * Example 1:
 * Input: A = [[0,1],[1,0]]
 * Output: 1
 *
 * Example 2:
 * Input: A = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 *
 * Example 3:
 * Input: A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 *
 * Constraints:
 *     2 <= A.length == A[0].length <= 100
 *     A[i][j] == 0 or A[i][j] == 1
 */
/*
    1. Always no more then 2 islands?
    Solution: find first WHOLE island with DFS and then do BFS to find path to the second one.
    a) run dfs on first 1 that we find to mark all members of first island
    b) run bfs using every member of first island
    c) return the distance that we have when we find the first 1

 */
public class ShortestBridge_934 {

    public static void main(String[] args) {
        ShortestBridge_934 s= new ShortestBridge_934();
        System.out.println(s.shortestBridge(new int[][]{
                {1,1,1,1,1,1},
                {1,0,0,0,0,1},
                {1,0,0,1,1,1},
                {1,0,1,0,0,1},
                {1,0,0,0,0,1},
                {1,1,1,1,1,1}})); //1
        System.out.println(s.shortestBridge(new int[][]{
                {0,1,0},
                {0,0,0},
                {0,0,1}})); //2
    }

    // O(n*m) - time, space
    public int shortestBridge(int[][] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int rows = A.length;
        int cols = A[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] seen = new boolean[rows][cols]; // can be removed and we can update A to -1 if the cell is visited to optimize on space
        findFirstIsland(A, queue, seen);
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int[] direction: directions) {
                    int newX = curr[0] + direction[0];
                    int newY = curr[1] + direction[1];
                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !seen[newX][newY]) {
                        if (A[newX][newY] == 1) {
                            return level;
                        }
                        queue.add(new int[]{newX,newY});
                        seen[newX][newY] = true;
                    }
                }
            }
            level++;
        }
        return -1;
    }

    private void findFirstIsland(int[][] grid, Queue<int[]> queue, boolean[][] seen) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid,queue,seen,i,j);
                    return;
                }
            }
        }
    }

    private void dfs(int[][] grid, Queue<int[]> queue, boolean[][] seen, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (i < 0 || i == rows || j < 0 || j == cols || seen[i][j] || grid[i][j] == 0) {
            return;
        }
        queue.add(new int[]{i,j});
        seen[i][j] = true;
        dfs(grid,queue,seen,i,j+1);
        dfs(grid,queue,seen,i+1,j);
        dfs(grid,queue,seen,i,j-1);
        dfs(grid,queue,seen,i-1,j);
    }
}
