package math;

import java.util.ArrayList;
import java.util.List;

/** H
 * Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?
 *
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: 5 = 5 = 2 + 3
 *
 * Example 2:
 * Input: 9
 * Output: 3
 * Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
 *
 * Example 3:
 * Input: 15
 * Output: 4
 * Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 *
 * Note: 1 <= N <= 10 ^ 9.
 */
public class ConsecutiveNumbersSum_829 {

    public static void main(String[] args) {
        ConsecutiveNumbersSum_829 s = new ConsecutiveNumbersSum_829();
        System.out.println(s.consecutiveNumbersSum(9)); //2
    }

    // TTL
    // O(N^N) - time, (N^2) - space
    public int consecutiveNumbersSum(int N) {
        return helper(N, 1, 0, new ArrayList<>());
    }

    private int helper(int n, int start, int sum, List<Integer> list) {
        if (sum == n) {
            return 1;
        }
        int count = 0;
        for (int i = start; i <= n; i++) {
            if (sum + i > n)  {
                continue;
            }
            if (!list.isEmpty() && list.get(list.size()-1) + 1 != i) {
                continue;
            }
            list.add(i);
            count += helper(n, i+1, sum + i, list);
            list.remove(list.size()-1);
        }
        return count;
    }
}
