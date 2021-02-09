package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

/** M
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
 * where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0),
 * and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right,
 * and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 * Example 1
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 *
 * Example 2
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 *
 * Example 3
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 * Constraints:
 *     rows == heights.length
 *     columns == heights[i].length
 *     1 <= rows, columns <= 100
 *     1 <= heights[i][j] <= 106
 */
public class PathWithMinimumEffort_1631 {

    public static void main(String[] args) {
        PathWithMinimumEffort_1631 s = new PathWithMinimumEffort_1631();
        System.out.println(s.minimumEffortPath3(new int[][]{
                {1,2,2},
                {3,8,2},
                {5,3,5}}));
        System.out.println(s.minimumEffortPath(new int[][]{
                {1,2,3},
                {3,8,4},
                {5,3,5}}));
        System.out.println(s.minimumEffortPath(new int[][]{
                {1,2,1,1,1},
                {1,2,1,2,1},
                {1,2,1,2,1},
                {1,2,1,2,1},
                {1,1,1,2,1}}));
    }
    // O(n*m*log(n*m)) - time, O(n*m) - space
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length, m = heights[0].length;
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->(a[0]-b[0]));
        int[] dir = new int[]{0, 1, 0, -1, 0};
        pq.add(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int effort = top[0];
            int i = top[1];
            int j = top[2];
            if (effort > dist[i][j]) continue;
            if (i == n - 1 && j == m - 1) {
                return effort;
            }
            for (int k = 0; k < 4; k++) {
                int a = i + dir[k];
                int b = j + dir[k + 1];
                if (a >= 0 && a < n && b >= 0 && b < m) {
                    int newDist = Math.max(effort, Math.abs(heights[a][b] - heights[i][j]));
                    if (dist[a][b] > newDist) {
                        dist[a][b] = newDist;
                        pq.add(new int[]{dist[a][b], a, b});
                    }
                }
            }
        }
        return 0;
    }

    // since we know height ranges, we can do binary search
    // O(n*m) - time, space.
    public int minimumEffortPath2(int[][] heights) {
        int left = 0;
        int right = 1000000;
        int result = right;
        while (left <= right) { // O(log(10^6))=O(1), where maxHeight=10^6
            int mid = (left + right) / 2;
            if (canReachDestinaton(heights, mid)) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    public int minimumEffortPath3(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        if (rows == 1 && cols == 1) {
            return 0;
        }
        UnionFind unionFind = new UnionFind(rows*cols);
        List<Edge> edgeList = new ArrayList<>();
        for (int currentRow = 0; currentRow < rows; currentRow++) {
            for (int currentCol = 0; currentCol < cols; currentCol++) {
                if (currentRow > 0) {
                    edgeList.add(new Edge(
                            currentRow * cols + currentCol,
                            (currentRow - 1) * cols + currentCol,
                            Math.abs(heights[currentRow][currentCol] - heights[currentRow - 1][currentCol]))
                    );
                }
                if (currentCol > 0) {
                    edgeList.add(new Edge(
                            currentRow * cols + currentCol,
                            currentRow * cols + currentCol - 1,
                            Math.abs(heights[currentRow][currentCol] - heights[currentRow][currentCol - 1]))
                    );
                }
            }
        }
        Collections.sort(edgeList, (e1, e2) -> e1.difference - e2.difference);
        for (int i = 0; i < edgeList.size(); i++) {
            int x = edgeList.get(i).x;
            int y = edgeList.get(i).y;
            unionFind.union(x, y);
            if (unionFind.find(0) == unionFind.find(rows * cols - 1)) {
                return edgeList.get(i).difference;
            }
        }
        return -1;
    }

    // use bfs to check if we can reach destination with max absolute difference k
    private boolean canReachDestinaton(int[][] heights, int k) { // BFS takes V+E, where V=m*n, E=m*n, n*m + n*m = n*m
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int row = heights.length;
        int col = heights[0].length;
        Deque<Cell> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[heights.length][heights[0].length];
        queue.addLast(new Cell(0, 0));
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Cell curr = queue.removeFirst();
            if(curr.x == row - 1 && curr.y == col - 1) {
                return true;
            }
            for (int[] direction : directions) {
                int adjacentX = curr.x + direction[0];
                int adjacentY = curr.y + direction[1];
                if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                    int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[curr.x][curr.y]);
                    if (currentDifference <= k) {
                        visited[adjacentX][adjacentY] = true;
                        queue.addLast(new Cell(adjacentX, adjacentY));
                    }
                }
            }
        }
        return false;
    }

    boolean isValidCell(int x, int y, int row, int col) {
        return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
    }

    private static class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int p1 = find(x);
            int p2 = find(y);
            if (p1 != p2) {
                if (rank[p1] > rank[p2]) {
                    parent[p2] = p1;
                } else if (rank[p2] > rank[p1]) {
                    parent[p1] = p2;
                } else {
                    parent[p2] = p1;
                    rank[p1]++;
                }
            }
        }
    }

    private static class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge {
        int x;
        int y;
        int difference;

        Edge(int x, int y, int difference) {
            this.x = x;
            this.y = y;
            this.difference = difference;
        }
    }
}
