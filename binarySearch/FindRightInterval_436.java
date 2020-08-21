package binarySearch;

import java.util.NavigableMap;
import java.util.TreeMap;

import utils.SolutionUtils;

// https://leetcode.com/problems/find-right-interval/
/** M
 Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point
 is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.
 For any interval i, you need to store the minimum interval j's index, which means that the interval j
 has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i.
 Finally, you need output the stored value of each interval as an array.
 Note:
 You may assume the interval's end point is always bigger than its start point.
 You may assume none of these intervals have the same start point.

 Example 1:
 Input: [ [1,2] ]
 Output: [-1]
 Explanation: There is only one interval in the collection, so it outputs -1.

 Example 2:
 Input: [ [3,4], [2,3], [1,2] ]
 Output: [-1, 0, 1]
 Explanation: There is no satisfied "right" interval for [3,4].
 For [2,3], the interval [3,4] has minimum-"right" start point;
 For [1,2], the interval [2,3] has minimum-"right" start point.
 */
public class FindRightInterval_436 {

    public static void main(String[] args) {
        FindRightInterval_436 s = new FindRightInterval_436();
        SolutionUtils.print(s.findRightInterval(new int[][]{{1,2}}));
        SolutionUtils.print(s.findRightInterval(new int[][]{{3,4}, {2,3}, {1,2}}));
        SolutionUtils.print(s.findRightInterval(new int[][]{{1,4}, {2,3}, {3,4}}));
    }

    // O(n*log(n)) - time, O(n) - space
    public int[] findRightInterval(int[][] intervals) {
        NavigableMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i][0], i);
        }

        int res[] = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            Integer greater = map.ceilingKey(intervals[i][1]);
            res[i] = greater != null ? map.get(greater) : -1;
        }

        return res;
    }
}
