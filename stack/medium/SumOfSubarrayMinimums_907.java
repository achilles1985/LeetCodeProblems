package stack.medium;

import java.util.Stack;

/** M [marked]
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
        System.out.println(s.sumSubarrayMins(new int[]{2,9,7,8,3,4,6,1})); //
        System.out.println(s.sumSubarrayMins(new int[]{3,1,2,4})); // 17
        System.out.println(s.sumSubarrayMinsBF(new int[]{3,1,2,4})); // 17
        System.out.println(s.sumSubarrayMinsDP(new int[]{3,1,2,4})); // 17
        System.out.println(s.sumSubarrayMinsDP2(new int[]{3,1,2,4})); // 17
    }

    // O(n^3) - time, O(1) - space
    public int sumSubarrayMinsBF(int[] arr) {
        int sum = 0;
        int MOD = 1_000_000_007;
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j <= arr.length - i; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = j; k < j + i; k++) {
                    min = Math.min(min, arr[k]);
                }
                sum += min;
                sum %= MOD;
            }
        }
        return sum;
    }

    // O(n^2) - time, space
    public int sumSubarrayMinsDP(int[] arr) {
        int MOD = 1_000_000_007;
        int[][] dp = new int[arr.length][arr.length];
        int sum = 0;
        for (int i = 0 ; i < arr.length; i++) {
            dp[i][i] = arr[i];
            sum += dp[i][i];
            sum %= MOD;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                dp[i][j] = Math.min(dp[i][j-1], arr[j]);
                sum += dp[i][j];
                sum %= MOD;
            }
        }
        return sum;
    }

    // O(n^2) - time, O(n) - space
    public int sumSubarrayMinsDP2(int[] arr) {
        int MOD = 1_000_000_007;
        int[] dp = new int[arr.length];
        int sum = 0;
        for (int i = 0 ; i < arr.length; i++) {
            dp[i] = arr[i];
            sum += dp[i];
            sum %= MOD;
        }
        for (int i = 0; i < arr.length; i++) {
            dp[i] = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                dp[j] = Math.min(dp[j-1], arr[j]);
                sum += dp[j];
                sum %= MOD;
            }
        }
        return sum;
    }

    // Find number of subarrays where A[i] is the smallest element and multiple that number to A[i].
    // https://leetcode.com/problems/sum-of-subarray-minimums/discuss/170750/JavaC%2B%2BPython-Stack-Solution
    // O(n) - time, space
    public int sumSubarrayMins(int[] A) {
        long res = 0;
        int mod = 1000_000_007;
        int n = A.length, left[] = new int[n], right[] = new int[n];
        Stack<int[]> s1 = new Stack<>(), s2 = new Stack<>();
        for (int i = 0; i < n; ++i) {
            int count = 1;
            while (!s1.isEmpty() && s1.peek()[0] > A[i]) {
                count += s1.pop()[1];
            }
            s1.push(new int[] {A[i], count});
            left[i] = count;
        }
        for (int i = n - 1; i >= 0; --i) {
            int count = 1;
            while (!s2.isEmpty() && s2.peek()[0] >= A[i]) {
                count += s2.pop()[1];
            }
            s2.push(new int[] {A[i], count});
            right[i] = count;
        }
        for (int i = 0; i < n; ++i) {
            res = (res + (long) A[i] * left[i] * right[i]) % mod;
        }
        return (int)res;
    }
}
