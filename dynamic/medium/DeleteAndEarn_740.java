package dynamic.medium;

/** M
 * You are given an integer array nums. You want to maximize the number of points you get by performing
 * the following operation any number of times:
 Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1
 and every element equal to nums[i] + 1.
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 *
 * Example 1:
 * Input: nums = [3,4,2]
 * Output: 6
 * Explanation: You can perform the following operations:
 * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
 * - Delete 2 to earn 2 points. nums = [].
 * You earn a total of 6 points.
 *
 * Example 2:
 * Input: nums = [2,2,3,3,3,4]
 * Output: 9
 * Explanation: You can perform the following operations:
 * - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
 * - Delete a 3 again to earn 3 points. nums = [3].
 * - Delete a 3 once more to earn 3 points. nums = [].
 * You earn a total of 9 points.
 *
 * Constraints:
 *     1 <= nums.length <= 2 * 104
 *     1 <= nums[i] <= 104
 */
public class DeleteAndEarn_740 {

    public static void main(String[] args) {
        DeleteAndEarn_740 s = new DeleteAndEarn_740();
        System.out.println(s.deleteAndEarn(new int[]{3,4,2})); //6
        System.out.println(s.deleteAndEarn(new int[]{2,2,3,3,3,4})); //9
    }

    //O(n) - time, O(n) - space
    public int deleteAndEarnMemoization(int[] nums) {
        int[] count = new int[10000 + 1];
        int maxNum = 0;
        for (int num: nums) {
            count[num]++;
            maxNum = Math.max(maxNum, num);
        }

        return deleteAndEarn(maxNum, count);
    }

    //O(n) - time, O(n) - space
    public int deleteAndEarn(int[] nums) {
        int[] buckets = new int[10001];
        for (int num : nums) {
            buckets[num] += num;
        }
        int[] dp = new int[10001];
        dp[0] = buckets[0];
        dp[1] = buckets[1];
        for (int i = 2; i < buckets.length; i++) {
            dp[i] = Math.max(buckets[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[10000];
    }

    //O(n) - time, O(1) - space
    public int deleteAndEarn2(int[] nums) {
        final int[] values = new int[10001];
        for (int num : nums) {
            values[num] += num;
        }
        int take = 0, skip = 0;
        for (final int value : values) {
            final int temp = Math.max(skip + value, take);
            skip = take;
            take = temp;
        }
        return take;
    }

    private int deleteAndEarn(int num, int[] count) {
        if (num <= 0) {
            return 0;
        }
        return Math.max(deleteAndEarn(num - 2, count) + (count[num] * num),
                deleteAndEarn(num - 1, count));
    }
}
