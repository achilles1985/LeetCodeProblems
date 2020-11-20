package array.medium;

import java.util.LinkedList;
import java.util.Queue;

public class MaxAreaOfIsland_695 {

    public static void main(String[] args) {
        MaxAreaOfIsland_695 s = new MaxAreaOfIsland_695();

        System.out.println(s.maxAreaOfIslandBFS(new int[][]
                {{1}})); //1
        System.out.println(s.maxAreaOfIslandBFS(new int[][]{
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}})); //4

        System.out.println(s.maxAreaOfIslandDFS(new int[][]{
                {0,1},
                {1,1}})); //3
        System.out.println(s.maxAreaOfIslandDFS(new int[][]{
                {1,1,1,1,0},
                {1,1,0,1,0},
                {1,1,0,0,1},
                {0,0,0,1,0}})); //9
        System.out.println(s.maxAreaOfIslandDFS(new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}})); // 6
        System.out.println(s.maxAreaOfIslandDFS(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}})); //0
    }

    // O(rows*columns) - time, space
    public int maxAreaOfIslandDFS(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int max = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    max = Math.max(max, dfs(i,j,visited,grid));
                }
            }
        }
        return max;
    }

    // O(rows*cols) - time, O(min(rows,cols) - space
    public int maxAreaOfIslandBFS(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int max = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    grid[row][col] = 0;
                    max = Math.max(max, bfs(grid,row,col));
                }
            }
        }
        return max;
    }

    private int bfs(int[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i,j});
        int count = 0;
        while (!queue.isEmpty()) {
            int[] coordinate = queue.poll();
            int row = coordinate[0];
            int col = coordinate[1];
            count++;
            for (int[] direction: directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                    queue.add(new int[]{newRow, newCol});
                    grid[newRow][newCol] = 0;
                }
            }
        }
        return count;
    }

    private int dfs(int i, int j, boolean[][] visited, int[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j] || grid[i][j] == 0) {
            return 0;
        }
        visited[i][j] = true;
        int right = dfs(i, j+1, visited, grid);
        int down = dfs(i+1, j, visited, grid);
        int left = dfs(i, j-1, visited, grid);
        int up = dfs(i-1, j, visited, grid);

        return right + down + left + up + 1;
    }
}
