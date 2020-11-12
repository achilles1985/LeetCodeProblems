package dynamic.medium;

import java.util.HashSet;
import java.util.Set;

/**M
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
 * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length -
 * 1, and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
 *
 * Example 1:
 * Input: [3,6,9,12]
 * Output: 4
 * Explanation:
 * The whole array is an arithmetic sequence with steps of length = 3.
 *
 * Example 2:
 * Input: [9,4,7,2,10]
 * Output: 3
 * Explanation:
 * The longest arithmetic subsequence is [4,7,10].
 *
 * Example 3:
 * Input: [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation:
 * The longest arithmetic subsequence is [20,15,10,5].
 *
 * Note:
 *     2 <= A.length <= 2000
 *     0 <= A[i] <= 10000
 */
/*
Inspired by the LIS problem. At each index i, we look every index j that j < i, if at index j, there is already also sequence with diff A[i] - A[j],
then add 1 to the length of that diff, otherwise, create a new kv pair, with key being diff, value being 2 (length 2)
 */
public class LongestArithmeticSequence_1027 {

    public static void main(String[] args) {
        LongestArithmeticSequence_1027 s = new LongestArithmeticSequence_1027();
        System.out.println(s.longestArithSeqLength2(new int[]{9,4,7,2,10})); //3
        System.out.println(s.longestArithSeqLength2(new int[]{83,20,17,43,52,78,68,45})); //2
        System.out.println(s.longestArithSeqLength2(new int[]{7,6,5,4,3,2,1})); //7
        System.out.println(s.longestArithSeqLength2(new int[]{3,6,9,12})); //4
        System.out.println(s.longestArithSeqLength2(new int[]{20,1,15,3,10,5,8})); //4
    }

    // O(n^2) - time, space
    public int longestArithSeqLengthBF(int[] A) {
        Integer max = 1;
        for(int i = 0;i<A.length;i++) {
            max = Math.max(max, helper(i, A, null));
        }
        return max;
    }

    private int helper(int index, int[] A, Integer diff) {
        if(index >= A.length) return 0;
        Integer max = 0;
        if(diff == null) {
            for(int j = index+1;j<A.length;j++) {
                max = Math.max(max, helper(j, A, A[j]-A[index]));
            }
        } else {
            for(int j = index+1; j<A.length;j++) {
                if(A[j]-A[index] == diff) {
                    max = Math.max(max, helper(j, A, diff));
                }
            }
        }
        return 1+max;
    }

    // https://leetcode.com/problems/longest-arithmetic-sequence/discuss/429042/Java-DP-array-explained
    // O(n^2) - time, space
    public int longestArithSeqLength2(int[] A) {
        int res = 0;
        int[][] dp = new int[A.length][20000];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                //get the difference i and j elements, apply 10,000 shift so we can use array indexes
                int dif = (A[i] - A[j]) + 10000;
                //int dif = Math.abs(A[i] - A[j]);
                dp[i][dif] = (dp[j][dif] == 0 ? 1 : dp[j][dif]) + 1;
                res = Math.max(res, dp[i][dif]);
            }
        }
        return res;
    }
}
