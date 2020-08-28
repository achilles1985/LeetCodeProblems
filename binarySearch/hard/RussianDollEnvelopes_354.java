package binarySearch.hard;

import java.util.Arrays;

/** H [TODO]
 You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 What is the maximum number of envelopes can you Russian doll? (put one inside other)
 Note:
 Rotation is not allowed.

 Example:
 Input: [[5,4],[6,4],[6,7],[2,3]]
 Output: 3
 Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
/*
    We cannot traverse through an sorted array to count next greater envelope. It's LIS problem.
 */
public class RussianDollEnvelopes_354 {

    public static void main(String[] args) {
        RussianDollEnvelopes_354 s = new RussianDollEnvelopes_354();
        System.out.println(s.maxEnvelopesBS(new int[][]{{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{7,380}})); //5
        System.out.println(s.maxEnvelopesBS(new int[][]{{5,4},{6,4},{6,7},{2,3}})); //3
        System.out.println(s.maxEnvelopesBS(new int[][]{{5,4},{6,4},{6,7},{2,3},{6,8},{6,9},{7,5},{7,8}})); //4
    }

    // O(n^2) - time, O(n) - space
    public int maxEnvelopesDP(int[][] envelopes) {
        Arrays.sort(envelopes, (e1,e2) -> e1[0] != e2[1] ? e1[0]-e2[0] : e1[1]-e2[1]);
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp,1);
        int res = 1;
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < envelopes.length; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    // O(n*log(n)) - time, O(n) - space
    // https://leetcode.com/problems/russian-doll-envelopes/discuss/348428/O(n-log-n)-Java-Solution-%2B-Example
    public int maxEnvelopesBS(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length != 2) {
            return 0;
        }
        // a[0], b[0], ... - ascending order; a[1], b[1] - descending order
        Arrays.sort(envelopes, (a,b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        // Longest Increasing Subsequence Algorithm (LIS)
        int[] dp = new int[envelopes.length];
        int size = 0;
        for (int[] envelope : envelopes) {
            int start = 0;
            int end = size; // 1 element past end of our dp
            while (start < end) {
                int mid = (start + end) / 2;
                if (dp[mid] < envelope[1]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            dp[start] = envelope[1];
            if (start == size) {
                size++;
            }
        }
        return size;
    }
}
