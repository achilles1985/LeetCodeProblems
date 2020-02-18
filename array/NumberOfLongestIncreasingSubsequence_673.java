package array;

import java.util.Arrays;

/**
 * M
 * Given an unsorted array of integers, find the number of longest increasing subsequence.
 * <p>
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 * <p>
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is
 * 1, so output 5.
 * <p>
 * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 */
//??? to be understood
public class NumberOfLongestIncreasingSubsequence_673 {

    public static void main(String[] args) {
        NumberOfLongestIncreasingSubsequence_673 s = new NumberOfLongestIncreasingSubsequence_673();
        System.out.println(s.findNumberOfLIS(new int[]{1, 3, 5, 4, 7})); //2
        System.out.println(s.findNumberOfLIS(new int[]{2, 2, 2, 2, 2})); //5
    }

    // O(n^2) - time, O(n) - space
    public int findNumberOfLIS(int[] nums) {
        int N = nums.length;
        if (N <= 1) {
            return N;
        }
        int[] lengths = new int[N]; //lengths[i] = length of longest ending in nums[i]
        int[] counts = new int[N]; //count[i] = number of longest ending in nums[i]
        Arrays.fill(counts, 1);

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < i; ++j)
                if (nums[i] > nums[j]) {
                    if (lengths[j] >= lengths[i]) {
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    } else if (lengths[j] + 1 == lengths[i]) {
                        counts[i] += counts[j];
                    }
                }
        }

        int longest = 0;
        for (int length : lengths) {
            longest = Math.max(longest, length);
        }
        int ans = 0;
        for (int i = 0; i < N; ++i) {
            if (lengths[i] == longest) {
                ans += counts[i];
            }
        }
        return ans;
    }
}
