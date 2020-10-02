package dynamic.medium;

import utils.SolutionUtils;

/**M
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's
 * in their binary representation and return them as an array.
 *
 * Example 1:
 * Input: 2
 * Output: [0,1,1]
 * Example 2:
 *
 * Input: 5
 * Output: [0,1,1,2,1,2]
 * Follow up:
 *
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)).
 * But can you do it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */
public class CountingBits_338 {

    public static void main(String[] args) {
        CountingBits_338 s = new CountingBits_338();
        SolutionUtils.print(s.countBits(2)); //[0,1,1]
        SolutionUtils.print(s.countBits(5)); //[0,1,1,2,1,2]

        SolutionUtils.print(s.countBitsDynamic(2)); //[0,1,1]
        SolutionUtils.print(s.countBitsDynamic(5)); //[0,1,1,2,1,2]
    }

    // O(n*sizeOf(integer)) - time, O(1) - space
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        for (int i = 0; i <= num; i++) {
            int counter = 0;
            String str = Integer.toBinaryString(i);
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '1') {
                    counter++;
                }
            }
            res[i] = counter;
        }

        return res;
    }

    // O(n) - time, O(1) - space
    // https://leetcode.com/problems/counting-bits/discuss/270693/Intermediate-processsolution-for-the-most-voted-solution-i.e.-no-simplificationtrick-hidden
    public int[] countBitsDynamic(int num) {
        if (num == 0) {
            return new int[] {0};
        }
        if (num == 1) {
            return new int[] {0, 1};
        }
        int[] dp = new int[num+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= num; i++) {
            // odd number
            if ((i&1) == 1) {
                dp[i] = dp[i-1] + 1;
            } else {
                dp[i] = dp[i>>1];
            }
        }

        return dp;
    }
}
