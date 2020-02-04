package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**M
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down,
 * left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next
 * direction.
 *
 * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the
 * destination.
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
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 *
 * Example 2:
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination.
 *
 * Note:
 *     There is only one ball and one destination in the maze.
 *     Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 *     The given maze does not contain border (like the red rectangle in the example pictures), but you could assume
 *     the border of the maze are all walls.
 *     The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
/*
    Choose a direction and go till wall is reached. That check if destination exists in that spot.
 */
public class TheMaze_490 {

    public static void main(String[] args) {
        TheMaze_490 s = new TheMaze_490();
        System.out.println(s.hasPath(new int[][]{
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        }, new int[]{0,4}, new int[]{4,4})); // true
        System.out.println(s.hasPath(new int[][]{
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        }, new int[]{0,4}, new int[]{3,2})); // false
    }

    // O(n*m) - time, space
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int[][] directions = new int[][]{{1,0},{-1,0},{0,-1},{0,1}};
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        Queue<int[]> neighbors = new LinkedList<>();
        neighbors.add(start);
        visited[start[0]][start[1]] = true;
        while (!neighbors.isEmpty()) {
            int size = neighbors.size();
            while (size-- > 0) {
                int[] cell = neighbors.poll();
                int row = cell[0];
                int col = cell[1];
                if (row == destination[0] && col == destination[1]) {
                    return true;
                }
                for (int[] direction: directions) {
                    int nextRow = row + direction[0];
                    int nextCol = col + direction[1];
                    while (nextRow >= 0 && nextRow < maze.length && nextCol >= 0 && nextCol < maze[0].length && maze[nextRow][nextCol] == 0) {
                        nextRow += direction[0];
                        nextCol += direction[1];
                    }
                    if (!visited[nextRow - direction[0]][nextCol - direction[1]]) {
                        neighbors.add(new int[]{nextRow - direction[0], nextCol - direction[1]});
                        visited[nextRow - direction[0]][nextCol - direction[1]] = true;
                    }
                }
            }
        }
        return false;
    }



}

