package array.hard;

import java.util.ArrayList;
import java.util.List;

/**H
 * Given an m x n binary grid grid where each 1 marks the home of one friend, return the minimal total travel distance.
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * Example 1:
 * Input: grid = [[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * Output: 6
 * Explanation: Given three friends living at (0,0), (0,4), and (2,2).
 * The point (0,2) is an ideal meeting point, as the total travel distance of 2 + 2 + 2 = 6 is minimal.
 * So return 6.
 *
 * Example 2:
 * Input: grid = [[1,1]]
 * Output: 1
 *
 * Constraints:
 *     m == grid.length
 *     n == grid[i].length
 *     1 <= m, n <= 200
 *     grid[i][j] is either 0 or 1.
 *     There will be at least two friends in the grid.
 */
/*
 Can meeting point be at 1? - yes
 */
public class BestMeetingPoint_296 {

    public static void main(String[] args) {
        BestMeetingPoint_296 s = new BestMeetingPoint_296();
        System.out.println(s.minTotalDistanceBF(new int[][]{
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}})); //6
        System.out.println(s.minTotalDistanceBF(new int[][]{
                {1,1,1},
                {1,1,1},
                {1,1,1}})); //1
        System.out.println(s.minTotalDistanceBF(new int[][]{
                {0,0,0},
                {0,0,0},
                {0,0,0}})); //0
        System.out.println(s.minTotalDistanceBF(new int[][]{{1,0,1,0,1}})); //4
    }

    // O(n^2*m^2) - time, O(m*n) - space
    public int minTotalDistanceBF(int[][] grid) {
        List<Point> points = getAllPoints(grid);
        int minDistance = Integer.MAX_VALUE;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                int distance = calculateDistance(points, row, col);
                minDistance = Math.min(distance, minDistance);
            }
        }
        return minDistance;
    }

    private int calculateDistance(List<Point> points, int row, int col) {
        int distance = 0;
        for (Point point : points) {
            distance += Math.abs(point.row - row) + Math.abs(point.col - col);
        }
        return distance;
    }

    private List<Point> getAllPoints(int[][] grid) {
        List<Point> points = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    points.add(new Point(row, col));
                }
            }
        }
        return points;
    }

    public static class Point {
        int row;
        int col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
