package intervals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**M
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.
 * If there is no common time slot that satisfies the requirements, return an empty array.
 * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
 * It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
 *
 * Example 1:
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * Output: [60,68]
 *
 * Example 2:
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * Output: []
 *
 * Constraints:
 *     1 <= slots1.length, slots2.length <= 10^4
 *     slots1[i].length, slots2[i].length == 2
 *     slots1[i][0] < slots1[i][1]
 *     slots2[i][0] < slots2[i][1]
 *     0 <= slots1[i][j], slots2[i][j] <= 10^9
 *     1 <= duration <= 10^6
 */
/*
    Questions:
    1. Do arrays already sorted?
    Strategy:
    1. Check if there is no overlap, if it is, check it's duration.
 */
public class MeetingScheduler_1229 {

    public static void main(String[] args) {
        MeetingScheduler_1229 s = new MeetingScheduler_1229();
        System.out.println(s.minAvailableDuration(new int[][]{{10,50},{60,120},{140,210}}, new int[][]{{0,15},{60,70}}, 8)); //[60,68]
        System.out.println(s.minAvailableDuration(new int[][]{{10,50},{60,120},{140,210}}, new int[][]{{0,15},{60,70}}, 12)); //[]
    }

    // O((n + m)*log(n+m)) - time, O(1) - space
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        int i = 0, j = 0;
        Arrays.sort(slots1, (e1, e2) -> e1[0] - e2[0]);
        Arrays.sort(slots2, (e1, e2) -> e1[0] - e2[0]);
        while (i < slots1.length && j < slots2.length) {
            // check if there is an overlap
            if (slots2[j][0] > slots1[i][1]) {
                i++;
            } else if (slots1[i][0] > slots2[j][1]) {
                j++;
            } else { // if it is, check it is duration
                int left = Math.max(slots1[i][0], slots2[j][0]);
                int right = Math.min(slots1[i][1], slots2[j][1]);
                if (right - left >= duration) {
                    return Arrays.asList(left, left + duration);
                } else { // if duration is less
                    if (slots1[i][1] < slots2[j][1]) {
                        i++;
                    } else {
                        j++;
                    }
                }
            }
        }
        return Collections.emptyList();
    }
}
