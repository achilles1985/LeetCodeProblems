package bfs;

import java.util.*;

/** H [marked]
 * You are given an m x n grid grid of values 0, 1, or 2, where:
 *     each 0 marks an empty land that you can pass by freely,
 *     each 1 marks a building that you cannot pass through, and
 *     each 2 marks an obstacle that you cannot pass through.
 * You want to build a house on an empty land that reaches all buildings in the shortest total travel distance.
 * You can only move up, down, left, and right.
 * Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * Example 1:
 * Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * Output: 7
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
 * So return 7.
 *
 * Example 2:
 * Input: grid = [[1,0]]
 * Output: 1
 *
 * Example 3:
 * Input: grid = [[1]]
 * Output: -1

 * Constraints:
 *     m == grid.length
 *     n == grid[i].length
 *     1 <= m, n <= 50
 *     grid[i][j] is either 0, 1, or 2.
 *     There will be at least one building in the grid.
 */
public class ShortestDistanceFromAllBuildings_317 {

    public static void main(String[] args) {
        ShortestDistanceFromAllBuildings_317 s = new ShortestDistanceFromAllBuildings_317();
        System.out.println(s.shortestDistance(new int[][]{
                {1,0,2,0,1},
                {0,0,0,0,0},
                {0,0,1,0,0}
        })); // 7
    }

    // O(n*m)^2 - time (in case all cells are houses), O(n*m) - space
    public int shortestDistance(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][][] distances = new int[rows][cols][2]; //[row][col][0] - distance, [row][col][1] - houses
        int houses = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    houses++;
                    bfs(i,j,grid, distances);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (distances[i][j][1] == houses) {
                    min = Math.min(min, distances[i][j][0]);
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }

        return min;
    }

    private void bfs(int i, int j, int[][] grid, int[][][] distances) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dists = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] seen = new boolean[rows][cols];
        queue.add(new int[]{i,j});
        seen[i][j] = true;
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] poll = queue.poll();
                int x = poll[0];
                int y = poll[1];
                if (grid[x][y] == 0) {
                    distances[x][y][0] += steps;
                    distances[x][y][1] += 1;
                }
                for (int[] dis: dists) {
                    int newX = x + dis[0];
                    int newY = y + dis[1];
                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 0 && !seen[newX][newY]) {
                        queue.add(new int[]{newX, newY});
                        seen[newX][newY] = true;
                    }
                }
            }
            steps++;
        }
    }
}
