package dfs;

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
                {1,1,1,1,1},
                {1,0,0,0,1},
                {1,0,0,0,1},
                {1,0,0,0,1},
                {1,1,1,1,1}
        }, new int[]{1,1}, new int[]{3,3})); // true
        System.out.println(s.hasPath(new int[][]{
                {1,1,1,1,1,1},
                {1,0,0,0,0,1},
                {1,0,0,0,0,1},
                {1,0,0,0,0,1},
                {1,0,0,0,0,1},
                {1,1,1,1,1,1}
        }, new int[]{1,1}, new int[]{4,4})); // true
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
        if (maze == null || maze.length == 0) {
            return false;
        }
        return dfs(maze, destination, start[0], start[1], new boolean[maze.length][maze[0].length]);
    }

    private boolean dfs(int[][] maze, int[] destination, int i, int j, boolean[][] seen) {
        int rows = maze.length;
        int cols = maze[0].length;
        if (seen[i][j] || maze[i][j] == 1) {
            return false;
        }
        if (i == destination[0] && j == destination[1]) {
            return true;
        }
        seen[i][j] = true;
        int right = j+1, down = i+1, left = j-1, up = i-1;
        while (right < cols && maze[i][right] == 0) { // Go in one direction till the wall
            right++;
        }
        if(dfs(maze,destination,i,right-1,seen)) { // Checks if there is a ball at the wall
            return true;
        }
        while (down < rows && maze[down][j] == 0) {
            down++;
        }
        if (dfs(maze,destination,down-1,j,seen)) {
            return true;
        }
        while (left >= 0 && maze[i][left] == 0) {
            left--;
        }
        if (dfs(maze,destination,i,left+1,seen)) {
            return true;
        }
        while (up >= 0 && maze[up][j] == 0) {
            up--;
        }
        if (dfs(maze,destination,up+1,j,seen)) {
            return true;
        }

        return false;
    }

    // O(n*m) - time, space
    public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
        int[][] directions = new int[][]{{1,0},{-1,0},{0,-1},{0,1}};
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs2(maze, start, destination, directions, visited);
    }

    private boolean dfs2(int[][] maze, int[] start, int[] destination, int[][] directions, boolean[][] visited) {
        if (visited[start[0]][start[1]]) {
            return false;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }

        visited[start[0]][start[1]] = true;
        for (int[] direction: directions) {
            int nextX = start[0] + direction[0];
            int nextY = start[1] + direction[1];
            while (nextX >=0 && nextX < maze.length && nextY >= 0 && nextY < maze[0].length && maze[nextX][nextY] != 1) { // Go in one direction till the wall.
                nextX += direction[0];
                nextY += direction[1];
            }
            if (dfs2(maze, new int[]{nextX - direction[0], nextY - direction[1]}, destination, directions, visited)) { // Checks if there is a ball at the wall.
                return true;
            }
        }

        return false;
    }

}
