package dynamic;

/**M
 Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

 Example 1:
 Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 Output: True
 Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.

 Note:
 1 <= k <= len(nums) <= 16.
 0 < nums[i] < 10000.
 */
/*
    1. Should it be contiguous partitions?
 */
public class PartitionToKEqualSumSubsets_698 {

    public static void main(String[] args) {
        PartitionToKEqualSumSubsets_698 s = new PartitionToKEqualSumSubsets_698();
        System.out.println(s.canPartitionKSubsets(new int[]{4,3,2,3,5,2,1}, 4)); // true
        System.out.println(s.canPartitionKSubsets(new int[]{1,2,3,4,5,1}, 4)); // false
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length || k == 0) {
            return false;
        }
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
        if (sum%k != 0) {
            return false;
        }
        int bucketSum = sum/k;
        boolean[] used = new boolean[nums.length];
        return canPartitionKSubsets(0, 0, nums, k, bucketSum, used);
    }

    private boolean canPartitionKSubsets(int index, int sum, int[] nums, int k, int targetSum, boolean[] used) {
        if (k == 1) {
            return true;
        }
        if (sum == targetSum) {
            return canPartitionKSubsets(0, 0, nums, k-1, targetSum, used);
        }
        for (int i = index; i < nums.length; i++) {
            if (!used[i] && sum + nums[i] <= targetSum) {
                used[i] = true;
                if (canPartitionKSubsets(i + 1, sum + nums[i], nums, k, targetSum, used)) {
                    return true;
                }
                used[i] = false;
            }
        }
        return false;
    }
}
