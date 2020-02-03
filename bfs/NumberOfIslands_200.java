package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid
 * are all surrounded by water.
 *
 * Example 1:
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * Output: 1
 *
 * Example 2:
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * Output: 3
 */
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
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[0].length; j++) {
                if (chars[i][j] == '1') {
                    count++;
                    bfs(i,j,chars);
                }
            }
        }
        return count;
    }

    private void bfs(int i, int j, char[][] chars) {
        int[][] directions = new int[][]{{1,0},{-1,0},{0,-1},{0,1}};
        Queue<int[]> adjacents = new LinkedList<>();
        adjacents.add(new int[]{i,j});
        while (!adjacents.isEmpty()) {
            int[] adjacent = adjacents.poll();
            int row = adjacent[0];
            int col = adjacent[1];
            for (int[] direction: directions) {
                int nextRow = row + direction[0];
                int nextCol = col + direction[1];
                if (nextRow < 0 || nextRow >= chars.length || nextCol < 0 || nextCol >= chars[0].length || chars[nextRow][nextCol] == '0') {
                    continue;
                }
                adjacents.add(new int[]{nextRow, nextCol});
                chars[nextRow][nextCol] = '0';
            }
        }
    }

}
