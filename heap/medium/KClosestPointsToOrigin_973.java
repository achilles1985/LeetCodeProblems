package heap.medium;

// https://leetcode.com/problems/k-closest-points-to-origin/

import java.util.PriorityQueue;
import java.util.Queue;

import utils.SolutionUtils;

/** M
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 (Here, the distance between two points on a plane is the Euclidean distance.)
 You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 Example 1:
 Input: points = [[1,3],[-2,2]], K = 1
 Output: [[-2,2]]
 Explanation:
 The distance between (1, 3) and the origin is sqrt(10).
 The distance between (-2, 2) and the origin is sqrt(8).
 Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

 Example 2:
 Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 Output: [[3,3],[-2,4]]
 (The answer [[-2,4],[3,3]] would also be accepted.)

 Note:
 1 <= K <= points.length <= 10000
 -10000 < points[i][0] < 10000
 -10000 < points[i][1] < 10000
 */
// https://leetcode.com/problems/k-closest-points-to-origin/discuss/345720/JAVA-Evolve-From-O(nlogk)-to-O(n)-Best-Case - possible solution, O(n) - time
public class KClosestPointsToOrigin_973 {

    public static void main(String[] args) {
        KClosestPointsToOrigin_973 s = new KClosestPointsToOrigin_973();
        SolutionUtils.print(s.kClosest(new int[][]{{1,3},{-2,2}}, 1));
        SolutionUtils.print(s.kClosest(new int[][]{{3,3},{5,-1},{-2,4}}, 2));
    }

    // O(n*log(k)) - time, O(k) - space for queue
    public int[][] kClosest(int[][] points, int K) {
        Queue<Point> queue = new PriorityQueue<>();
        for (int[] point: points) {
            queue.add(new Point(point[0], point[1]));
            if (queue.size() > K) {
                queue.poll();
            }
        }

        int[][] res = new int[K][2];
        int i = 0;
        while (!queue.isEmpty()) {
            res[i++] = queue.poll().getCoordinates();
        }

        return res;
    }

    private static class Point implements Comparable<Point> {
        int x;
        int y;
        int hypo;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.hypo = x*x + y*y;
        }

        int[] getCoordinates() {
            return new int[] {x,y};
        }

        @Override
        public int compareTo(Point o) {
            return o.hypo - hypo;
        }
    }
}
