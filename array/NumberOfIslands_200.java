package array;

public class NumberOfIslands_200 {

    public static void main(String[] args) {
        NumberOfIslands_200 s = new NumberOfIslands_200();
        System.out.println(s.numIslands(new char[][] {
                {'1','0','0','1','0'},
                {'1','0','1','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}})); // 2
        System.out.println(s.numIslands(new char[][] {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}})); // 1
        System.out.println(s.numIslands(new char[][] {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}})); // 3
        System.out.println(s.numIslands(new char[][] {
                {'1','1','1','1','0'},
                {'1',1,0,1,0},
                {1,1,0,0,0},
                {0,0,0,0,0}})); // 1
        System.out.println(s.numIslands(new char[][] {
                {1,1,1,1,0},
                {1,1,0,1,0},
                {1,1,0,0,1},
                {0,0,0,1,0}})); // 3
        System.out.println(s.numIslands(new char[][] {})); // 0
        System.out.println(s.numIslands(new char[][] {{}})); // 0
        System.out.println(s.numIslands(new char[][] {{}, {}})); // 0
    }

    // O(rows*columns) - time, space
    public int numIslands(char[][] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int count = 0;
        boolean[][] visited = new boolean[chars.length][chars[0].length];
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[0].length; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (chars[i][j] == '1') {
                    dfs(i, j, visited, chars);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int i, int j, boolean[][] visited, char[][] chars) {
        if (i < 0 || i >= chars.length || j < 0 || j >= chars[0].length || visited[i][j] || chars[i][j] == '0') {
            return;
        }
        visited[i][j] = true;
        dfs(i, j+1, visited, chars);
        dfs(i+1, j, visited, chars);
        dfs(i, j-1, visited, chars);
        dfs(i-1, j, visited, chars);
    }
}
