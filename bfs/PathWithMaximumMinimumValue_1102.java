package bfs;

import java.util.LinkedList;
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
/*
    Keep adding values to max priority queue (min(curVal, newVal))
 */
public class PathWithMaximumMinimumValue_1102 {

    public static void main(String[] args) {
        PathWithMaximumMinimumValue_1102 s = new PathWithMaximumMinimumValue_1102();
        System.out.println(s.maximumMinimumPath(new int[][]{
                {5,4,5},
                {1,2,6},
                {7,4,6}})); //4
        System.out.println(s.maximumMinimumPath(new int[][]{
                {2,0,2,3,1},
                {0,2,2,3,3},
                {2,3,0,2,3},
                {1,1,2,3,1},
                {2,2,0,0,1}})); //0
        System.out.println(s.maximumMinimumPath(new int[][]{
                {2,2,1,2,2,2},
                {1,2,2,2,1,2}})); //2
        System.out.println(s.maximumMinimumPath(new int[][]{
                {3,4,6,3,4},
                {0,2,1,1,7},
                {8,8,3,2,7},
                {3,2,4,9,8},
                {4,1,2,0,0},
                {4,6,5,4,3}})); //3
    }

    // O(n*m*log(n*m)) - time, O(n*m) - space
    public int maximumMinimumPath(int[][] A) {
        int[][] DIRECTIONS = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
        int rows = A.length;
        int cols = A[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        queue.add(new int[] {0, 0, A[0][0]});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] currPos = queue.poll();
            int x = currPos[0], y = currPos[1];
            if (x == rows -1 && y == cols - 1) {
                return currPos[2];
            }
            for (int[] dir : DIRECTIONS) {
                int newX = currPos[0] + dir[0];
                int newY = currPos[1] + dir[1];
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
                    queue.add(new int[] {newX, newY, Math.min(currPos[2], A[newX][newY]) });
                    visited[newX][newY] = true;
                }
            }
        }

        return -1;
    }

    public int maximumMinimumPath2(int[][] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int rows = A.length;
        int cols = A[0].length;
        int[][] dirs = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
        Queue<int[]> queue = new LinkedList<>();
        int min = Integer.MAX_VALUE;
        boolean[][] seen = new boolean[rows][cols];
        seen[0][0] = true;
        queue.add(new int[]{0,0});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (A[curr[0]][curr[1]] < min) {
                min = A[curr[0]][curr[1]];
            }
            if (curr[0] == rows-1 && curr[1] == cols-1) {
                return min;
            }
            int max = Integer.MIN_VALUE;
            int i = 0, j= 0;
            for (int[] dir: dirs) {
                int newX = curr[0] + dir[0];
                int newY = curr[1] + dir[1];
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !seen[newX][newY]) {
                    if (A[newX][newY] > max) {
                        max = A[newX][newY];
                        i = newX;
                        j = newY;
                    }
                    seen[newX][newY] = true;
                }
            }
            queue.add(new int[]{i,j});
        }
        return -1;
    }
}
