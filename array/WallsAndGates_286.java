package array;

import utils.SolutionUtils;

/**M
 * You are given a m x n 2D grid initialized with these three possible values.
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that
 * the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a Gate, that room should
 * remain filled with INF
 *
 * Have you met this question in a real interview?
 * Example1
 * Input:
 * [[2147483647,-1,0,2147483647],
 * [2147483647,2147483647,2147483647,-1],
 * [2147483647,-1,2147483647,-1],
 * [0,-1,2147483647,2147483647]]
 * Output:
 * [[3,-1,0,1],
 * [2,2,1,-1],
 * [1,-1,2,-1],
 * [0,-1,3,4]]
 *
 * Explanation:
 * the 2D grid is:
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 * the answer is:
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 *
 *  Example2
 * Input:
 * [[0,-1],
 * [2147483647,2147483647]]
 * Output:
 * [[0,-1],
 * [1,2]]
 */
public class WallsAndGates_286 {

    public static void main(String[] args) {
        WallsAndGates_286 s = new WallsAndGates_286();
        int[][] rooms1 = {
                {Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
                {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}};
        int[][] rooms2 = {
                {0, -1},
                {Integer.MAX_VALUE, Integer.MAX_VALUE}};
        s.wallsAndGates(rooms1); // [[3,-1,0,1],[2,2,1,1],[1,-1,2,-1],[0,-1,3,4]]
        s.wallsAndGates(rooms2); // [[0,-1],[1,2]]
    }

    // O(rows*cols) - time, space
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        boolean[][] visited = new boolean[rooms.length][rooms[0].length];
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    dfs(i,j,0,visited,rooms);
                }
            }
        }
        SolutionUtils.print(rooms);
    }

    private void dfs(int i, int j, int distance, boolean[][] visited, int[][] rooms) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] == -1) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        if (distance < rooms[i][j]) {
            rooms[i][j] = distance;
        }
        visited[i][j] = true;
        dfs(i,j+1,distance+1,visited, rooms);
        dfs(i+1,j,distance+1,visited, rooms);
        dfs(i,j-1,distance+1,visited, rooms);
        dfs(i-1,j,distance+1,visited, rooms);

        visited[i][j] = false;
    }

    // A bit optimized version, got rid of visited[][]
    // O(rows*cols) - time, O(w) - space, w - max stack depth
    public void wallsAndGates2(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    dfs2(i,j,0,rooms);
                }
            }
        }
        SolutionUtils.print(rooms);
    }

    private void dfs2(int i, int j, int distance, int[][] rooms) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < distance) {
            return;
        }
        if (distance < rooms[i][j]) {
            rooms[i][j] = distance;
        }
        dfs2(i,j+1,distance+1, rooms);
        dfs2(i+1,j,distance+1, rooms);
        dfs2(i,j-1,distance+1, rooms);
        dfs2(i-1,j,distance+1, rooms);
    }
}
