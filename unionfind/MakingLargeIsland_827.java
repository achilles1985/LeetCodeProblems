package unionfind;

import java.util.HashSet;
import java.util.Set;

/** H
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 * Return the size of the largest island in grid after applying this operation.
 * An island is a 4-directionally connected group of 1s.
 *
 * Example 1:
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 *
 * Example 2:
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 *
 * Example 3:
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 *
 * Constraints:
 *     n == grid.length
 *     n == grid[i].length
 *     1 <= n <= 500
 *     grid[i][j] is either 0 or 1.
 */
public class MakingLargeIsland_827 {

    public static void main(String[] args) {
        MakingLargeIsland_827 s = new MakingLargeIsland_827();
        System.out.println(s.largestIsland(new int[][]{
                {1,0,0,1,1},
                {1,1,0,1,1},
                {0,0,0,1,1},
                {1,1,0,1,1}
        })); //4
        System.out.println(s.largestIsland(new int[][]{
                {1,1},
                {1,0}
        })); //4
    }

    // O(rows*cols)^2 - time, O(rows*cols) - space
    public int largestIslandBF(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int max = 0;
        boolean hasZero = false;
        for (int i= 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    hasZero = true;
                    grid[i][j] = 1;
                    max = Math.max(max, dfs(i,j,grid, new boolean[rows][cols]));
                    grid[i][j] = 0;
                    if (max == rows*cols) {
                        return max;
                    }
                }
            }
        }

        return hasZero ? max : rows*cols;
    }

    // O(rows*cols) - time, O(rows*cols) - space
    public int largestIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] max = new int[1];
        UnionFind uf = new UnionFind(rows * cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    unionAround(uf, grid, i, j, max);
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    int[][] neighbors = new int[][]{{ i - 1, j }, { i + 1, j }, { i, j - 1 }, { i, j + 1 }};
                    int connect = 0;
                    Set<Integer> set = new HashSet<>();
                    for (int[] neighbor : neighbors) {
                        int ni = neighbor[0];
                        int nj = neighbor[1];
                        if (valid(rows, cols, ni, nj) && grid[ni][nj] == 1) {
                            set.add(uf.find(rows * ni + nj));
                        }
                    }
                    for (int node : set) {
                        connect += uf.count[node];
                    }
                    max[0] = Math.max(max[0], connect + 1);
                }
            }
        }

        return max[0];
    }

    private void unionAround(UnionFind uf, int[][] grid, int i, int j, int[] max) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] neighbors = new int[][]{{i,j+1},{i+1,j},{i,j-1},{i+1,j}};
        for (int[] neighbor : neighbors) {
            int ni = neighbor[0];
            int nj = neighbor[1];
            if (valid(rows, cols, ni, nj) && grid[ni][nj] == 1) {
                uf.union(rows * i + j, rows * ni + nj);
            }
        }
        max[0] = Math.max(max[0], uf.count[uf.find(i * rows + j)]);
    }

    private boolean valid(int m, int n, int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    private int dfs(int i, int j, int[][] grid, boolean[][] seen) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || seen[i][j] || grid[i][j] == 0) {
            return 0;
        }
        seen[i][j] = true;

        return 1 + dfs(i, j+1, grid, seen) + dfs(i+1, j, grid, seen) + dfs(i, j-1, grid, seen) + dfs(i-1, j, grid, seen);
    }

    private static class UnionFind {
        int[] parent;
        int[] count;
        public UnionFind(int n) {
            this.parent = new int[n];
            this.count = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                count[i] = 1;
            }
        }
        public void union(int a, int b) {
            int parent1 = find(a);
            int parent2 = find(b);
            if (parent1 != parent2) {
                parent[parent2] = parent1;
                count[parent1] += count[parent2];
            }
        }
        public int find(int a) {
            while (a != parent[a]) {
                a = parent[parent[a]];
            }
            return a;
        }
    }
}
