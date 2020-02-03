package unionfind;

import java.util.PriorityQueue;
import java.util.Queue;

/**M
 * Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and
 * ending at [R-1,C-1].
 * The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.
 * A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4
 * cardinal directions (north, east, west, south).
 *
 * Example 1:
 * Input: [[5,4,5],[1,2,6],[7,4,6]]
 * Output: 4
 * Explanation:
 * The path with the maximum score is highlighted in yellow.
 *
 * Example 2:
 * Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
 * Output: 2
 *
 * Example 3:
 * Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
 * Output: 3
 *
 * Note:
 *     1 <= R, C <= 100
 *     0 <= A[i][j] <= 10^9
 */
public class PathWithMaximumMinimumValue_1102 {

    public static void main(String[] args) {
        PathWithMaximumMinimumValue_1102 s = new PathWithMaximumMinimumValue_1102();
        System.out.println(s.maximumMinimumPath(new int[][]{{5,4,5},{1,2,6},{7,4,6}})); //4
        System.out.println(s.maximumMinimumPath(new int[][]{{2,2,1,2,2,2},{1,2,2,2,1,2}})); //2
        System.out.println(s.maximumMinimumPath(new int[][]{{3,4,6,3,4},{0,2,1,1,7},{8,8,3,2,7},{3,2,4,9,8},{4,1,2,0,0},{4,6,5,4,3}})); //3
    }

    // O(n*m*log(n*m)) - time, O(n*m) - space
    public int maximumMinimumPath(int[][] A) {
        if(A == null || A.length == 0 || A[0] == null || A[0].length == 0) {
            return 0;
        }
        int rows = A.length, cols = A[0].length;
        // int[] a = [x, y, value];
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> b[2]-a[2]);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                queue.add(new int[] {i, j, A[i][j]});
            }
        }

        UnionFind uf = new UnionFind(rows, cols);
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = {{0,1},{0,-1},{-1,0},{1,0}};
        while(queue.size() != 0) {
            int[] top = queue.poll();
            visited[top[0]][top[1]] = true;
            for(int i = 0; i < directions.length; i++) {
                int[] direction = directions[i];
                int row = top[0] + direction[0];
                int col = top[1] + direction[1];
                if(row >= 0 && row < rows && col >= 0 && col < cols && visited[row][col]) {
                    int curIdx = top[0] * cols + top[1];
                    int newIdx = row * cols + col;
                    uf.union(newIdx, curIdx);
                }
            }

            if(uf.connected()) {
                return top[2];
            }
        }

        return -1;
    }

    class UnionFind {
        private int[] parents;
        public UnionFind(int m, int n) {
            parents = new int[m * n];
            for(int i = 0; i < parents.length; i++) parents[i] = i;
        }

        public boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if(pa == pb) {
                return false;
            }
            parents[pb] = pa;

            return true;
        }

        public int find(int a) {
            if(parents[a] == a) {
                return a;
            }
            parents[a] = find(parents[a]);

            return parents[a];
        }

        public boolean connected() {
            return find(0) == find(parents.length-1);
        }
    }
}
