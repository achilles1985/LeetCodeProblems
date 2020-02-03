package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**E
 * In a given grid, each cell can have one of three values:
 *     the value 0 representing an empty cell;
 *     the value 1 representing a fresh orange;
 *     the value 2 representing a rotten orange.
 *
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible,
 * return -1 instead.

 * Example 1:
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 * Input: [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens
 * 4-directionally.
 *
 * Example 3:
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 * Note:
 *     1 <= grid.length <= 10
 *     1 <= grid[0].length <= 10
 *     grid[i][j] is only 0, 1, or 2.
 */
/*
    Find all rotten ones. Do BFS for each of them at once counting each round.
 */
public class RottingOranges_994 {

    public static void main(String[] args) {
        RottingOranges_994 s = new RottingOranges_994();
        System.out.println(s.orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}})); //4
        System.out.println(s.orangesRotting(new int[][]{{2,1,1},{0,1,1},{1,0,1}})); //-1
        System.out.println(s.orangesRotting(new int[][]{{0,2}})); //0
    }

    // O(n*m) - time, space
    public int orangesRotting(int[][] grid) {
        int[][] directions = new int[][]{{1,0},{-1,0},{0,-1},{0,1}};
        Queue<int[]> queue = new LinkedList<>();
        int countFresh = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i,j});
                } else if (grid[i][j] == 1) {
                    countFresh++;
                }
            }
        }
        if (countFresh == 0) {
            return 0;
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] polled = queue.poll();
                int row = polled[0];
                int col = polled[1];
                for (int[] direction: directions) {
                    int nextRow = row + direction[0];
                    int nextCol = col + direction[1];
                    if (nextRow < 0 || nextRow >= grid.length || nextCol < 0 || nextCol >= grid[0].length || grid[nextRow][nextCol] != 1) {
                        continue;
                    }
                    queue.add(new int[]{nextRow, nextCol});
                    grid[nextRow][nextCol]++;
                    countFresh--;
                }
            }
            time++;
        }
        return countFresh == 0 ? time - 1 : -1;
    }
}
