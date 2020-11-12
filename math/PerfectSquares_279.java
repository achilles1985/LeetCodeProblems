package math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**M [MARKED]
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which
 * sum to n.
 *
 * Example 1:
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 *
 *  Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
/*
                12
        1^2/  2^2|  3^2\
     12-1(11) 12-4(8)  12-9(3)
    1/ 4| 9\
   10  8    3
   If we traverse level by level we ends up with zero diff, which is a result
 */

public class PerfectSquares_279 {

    public static void main(String[] args) {
        PerfectSquares_279 s = new PerfectSquares_279();
        System.out.println(s.numSquaresBF(12)); //3
        System.out.println(s.numSquaresBFS(13)); //2
        System.out.println(s.numSquaresBFS(4)); //1
    }

    public int numSquaresBF(int n) {
        if(n < 4) {
            return n;
        }
        int count = n; // as we can form number n with sum of atleqast n 1's.
        for(int i = 1; i*i <= n; i++) {
            count = Math.min(count, numSquaresBF(n - i * i) + 1);
        }
        return count;
    }

    public int numSquaresBFS(int n) {
        if (n <= 0) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(n);
        visited.add(n);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int curAmount = queue.poll();
                if (curAmount == 0) {
                    return level;
                }
                for (int i = 1; i*i <= n; i++) {
                    int nextAmount = curAmount - i*i;
                    if (nextAmount >= 0 && !visited.contains(nextAmount)) {
                        queue.add(nextAmount);
                        visited.add(nextAmount);
                    }
                }
            }
            level++;
        }
        return 1;
    }

    /*
    Let dp[i] is the least number of perfect square to sum up i.
    Base Case
    i = 0; dp[0] = 0
    i = 1; dp[1] = 1
    i = 2; dp[2] = 2
    i = 3; dp[3] = 3

    initilialize dp[i] = Int.MAX_VALUE
    dp[i] = Math.min(dp[i], dp[i - j * j]) for all j where j * j <= i
     */
    public int numSquaresDP(int n) {
        if(n < 4) {
            return n;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; dp[1] = 1; dp[2] = 2; dp[3] = 3;

        for(int i = 4; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }
}
