package heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**H
 * On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
 * Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another
 * 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can
 * swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1,
 * N-1)?
 *
 * Example 1:
 * Input: [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 *
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 *
 * Example 2:
 * Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation:
 *  0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 *
 * The final route is marked in bold.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 *
 * Note:
 *     2 <= N <= 50.
 *     grid[i][j] is a permutation of [0, ..., N*N - 1].
 */
public class SwimInRisingWater_778 {

    public static void main(String[] args) {
        SwimInRisingWater_778 s = new SwimInRisingWater_778();
        System.out.println(s.swimInWater2(new int[][]{
                {0,1,2,3,4},
                {24,23,22,21,5},
                {12,13,14,15,16},
                {11,17,18,19,20},
                {10,9,8,7,6}})); //16
    }

    // O(n^2*log(n*n)) - time, O(n^2) - space
    public int swimInWater(int[][] grid) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (grid[a[0]][a[1]] - grid[b[0]][b[1]]));
        pq.add(new int[]{0, 0});
        int level = 0;
        int n = grid.length;
        int[][] nexts = {{0 ,1}, {0, -1},{1, 0}, {-1, 0}};
        boolean[][] isVisited = new boolean[n][n];
        while (!pq.isEmpty()){
            int[] top = pq.poll();
            level = Math.max(level, grid[top[0]][top[1]]);
            if (top[0] == n - 1 && top[1] == n - 1){
                break;
            }

            for (int[] next : nexts){
                int x = top[0] + next[0];
                int y = top[1] + next[1];
                if (x >= 0 && x < n && y >= 0 && y < n && !isVisited[x][y]){
                    isVisited[top[0]][top[1]] = true;
                    pq.add(new int[]{x, y});
                }
            }
        }

        return level;
    }

    public int swimInWater2(int[][] grid) {
        int N = grid.length;
        int lo = grid[0][0], hi = N * N;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (!possible(mi, grid)) {
                lo = mi + 1;
            } else {
                hi = mi;
            }
        }
        return lo;
    }

    public boolean possible(int T, int[][] grid) {
        int N = grid.length;
        Set<Integer> seen = new HashSet();
        seen.add(0);
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        Stack<Integer> stack = new Stack();
        stack.add(0);

        while (!stack.empty()) {
            int k = stack.pop();
            int r = k / N, c = k % N;
            if (r == N-1 && c == N-1) return true;

            for (int i = 0; i < 4; ++i) {
                int cr = r + dr[i];
                int cc = c + dc[i];
                int ck = cr * N + cc;
                if (0 <= cr && cr < N && 0 <= cc && cc < N && !seen.contains(ck) && grid[cr][cc] <= T) {
                    stack.add(ck);
                    seen.add(ck);
                }
            }
        }

        return false;
    }
}
