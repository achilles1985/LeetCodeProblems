package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**M
 Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land,
 find a water cell such that its distance to the nearest land cell is maximized and return the distance.
 The distance used in this problem is the Manhattan distance:
 the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 If no land or water exists in the grid, return -1.

 Example 1:
 Input: [[1,0,1],[0,0,0],[1,0,1]]
 Output: 2
 Explanation:
 The cell (1, 1) is as far as possible from all the land with distance 2.

 Example 2:
 Input: [[1,0,0],[0,0,0],[0,0,0]]
 Output: 4
 Explanation:
 The cell (2, 2) is as far as possible from all the land with distance 4.
 */
/*
    Tips: For BFS solution, spread from all the lands at the same time.
 */
public class AsFarFromLandAsPossible_1162 {

    public static void main(String[] args) {
        AsFarFromLandAsPossible_1162 s = new AsFarFromLandAsPossible_1162();
        System.out.println(s.maxDistance3(new int[][]{
                {1,0,0,0,1},
                {0,0,0,0,0},
                {0,0,1,0,1},
                {0,0,0,0,0},
                {1,0,0,0,1}})); //2
        System.out.println(s.maxDistance3(new int[][]{
                {1,0,1},
                {0,0,0},
                {1,0,0}})); //2
        System.out.println(s.maxDistance3(new int[][]{
                {1,0,0},
                {0,0,0},
                {0,0,0}})); //4
        System.out.println(s.maxDistance2(new int[][]{
                {0,0,0},
                {0,0,0},
                {0,0,0}})); //-1
        System.out.println(s.maxDistance2(new int[][]{
                {1,1,1},
                {1,1,1},
                {1,1,1}})); //-1
    }

    // O(n^3) - time, O(n^2) - space
    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        List<Coordinate> lands = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    lands.add(new Coordinate(i,j));
                }
            }
        }
        if (lands.isEmpty() || lands.size() == grid.length*grid[0].length) {
            return -1;
        }
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    for (Coordinate coordinate: lands) {
                        int distance = Math.abs(coordinate.x - i) + Math.abs(coordinate.y - j);
                        max = Math.max(max, distance);
                    }
                }
            }
        }
        return max;
    }

    // https://leetcode.com/problems/as-far-from-land-as-possible/discuss/422691/7ms-DP-solution-with-example-beats-100
    // O(n^2) - time, O(1) - space
    public int maxDistance2(int[][] grid) {
        int n = grid.length, m = grid[0].length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }
                grid[i][j] = 201; //201 here cuz as the despription, the size won't exceed 100.
                if (i > 0) {
                    grid[i][j] = Math.min(grid[i][j], grid[i-1][j] + 1);
                }
                if (j > 0) {
                    grid[i][j] = Math.min(grid[i][j], grid[i][j-1] + 1);
                }
            }
        }

        for (int i = n-1; i > -1; i--) {
            for (int j = m-1; j > -1; j--) {
                if (grid[i][j] == 1) {
                    continue;
                }
                if (i < n-1) {
                    grid[i][j] = Math.min(grid[i][j], grid[i+1][j] + 1);
                }
                if (j < m-1) {
                    grid[i][j] = Math.min(grid[i][j], grid[i][j+1] + 1);
                }
                res = Math.max(res, grid[i][j]); //update the maximum
            }
        }

        return res == 201 ? -1 : res - 1;
    }

    // O(n*m) - time, space. Do BFS from all the lands at the same time.
    public int maxDistance3(int[][] grid) {
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i,j});
                }
            }
        }
        int level = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cell = queue.poll();
                int x = cell[0];
                int y = cell[1];
                for (int[] direction: directions) {
                    int nextX = x + direction[0];
                    int nextY = y + direction[1];
                    if (nextX >=0 && nextX < grid.length && nextY >= 0 & nextY < grid[0].length && !visited[nextX][nextY] && grid[nextX][nextY] == 0) {
                        queue.add(new int[]{nextX, nextY});
                        visited[x][y] = true;
                    }
                }
            }
            level++;
        }
        return level <= 0 ? -1 : level;
    }

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
