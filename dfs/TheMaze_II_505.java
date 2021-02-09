package dfs;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**M
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down,
 * left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next
 * direction.
 *
 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at
 * the destination. The distance is defined by the number of empty spaces traveled by the ball from the start
 * position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
 *
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that
 * the borders of the maze are all walls. The start and destination coordinates are represented by row and column
 * indexes.
 *
 * Example 1:
 * Input 1: a maze represented by a 2D array
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 * Output: 12
 * Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 *              The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
 *
 *              Input 1: a maze represented by a 2D array
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 * Output: -1
 * Explanation: There is no way for the ball to stop at the destination.
 *
 * Note:
 *     There is only one ball and one destination in the maze.
 *     Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 *     The given maze does not contain border (like the red rectangle in the example pictures), but you could assume
 *     the border of the maze are all walls.
 *     The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
public class TheMaze_II_505 {

    public static void main(String[] args) {
        TheMaze_II_505 s = new TheMaze_II_505();
        System.out.println(s.shortestDistance(new int[][]{
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        }, new int[]{0,4}, new int[]{4,4})); // 12
        System.out.println(s.shortestDistance(new int[][]{
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        }, new int[]{0,4}, new int[]{3,2})); // -1
    }

    // O(m*n*max(m,n)) - time, O(m*n) - space
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] distances = new int[maze.length][maze[0].length];
        for (int[] distance: distances) {
            Arrays.fill(distance, Integer.MAX_VALUE);
        }
        distances[start[0]][start[1]] = 0;
        dfs(maze, start, distances);

        return distances[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distances[destination[0]][destination[1]];
    }

    // O(m*n*log(m*n)) - time, O(m*n) - space
    public int shortestDistance2(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        dijkstra(maze, start, distance);

        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    private void dijkstra(int[][] maze, int[] start, int[][] distance) {
        int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
        Queue<int[] > queue = new PriorityQueue< >((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{start[0],start[1],0});
        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            if(distance[s[0]][s[1]] < s[2])
                continue;
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                    queue.offer(new int[]{x - dir[0], y - dir[1], distance[x - dir[0]][y - dir[1]]});
                }
            }
        }
    }

    private void dfs(int[][] maze, int[] start, int[][] distances) {
        int[][] directions = new int[][]{{1,0},{-1,0},{0,-1},{0,1}};
        for (int[] direction: directions) {
            int nextRow = start[0] + direction[0];
            int nextCol = start[1] + direction[1];
            int count = 0;
            while (nextRow >= 0 && nextRow < maze.length && nextCol >= 0 && nextCol < maze[0].length && maze[nextRow][nextCol] == 0) {
                nextRow += direction[0];
                nextCol += direction[1];
                count++;
            }
            if (distances[start[0]][start[1]] + count < distances[nextRow - direction[0]][nextCol - direction[1]]) {
                distances[nextRow - direction[0]][nextCol - direction[1]] = distances[start[0]][start[1]] + count;
                dfs(maze, new int[]{nextRow - direction[0], nextCol - direction[1]}, distances);
            }
        }
    }
}
