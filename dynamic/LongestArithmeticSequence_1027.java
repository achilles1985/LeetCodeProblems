package dynamic;

import java.util.HashMap;

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
        System.out.println(s.longestArithSeqLength(new int[]{7,6,5,4,3,2,1})); //7
        System.out.println(s.longestArithSeqLength(new int[]{9,4,7,2,10})); //3
        System.out.println(s.longestArithSeqLength(new int[]{3,6,9,12})); //4
        System.out.println(s.longestArithSeqLength(new int[]{20,1,15,3,10,5,8})); //4
    }

    // O(n^2) - time, space
    public int longestArithSeqLength(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        // create an array of map, each array[i] key is the diff, value is the seqence length of this diff up to i
        HashMap<Integer, Integer>[] dp = new HashMap[A.length];
        dp[0] = new HashMap();
        int res = 1;
        for (int i = 1; i < A.length; i++) {
            dp[i] = new HashMap();
            for (int j = 0; j < i; j++) {
                int diff = A[i] - A[j];
                int prev = dp[j].getOrDefault(diff, 0) + 1;
                int cur = Math.max(dp[i].getOrDefault(diff, 0), prev);
                dp[i].put(diff, cur);
                res = Math.max(res, cur);
            }
        }
        return res + 1;
    }
}
