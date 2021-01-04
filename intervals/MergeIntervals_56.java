package intervals;

import java.util.Comparator;
import utils.SolutionUtils;

import java.util.Arrays;
import java.util.Stack;

/**M [marked]
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
/*
    Questions:
    1. Any ordering of the result?
    2. Can it be like [[1,5],[2,4]]? - [1,5] then?
 */
public class MergeIntervals_56 {

    public static void main(String[] args) {
        MergeIntervals_56 s = new MergeIntervals_56();
        SolutionUtils.print(s.merge(new int[][]{{2,6},{1,3},{8,10},{15,18}})); //[[1,6],[8,10],[15,18]]
        SolutionUtils.print(s.merge(new int[][]{{1,4},{4,5}})); //[[1,5]]

        SolutionUtils.print(s.merge2(new int[][]{{2,6},{1,3},{8,10},{15,18}})); //[[1,6],[8,10],[15,18]]
    }

    // O(n*log(n)) - time, O(n) - space, n - number of intervals
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{};
        }
        Arrays.sort(intervals, (e1, e2) -> (e1[0] - e2[0]));
        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= stack.peek()[1]) {
                int[] popped = stack.pop();
                stack.push(new int[]{popped[0], Math.max(popped[1], intervals[i][1])});
            } else {
                stack.push(intervals[i]);
            }
        }
        return stack.toArray(new int[][]{});
    }

    // Incorrect since while is cyclic
    public int[][] merge2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return null;
        }
        Arrays.sort(intervals, Comparator.comparing(e -> e[0]));
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            while (!stack.isEmpty() && stack.peek()[1] >= interval[0]) {
                int[] popped = stack.pop();
                stack.push(new int[]{popped[0], Math.max(popped[1], interval[1])});
            }
            stack.push(interval);
        }
        int[][] result = new int[stack.size()][];
        int i = 0;
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();
        }

        return result;
    }
}
