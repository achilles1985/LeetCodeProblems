package unionfind;

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
/*
    Also can be solved with Heap.
 */
public class SwimInRisingWater_778 {

    public static void main(String[] args) {
        SwimInRisingWater_778 s = new SwimInRisingWater_778();
        System.out.println(s.swimInWater(new int[][]{
                {0,1,2,3,4},
                {24,23,22,21,5},
                {12,13,14,15,16},
                {11,17,18,19,20},
                {10,9,8,7,6}})); //16
        System.out.println(s.swimInWater(new int[][]{
                {0,2},
                {1,3}})); //16
    }

    // O(n^2) - time, O(n^2) - space
    public int swimInWater(int[][] grid) {
        DisjointSet ds = new DisjointSet(grid.length*grid[0].length);
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        int[] valueToIndex = new int[grid.length * grid.length]; // since the max number in the matrix equals the number of elements in the matrix - 1.
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid.length; ++j) {
                valueToIndex[grid[i][j]] = i * grid.length + j;
            }
        }

        for (int time = 0; time < grid.length * grid.length; ++time) {
            int idx = valueToIndex[time];
            int row = idx / grid.length;
            int col = idx % grid.length;

            for (int[] direction : directions) {
                int rr = row + direction[0];
                int cc = col + direction[1];
                if (rr >= 0 && rr < grid.length && cc >= 0 && cc < grid.length && grid[rr][cc] < time) {
                   ds.union(idx, rr * grid.length + cc);
                }
            }

            if (ds.connected(0, grid.length * grid.length - 1)) {
                return time;
            }
        }

        return -1;
    }

    private static class DisjointSet {
        int[] parent;
        int[] rank;

        DisjointSet(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        int find(int node) {
            if (node != parent[node]) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        void union(int n1, int n2) {
            int p1 = find(n1);
            int p2 = find(n2);
            if (p1 != p2) {
                if (rank[p1] > rank[p2]) {
                    parent[p2] = p1;
                } else if (rank[p1] < rank[p2]) {
                    parent[p1] = p2;
                } else {
                    parent[p2] = p1;
                    rank[p1]++;
                }
            }
        }

        public boolean connected(int node1, int node2) {
            return find(node1) == find(node2);
        }
    }
}
