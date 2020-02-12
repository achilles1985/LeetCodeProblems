package dfs;

import java.util.Arrays;

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

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] distances = new int[maze.length][maze[0].length];
        for (int[] distance: distances) {
            Arrays.fill(distance, Integer.MAX_VALUE);
        }
        distances[start[0]][start[1]] = 0;
        dfs(maze, start, distances);

        return distances[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distances[destination[0]][destination[1]];
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
            if (distances[nextRow - direction[0]][nextCol - direction[1]] > distances[start[0]][start[1]] + count) {
                distances[nextRow - direction[0]][nextCol - direction[1]] = distances[start[0]][start[1]] + count;
                dfs(maze, new int[]{nextRow - direction[0], nextCol - direction[1]}, distances);
            }
        }
    }
}