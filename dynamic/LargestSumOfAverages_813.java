package dynamic;

/**M
 * We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the
 * average of each group. What is the largest score we can achieve?
 * Note that our partition must use every number in A, and that scores are not necessarily integers.
 *
 * Example:
 * Input:
 * A = [9,1,2,3,9]
 * K = 3
 * Output: 20
 * Explanation:
 * The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * We could have also partitioned A into [9, 1], [2], [3, 9], for example.
 * That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 *
 * Note:
 *     1 <= A.length <= 100.
 *     1 <= A[i] <= 10000.
 *     1 <= K <= A.length.
 *     Answers within 10^-6 of the correct answer will be accepted as correct.
 */
public class LargestSumOfAverages_813 {

    public static void main(String[] args) {
        LargestSumOfAverages_813 s = new LargestSumOfAverages_813();
        System.out.println(s.largestSumOfAverages(new int[]{9,1,2,3,9}, 3)); //20.0
    }

    public double largestSumOfAverages(int[] A, int K) {
        int[] sum = new int[A.length];
        sum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            sum[i] = A[i] + sum[i-1];
        }
        return dfs(A, K, sum, A.length, 0);
    }

    public double dfs(int[] A, int k, int[] sum, int len, int start) {
        if (k == 1) {
            return ((double)(sum[len-1]-sum[start]+A[start])/(len-start));
        }
        double num = 0;
        for (int i = start; i + k <= len ; i++) {
            double avarage = (double) (sum[i] - sum[start] + A[start]) / (i - start + 1);
            double dfs = dfs(A, k - 1, sum, len, i + 1);
            num = Math.max(num, avarage + dfs);
        }
        return num;
    }
}
