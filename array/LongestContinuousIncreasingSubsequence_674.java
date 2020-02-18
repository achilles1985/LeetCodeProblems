package array;

/**E
 *  Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
 *
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
 * Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
 *
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2], its length is 1.
 *
 * Note: Length of the array will not exceed 10,000.
 */
public class LongestContinuousIncreasingSubsequence_674 {

    public static void main(String[] args) {
        LongestContinuousIncreasingSubsequence_674 s = new LongestContinuousIncreasingSubsequence_674();
        System.out.println(s.findLengthOfLCIS(new int[]{1,3,5,4,7})); // 3
        System.out.println(s.findLengthOfLCIS(new int[]{2,2,2,2,2})); // 1
        System.out.println(s.findLengthOfLCIS(new int[]{2,2,2,2,2,1,2,3,1,7})); // 3
        System.out.println(s.findLengthOfLCIS(new int[]{2})); // 1
    }

    // O(n) - time, O(1) - space
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int max = 0, anchor = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i-1] >= nums[i]) {
                anchor = i;
            }
            max = Math.max(max, i - anchor + 1);
        }
        return max;
    }
}
