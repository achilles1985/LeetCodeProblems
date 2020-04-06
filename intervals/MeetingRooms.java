package intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** E
 Given an array of meeting time intervals consisting of start and end times [s1, e1], [s2, e2], ... ,
 determine if a person could attend all meetings.

 For example,
 Given [ [0, 30], [5, 10], [15, 20] ],
 return false.
 */
public class MeetingRooms {

    public static void main(String[] args) {
        MeetingRooms s = new MeetingRooms();

        System.out.println(s.canAttendMeetings2(new int[][]{{5,10},{0,30},{15,20}})); // false
        System.out.println(s.canAttendMeetings2(new int[][]{{10,20},{5,10},{25,30}})); // true
    }

    // O(n*log(n)) - time, O(n) - space
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return false;
        }
        List<Interval> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            list.add(new Interval(intervals[i][0], intervals[i][1]));
        }

        Collections.sort(list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getStart() < list.get(i-1).getEnd()) {
                return false;
            }
        }

        return true;
    }

    // O(n*log(n)) - time, O(1) - space
    public boolean canAttendMeetings2(int[][] intervals) {
        Arrays.sort(intervals, (i1,i2) -> Integer.compare(i1[0], i2[0]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1]) {
                return false;
            }
        }
        return true;
    }

    private static class Interval implements Comparable<Interval> {
        private int start;
        private int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public int compareTo(Interval other) {
            if (start == other.start) {
                return 0;
            }
            return start < other.start ? -1 : 1;
        }
    }
}
