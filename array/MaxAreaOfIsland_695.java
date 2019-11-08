package array;

public class MaxAreaOfIsland_695 {

    public static void main(String[] args) {
        MaxAreaOfIsland_695 s = new MaxAreaOfIsland_695();

        System.out.println(s.maxAreaOfIsland(new int[][]{
                {0,1},
                {1,1}})); //3
        System.out.println(s.maxAreaOfIsland(new int[][]{
                {1,1,1,1,0},
                {1,1,0,1,0},
                {1,1,0,0,1},
                {0,0,0,1,0}})); //9
        System.out.println(s.maxAreaOfIsland(new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}})); // 6
        System.out.println(s.maxAreaOfIsland(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}})); //0
    }

    // O(rows*columns) - time, space
    public int maxAreaOfIsland(int[][] grid) {
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
