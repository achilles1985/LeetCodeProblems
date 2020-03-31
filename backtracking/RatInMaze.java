package backtracking;

import utils.SolutionUtils;

/**
 * A Maze is given as N*N binary matrix of blocks where source block is the upper left most block i.e., maze[0][0] and destination block is
 lower rightmost block i.e., maze[N-1][N-1]. A rat starts from source and has to reach the destination. The rat can move only in two directions: forward and down.
 In the maze matrix, 0 means the block is a dead end and 1 means the block can be used in the path from source to destination.
 Note that this is a simple version of the typical Maze problem.
 */
// 0(2^(N*M)) - We have N*M cells and from each cell we need to make 2 choices.
public class RatInMaze {

    public static void main(String[] args) {
        RatInMaze s  = new RatInMaze();
        int maze1[][] = {
                { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 0, 1, 0, 0 },
                { 1, 1, 1, 1 } };
        int maze2[][] = {
                { 1, 1, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 0, 0, 0 },
                { 1, 1, 1, 1 } };
        int maze3[][] = {
                { 1,1,0,1,1,1,0,1 },
                { 1,1,0,1,1,0,1,1 },
                { 1,0,0,1,0,0,1,0 },
                { 1,0,0,1,1,0,1,0 },
                { 1,0,0,0,1,0,1,0 },
                { 1,1,0,0,1,0,1,0 },
                { 0,1,1,1,1,1,0,0 },
                { 1,1,0,0,0,1,1,1 }
        };

        SolutionUtils.print(s.solveMaze(maze1));
        SolutionUtils.print(s.solveMaze(maze2));

    }

    private boolean[][] solveMaze(int[][] maze) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        solveMazeUtils(0, 0, visited, maze);

        return visited;
    }

    private boolean solveMazeUtils(int i, int j, boolean[][] visited, int[][] maze) {
        if (i == maze.length-1 && j == maze[0].length-1) {
            visited[i][j] = true;
            return true;
        }
        if (i < 0 || i > maze.length-1 || j < 0 || j > maze[0].length-1 || maze[i][j] == 0 || visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        boolean right = solveMazeUtils(i, j+1, visited, maze);
        boolean down = solveMazeUtils(i+1, j, visited, maze);
        if (right || down) {
            return true;
        }

        visited[i][j] = false;

        return false;
    }


}
