package binarySearch;

import java.util.Arrays;

/** H
 You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 What is the maximum number of envelopes can you Russian doll? (put one inside other)
 Note:
 Rotation is not allowed.

 Example:
 Input: [[5,4],[6,4],[6,7],[2,3]]
 Output: 3
 Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class RussianDollEnvelopes_354 {

    public static void main(String[] args) {
        RussianDollEnvelopes_354 s = new RussianDollEnvelopes_354();
        System.out.println(s.maxEnvelopesBS(new int[][]{{5,4},{6,4},{6,7},{2,3}})); //3
    }

    // O(n^2) - time, O(n) - space
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (e1,e2) -> e1[0] != e2[1] ? e1[0]-e2[0] : e1[1]-e2[1]); //3
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
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return b[1] - a[1];
            }
        });

        // Longest Increasing Subsequence Algorithm
        int[] sortedArray = new int[envelopes.length];
        int size = 0;
        for (int[] envelope : envelopes) {
            int start = 0;
            int end = size; // 1 element past end of our sortedArray
            while (start != end) {
                int mid = (start + end) / 2;
                if (sortedArray[mid] < envelope[1]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            sortedArray[start] = envelope[1];
            if (start == size) {
                size++;
            }
        }
        return size;
    }
}
