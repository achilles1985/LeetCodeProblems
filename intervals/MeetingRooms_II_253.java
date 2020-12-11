package intervals;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/** M [marked]
 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 find the minimum number of conference rooms required.

 Example1
 Input: intervals = [(0,30),(5,10),(15,20)]
 Output: 2
 Explanation:
 We need two meeting rooms
 room1: (0,30)
 room2: (5,10),(15,20)

 Example2
 Input: intervals = [(2,7)]
 Output: 1
 Explanation:
 Only need one meeting room
 */
public class MeetingRooms_II_253 {

    public static void main(String[] args) {
        MeetingRooms_II_253 s = new MeetingRooms_II_253();

        System.out.println(s.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}})); //2
        System.out.println(s.minMeetingRooms(new int[][]{{2,7}})); //1
        System.out.println(s.minMeetingRooms(new int[][]{{5,10},{0,30},{21,30},{15,20},{25,35},{40,45}})); //2
    }

    // O(n*log(n)) - time, O(n)- space
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));
        Queue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < intervals.length; i++) {
            if (!heap.isEmpty() && intervals[i][0] >= heap.peek()) {
                heap.poll();
            }
            heap.add(intervals[i][1]);
        }

        return heap.size();
    }
}
