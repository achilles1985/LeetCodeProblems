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
public class MeetingRooms_252 {

    public static void main(String[] args) {
        MeetingRooms_252 s = new MeetingRooms_252();
        System.out.println(s.canAttendMeetings3(new int[][]{{13,15},{1,13}})); // true
        System.out.println(s.canAttendMeetings3(new int[][]{{5,10},{0,30},{15,20}})); // false
        System.out.println(s.canAttendMeetings3(new int[][]{{10,20},{5,10},{25,30}})); // true
    }

    // O(n*log(n)) - time, O(1) - space
    public boolean canAttendMeetings3(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals.length == 1) {
            return true;
        }
        Arrays.sort(intervals, (e1,e2) -> e1[0] - e2[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1]) {
                return false;
            }
        }
        return true;
    }
}
