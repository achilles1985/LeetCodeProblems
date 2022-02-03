package slidingWindow.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/** H
 * Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such
 * that for every two consecutive integers in the subsequence,
 * nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
 * A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array,
 * leaving the remaining elements in their original order.
 *
 * Example 1:
 * Input: nums = [10,2,-10,5,20], k = 2
 * Output: 37
 * Explanation: The subsequence is [10, 2, 5, 20].
 *
 * Example 2:
 * Input: nums = [-1,-2,-3], k = 1
 * Output: -1
 * Explanation: The subsequence must be non-empty, so we choose the largest number.
 *
 * Example 3:
 * Input: nums = [10,-2,-10,-5,20], k = 2
 * Output: 23
 * Explanation: The subsequence is [10, -2, -5, 20].
 *
 * Constraints:
 *     1 <= k <= nums.length <= 105
 *     -104 <= nums[i] <= 104
 */
public class ConstrainedSubsequenceSum_1425 {

    public static void main(String[] args) {
        ConstrainedSubsequenceSum_1425 s = new ConstrainedSubsequenceSum_1425();
        System.out.println(s.constrainedSubsetSumBF(new int[]{10,2,-10,5,20}, 4)); //37
        System.out.println(s.constrainedSubsetSumBF(new int[]{-1,-2,-3}, 1)); //-1

        System.out.println(s.constrainedSubsetSum(new int[]{-1,-2,-3}, 1)); //-1
        System.out.println(s.constrainedSubsetSum(new int[]{10,2,-10,5,20}, 2)); //37
        System.out.println(s.constrainedSubsetSum(new int[]{10,-2,-10,-5,20}, 2)); //23
    }

    // O(n*k) - time, O(n) - space
    public int constrainedSubsetSumBF(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = Math.max(i - k, 0); j < i; j++) { // choose the max element in latest k elements, it's in range [i-k, i-1]
                max = Math.max(max, dp[j]);
            }
            dp[i] = arr[i] + max;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // O(n) - time, space
    public int constrainedSubsetSum(int[] nums, int k) {
        int[] dp = new int[nums.length];
        Deque<Integer> deque = new ArrayDeque<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int max = Math.max(0, deque.isEmpty() ? 0 : dp[deque.peekFirst()]);
            dp[i] = nums[i] + max;
            ans = Math.max(ans, dp[i]);
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) { // If dp[i] >= deque.peekLast() -> Can discard the tail since it's useless
                deque.pollLast();
            }
            deque.addLast(i);
            if (i - deque.peekFirst() + 1 > k) { // remove the last element of range k
                deque.removeFirst();
            }
        }
        return ans;
    }
}

