package dynamic;

/**
 * M
 * Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After
 * partitioning, each subarray has their values changed to become the maximum value of that subarray.
 * Return the largest sum of the given array after partitioning.
 * <p>
 * Example 1:
 * Input: A = [1,15,7,9,2,5,10], K = 3
 * Output: 84
 * Explanation: A becomes [15,15,15,9,10,10,10]
 * <p>
 * Note:
 * 1 <= K <= A.length <= 500
 * 0 <= A[i] <= 10^6
 */
public class PartitionArrayForMaximumSum_1043 {

    public static void main(String[] args) {
        PartitionArrayForMaximumSum_1043 s = new PartitionArrayForMaximumSum_1043();
        System.out.println(s.maxSumAfterPartitioning(new int[]{1,15,7,9,2,5,10}, 3)); // 84
        System.out.println(s.maxSumAfterPartitioning(new int[]{1,4,1,5,7,3,6,1,9,9,3}, 4)); // 83
    }

    // https://leetcode.com/problems/partition-array-for-maximum-sum/discuss/291057/Java-visualize-the-pattern
    public int maxSumAfterPartitioning(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }
        // dp[i] represents the maximum sum for array 0...i-1
        int[] dp = new int[A.length + 1];
        dp[0] = 0;
        for (int i = 1; i <= A.length; i++) {
            // put i into a separate group
            dp[i] = dp[i - 1] + A[i - 1];
            int maxNum = A[i - 1];
            // combine i into a group with at most K numbers
            for (int j = i - 1; j >= i - K + 1; j--) {
                if (j < 1) {
                    break;
                }
                // get the maximum value of the group
                maxNum = Math.max(maxNum, A[j - 1]);
                dp[i] = Math.max(dp[j - 1] + maxNum * (i - j + 1), dp[i]);
            }
        }
        return dp[A.length];
    }
}
