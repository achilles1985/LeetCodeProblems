package dynamic;

import utils.SolutionUtils;

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
