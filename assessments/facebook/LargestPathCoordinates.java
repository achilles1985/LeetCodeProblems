package assessments.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/** M
 * Given a floor plan, which includes the rooms ('O'), the obstacles ('X') and a robot vacuum ('R'),
 * your goal is to return the set of rooms that are farthest from the robot vacuum (when the robot takes the shortest path).
 *   The robot vacuum only move vertically and horizontally (not diagonally). So for instance an example floor plan is
 *
 *     0 1 2 3 4
 * 0   0 0 0 0 0
 * 1   0 0 0 0 0
 * 2   0 0 X 0 0
 * 3   0 R X 0 (this)
 * 4   0 0 X (this) X
 *
 * For which the answers include (4, 3) and (3, 4) (i.e the two tiles in the  bottom right corner) having the distance of 7.
 */
public class LargestPathCoordinates {

    public static void main(String[] args) {
        LargestPathCoordinates s = new LargestPathCoordinates();
        System.out.println(s.findCoordinates(new char[][]{
                {'0','0','0','0','0'},
                {'0','0','0','0','0'},
                {'0','0','X','0','0'},
                {'0','R','X','0','0'},
                {'0','0','X','0','X'}}, 3,1)); //
    }

    // O(n*m) - time, O(width) - space
    public List<Coordinate> findCoordinates(char[][] matrix, int x, int y) {
        if (matrix == null || matrix.length == 0) {
            return Collections.emptyList();
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        Coordinate robot = new Coordinate(x,y,0);
        Queue<Coordinate> queue = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();
        queue.add(robot);
        visited.add(robot);
        List<Coordinate> result = new ArrayList<>();
        int max = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Coordinate> temp = new ArrayList<>();
            int newDistance = queue.peek().distance + 1;
            while (size-- > 0) {
                Coordinate currCoordinate = queue.poll();
                for (int[] direction: directions) {
                    int newX = currCoordinate.x + direction[0];
                    int newY = currCoordinate.y + direction[1];
                    Coordinate newCoordinate = new Coordinate(newX, newY, newDistance);
                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && matrix[newX][newY] == '0' && !visited.contains(newCoordinate)) {
                        queue.add(newCoordinate);
                        visited.add(newCoordinate);
                        temp.add(newCoordinate);
                    }
                }
            }
            if (newDistance > max && !temp.isEmpty()) {
                max = newDistance;
                result = temp;
            }
        }
        return result;
    }

    private static class Coordinate {
        int x;
        int y;
        int distance;

        public Coordinate(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "x=" + x +
                    ", y=" + y +
                    ", distance=" + distance +
                    '}';
        }
    }
}


