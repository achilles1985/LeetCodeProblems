package intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**M
 * Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers x such that a <= x < b.
 * We remove the intersections between any interval in intervals and the interval toBeRemoved.
 * Return a sorted list of intervals after all such removals.
 *
 * Example 1:
 * Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
 * Output: [[0,1],[6,7]]
 *
 * Example 2:
 * Input: intervals = [[0,5]], toBeRemoved = [2,3]
 * Output: [[0,2],[3,5]]
 *
 * Constraints:
 *     1 <= intervals.length <= 10^4
 *     -10^9 <= intervals[i][0] < intervals[i][1] <= 10^9
 */
/*
    Sweep line algorithm
 */
public class RemoveInterval_1272 {

    public static void main(String[] args) {
        RemoveInterval_1272 s = new RemoveInterval_1272();
        System.out.println(s.removeInterval(new int[][]{{0,100}}, new int[]{0,50})); //[[50,100]]
        System.out.println(s.removeInterval(new int[][]{{0,100}}, new int[]{50,100})); //[[0,50]]

        System.out.println(s.removeInterval(new int[][]{{0,2},{3,4},{5,7}}, new int[]{1,6})); //[[0,1],[6,7]]
        System.out.println(s.removeInterval(new int[][]{{0,5}}, new int[]{2,3})); //[[0,2],[3,5]]
        System.out.println(s.removeInterval(new int[][]{{0,1},{4,5}}, new int[]{2,3})); //[[0,1],[4,5]]
        System.out.println(s.removeInterval(new int[][]{{-5,-4},{-3,-2},{1,2},{3,5},{8,9}}, new int[]{-1,4})); //[[-5,-4],[-3,-2],[4,5],[8,9]]
    }

    // O(n) - time, O(n) - space, n - number of intervals
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> result = new ArrayList<>();
        int toRemovedStart = toBeRemoved[0];
        int toBeRemovedEnd = toBeRemoved[1];
        for (int[] interval: intervals) {
            int start = interval[0];
            int end = interval[1];
            if (end <= toRemovedStart || toBeRemovedEnd <= start) { // interval is outside of remove interval
                result.add(Arrays.asList(start, end));
            } else if (start < toRemovedStart && end <= toBeRemovedEnd) { // left overlap
                result.add(Arrays.asList(start, toRemovedStart));
            } else if (start >= toRemovedStart && end > toBeRemovedEnd) { // right overlap
                result.add(Arrays.asList(toBeRemovedEnd, end));
            } else if (start < toRemovedStart && end > toBeRemovedEnd) { // remove interval is inside interval
                result.add(Arrays.asList(start, toRemovedStart));
                result.add(Arrays.asList(toBeRemovedEnd, end));
            }
        }
        return result;
    }
}
