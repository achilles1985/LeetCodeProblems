package bfs;

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
        System.out.println(s.maximumMinimumPath(new int[][]{{5,4,5},{1,2,6},{7,4,6}})); //4
        System.out.println(s.maximumMinimumPath(new int[][]{{2,2,1,2,2,2},{1,2,2,2,1,2}})); //2
        System.out.println(s.maximumMinimumPath(new int[][]{{3,4,6,3,4},{0,2,1,1,7},{8,8,3,2,7},{3,2,4,9,8},{4,1,2,0,0},{4,6,5,4,3}})); //3
    }

    // O(n*m*log(n*m)) - time, O(n*m) - space
    public int maximumMinimumPath(int[][] A) {
        int[][] DIRECTIONS = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
        int rows = A.length;
        int cols = A[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        queue.add(new int[] {0, 0, A[0][0]});
        while (!queue.isEmpty()) {
            int[] currPos = queue.poll();
            int x = currPos[0], y = currPos[1];
            visited[x][y] = true;
            if (x == rows -1 && y == cols - 1) {
                return currPos[2];
            }
            for (int[] dir : DIRECTIONS) {
                int newX = currPos[0] + dir[0], newY = currPos[1] + dir[1];
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
                    queue.add(new int[] {newX, newY, Math.min(currPos[2], A[newX][newY]) });
                }
            }
        }

        return -1;
    }

}
