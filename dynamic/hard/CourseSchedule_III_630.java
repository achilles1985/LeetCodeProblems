package dynamic.hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/** H
 * There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day.
 * A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.
 * Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.
 *
 * Example:
 * Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * Output: 3
 * Explanation:
 * There're totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 *
 * Note:
 *     The integer 1 <= d, t, n <= 10,000.
 *     You can't take two courses simultaneously.
 */
// https://leetcode.com/problems/course-schedule-iii/discuss/363735/Recursive-or-DP-Top-Down-or-Dp-Bottom-Up-or-Greedy-or-Greedy-Optimised-or-Explanations
public class CourseSchedule_III_630 {

    public static void main(String[] args) {
        CourseSchedule_III_630 s = new CourseSchedule_III_630();
        System.out.println(s.scheduleCourse(new int[][]{{100,200},{200,2300},{1000,1250},{2000,3200}})); //3
    }

    // BF: O(2^n) - time, O(n) length

    // O(len*maxDays) - time, space
    public int scheduleCourse(int[][] courses) {
        if (courses == null || courses.length == 0) {
            return 0;
        }
        Arrays.sort(courses, Comparator.comparing(course -> course[1]));

        return helper(courses, 0, 0, new HashMap<>());
    }

    // O(n*log(n)) - time, O(n) - space
    public int scheduleCourse2(int[][] courses) {
        if (courses == null || courses.length == 0) {
            return 0;
        }

        Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));
        int time = 0;
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int[] course : courses) {
             // Can we pick this course ?
            if (time + course[0] <= course[1]) {
                time = time + course[0];
                maxHeap.offer(course[0]);
            } else if (!maxHeap.isEmpty() && maxHeap.peek() > course[0]) {
                //Find a course by which we can replace this course. For that j'th course duration > current duration. By doing this
                // we can make more days available as duration(j) - duration(i) will be +ve as duration(j) > duration(i)
                //The more duration(j) is bigger than duration(i), more days can be added.
                int maxDuration = maxHeap.poll();
                time = time - maxDuration + course[0];
                maxHeap.offer(course[0]);
            }
        }

        return maxHeap.size();
    }

    private int helper(int[][] courses, int idx, int time, Map<String, Integer> cache) {
        String key = idx + "," + time;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (idx == courses.length) {
            return 0;
        }
        int duration = courses[idx][0];
        int endDate = courses[idx][1];
        int include = 0;
        if (time + duration <= endDate) {
            include = 1 + helper(courses, idx+1, time + duration, cache);
        }
        int exclude = helper(courses, idx+1, time, cache);
        int max = Math.max(include, exclude);
        cache.put(key, max);

        return max;
    }
}
