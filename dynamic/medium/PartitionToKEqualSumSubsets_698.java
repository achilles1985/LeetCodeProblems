package dynamic.medium;

import java.util.Arrays;

/**M [marked]
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
    2. max number, array size, k always < nums.length?
 */
public class PartitionToKEqualSumSubsets_698 {

    public static void main(String[] args) {
        PartitionToKEqualSumSubsets_698 s = new PartitionToKEqualSumSubsets_698();
        System.out.println(s.canPartitionKSubsets(new int[]{4,3,2,3,5,2,1}, 4)); // true
        System.out.println(s.canPartitionKSubsets(new int[]{1,2,3,4,5,1}, 4)); // false
    }

    // O(n!)?? - time, O(n) - space??
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int totalSum = Arrays.stream(nums).sum();
        if (totalSum%k != 0) {
            return false;
        }
        int bucketSum = totalSum/k;
        return dfs(0, 0, bucketSum, new boolean[nums.length], nums, k);
    }

    private boolean dfs(int start, int sum, int bucketSum, boolean[] used, int[] nums, int k) {
        if (k == 1) {
            return true;
        }
        if (sum == bucketSum) {
            return dfs(0, 0, bucketSum, used, nums, k - 1);
        }
        for (int i = start; i < nums.length; i++) {
            if (sum + nums[i] > bucketSum || used[i]) {
                continue;
            }
            used[i] = true;
            if (dfs(i+1, sum + nums[i], bucketSum, used, nums, k)) {
                return true;
            }
            used[i] = false;
        }
        return false;
    }

}
