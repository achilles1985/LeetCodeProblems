package unionfind;

import java.util.Arrays;

/** M
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
//https://leetcode.com/problems/number-of-islands/
/*
    Initially, let's treat each '1' as an island and create parent[m*n] and populate with index number only those cells that marked as '1'.
*/
public class NumberOfIslands_200 {

    public static void main(String[] args) {
        NumberOfIslands_200 s = new NumberOfIslands_200();
        System.out.println(s.numIslands(new char[][] {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}})); // 3
        System.out.println(s.numIslands(new char[][] {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}})); // 1
        System.out.println(s.numIslands(new char[][] {
                {'1','0','0','1','0'},
                {'1','0','1','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}})); // 2
        System.out.println(s.numIslands(new char[][] {})); // 0
        System.out.println(s.numIslands(new char[][] {{}})); // 0
        System.out.println(s.numIslands(new char[][] {{}, {}})); // 0
    }

    // O(n*m) - time (if using union-by-rank), O(n*m) - space
    public int numIslands(char[][] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }

        DisjointSet ds = new DisjointSet(chars);
        int rows = chars.length;
        int cols = chars[0].length;
        for (int row = 0; row < rows; row++) { //n*m
            for (int col = 0; col < cols; col++) {
                if (chars[row][col] == '1') {
                    chars[row][col] = '0';
                    int current = row * cols + col;
                    if (col + 1 < cols && chars[row][col+1] == '1') {
                        ds.union(current, row*cols + col + 1); //1
                    }
                    if (row + 1 < rows && chars[row+1][col] == '1') {
                        ds.union(current, (row+1)*cols + col); //1
                    }
                    if (row - 1 >= 0 && chars[row-1][col] == '1') {
                        ds.union(current, (row-1)*cols + col); //1
                    }
                    if (col-1 >= 0 && chars[row][col-1] == '1') {
                        ds.union(current, row * cols + col - 1); //1
                    }
                }
            }
        }

        return ds.count;
    }

    private static class DisjointSet {
        int count;
        int[] parent;
        int[] rank;

        DisjointSet(char[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            parent = new int[rows*cols];
            rank = new int[rows*cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        parent[i*cols + j] = i*cols + j;
                        count++;
                    }
                }
            }
        }

        int find(int x) { // with path compression, O(1) - time
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) { //union by rank, O(1) - time
            int p1 = find(x);
            int p2 = find(y);
            if (p1 != p2) {
                if (rank[p1] > rank[p2]) {
                    parent[p2] = p1;
                } else if (rank[p2] > rank[p1]) {
                    parent[p1] = p2;
                } else {
                    parent[p2] = p1;
                    rank[p1] += 1;
                }
                count--;
            }
        }
    }
}
