package array.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** M
 * A cinema has n rows of seats, numbered from 1 to n and there are ten seats in each row, labelled from 1 to 10 as shown in the figure above.
 * Given the array reservedSeats containing the numbers of seats already reserved, for example,
 * reservedSeats[i] = [3,8] means the seat located in row 3 and labelled with 8 is already reserved.
 * Return the maximum number of four-person groups you can assign on the cinema seats.
 * A four-person group occupies four adjacent seats in one single row. Seats across an aisle (such as [3,3] and [3,4]) are not considered to be adjacent, but there is an exceptional case on which an aisle split a four-person group, in that case, the aisle split a four-person group in the middle, which means to have two people on each side.

 *
 * Example 1:
 * Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
 * Output: 4
 * Explanation: The figure above shows the optimal allocation for four groups, where seats mark with blue are already reserved and contiguous seats mark with orange are for one group.
 *
 * Example 2:
 * Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
 * Output: 2
 *
 * Example 3:
 * Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
 * Output: 4

 * Constraints:
 *     1 <= n <= 10^9
 *     1 <= reservedSeats.length <= min(10*n, 10^4)
 *     reservedSeats[i].length == 2
 *     1 <= reservedSeats[i][0] <= n
 *     1 <= reservedSeats[i][1] <= 10
 *     All reservedSeats[i] are distinct.
 */
public class CinemaSeatAllocation_1386 {

    public static void main(String[] args) {
        CinemaSeatAllocation_1386 s = new CinemaSeatAllocation_1386();
        System.out.println(s.maxNumberOfFamilies(3, new int[][]{{1,2},{1,3},{1,8},{2,6},{3,1},{3,10}})); //4
    }

    // O(reserved) - time, O(reserved) - space
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] seat: reservedSeats) {
            int row = seat[0], col = seat[1];
            map.computeIfAbsent(row, k -> new HashSet<>()).add(col);
        }
        int res = 2*(n - map.size());
        for (Set<Integer> seats: map.values()) {
            int count = 0, mid = 0;
            if (!seats.contains(2) && !seats.contains(3) && !seats.contains(4) && !seats.contains(5)) {
                count++;
            }
            if (!seats.contains(6) && !seats.contains(7) && !seats.contains(8) && !seats.contains(9)) {
                count++;
            }
            if (!seats.contains(4) && !seats.contains(5) && !seats.contains(6) && !seats.contains(7)) {
                mid++;
            }
            res += Math.max(mid, count);
        }

        return res;
    }

    // https://leetcode.com/problems/cinema-seat-allocation/discuss/1312693/Java-version
    public int maxNumberOfFamilies2(int n, int[][] reservedSeats) {
        Map<Integer, Integer> graph = new HashMap<>();
        for (int i = 0; i < reservedSeats.length; i++) {
            int row = reservedSeats[i][0];
            int col = reservedSeats[i][1];
            graph.put(row,graph.getOrDefault(row,0) | (1 << (col-1)));
        }
        int max = 0;
        for (int row : graph.keySet()) {
            int reserved = graph.get(row);
            int cnt = 0;
            if ((reserved &  30) == 0) cnt += 1; // check if seats 2,3,4,5 are available
            if ((reserved & 480) == 0) cnt += 1; // check if seats 6,7,8,9 are available
            if ((reserved & 120) == 0 && cnt == 0) cnt = 1; // check if seats 4,5,6,7 are available
            max += cnt;
        }
        return max + 2 * (n - graph.size());
    }
}
