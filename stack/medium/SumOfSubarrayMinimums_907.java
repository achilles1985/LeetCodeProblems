package stack.medium;

/** M
 Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
 Since the answer may be large, return the answer modulo 10^9 + 7.

 Example 1:
 Input: [3,1,2,4]
 Output: 17
 Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.

 Note:
 1 <= A.length <= 30000
 1 <= A[i] <= 30000
 */
public class SumOfSubarrayMinimums_907 {

    public static void main(String[] args) {
        SumOfSubarrayMinimums_907 s = new SumOfSubarrayMinimums_907();
        System.out.println(s.sumSubarrayMinsBF(new int[]{3,1,2,4})); // 17
    }

    // O(n^3) - time, O(1) - space
    public int sumSubarrayMinsBF(int[] A) {
        int MOD = 1_000_000_007;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                int localMin = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    localMin = Math.min(localMin, A[k]);
                }
                if (localMin != Integer.MAX_VALUE) {
                    sum += localMin;
                    sum %= MOD;
                }
            }
        }

        return sum;
    }
}
