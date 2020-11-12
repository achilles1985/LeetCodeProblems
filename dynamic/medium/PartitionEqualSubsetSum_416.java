package dynamic.medium;

import java.util.HashMap;

/**M [MARKED]
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets
 * such that the sum of elements in both subsets is equal.
 *
 * Note:
 *     Each of the array element will not exceed 100.
 *     The array size will not exceed 200.
 *
 * Example 1:
 * Input: [1, 5, 11, 5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 * Input: [1, 2, 3, 5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionEqualSubsetSum_416 {

    public static void main(String[] args) {
        PartitionEqualSubsetSum_416 s = new PartitionEqualSubsetSum_416();
        System.out.println(s.canPartition(new int[]{1,5,11,5})); //true
        System.out.println(s.canPartitionTopDown(new int[]{1,5,11,5})); //true

        System.out.println(s.canPartition(new int[]{1,2,3,5})); //false
        System.out.println(s.canPartitionTopDown(new int[]{1,2,3,5})); //false
    }

    // O(2^nums) - time, O(n) - space
    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int item: nums) {
            total += item;
        }
        if (total%2 != 0) {
            return false;
        }
        return canPartition(0, 0, nums, total);
    }

    private boolean canPartition(int index, int sum, int[] nums, int total) {
        if (sum*2 == total) {
            return true;
        }
        if (index >= nums.length || 2*sum > total) {
            return false;
        }
        return canPartition(index + 1, sum + nums[index], nums, total) || canPartition(index + 1, sum, nums, total);
    }

    // O(nums*total) - time
    public boolean canPartitionTopDown(int[] nums) {
        int total = 0;
        for (int item: nums) {
            total += item;
        }
        if (total%2 != 0) {
            return false;
        }
        return canPartitionTopDown(0, 0, nums, total, new HashMap<String, Boolean>());
    }

    private boolean canPartitionTopDown(int index, int sum, int[] nums, int total, HashMap<String, Boolean> cache) {
        if (sum*2 == total) {
            return true;
        }
        String key = index + ":" + sum;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (index >= nums.length || 2*sum > total) {
            return false;
        }
        boolean result = canPartitionTopDown(index + 1, sum + nums[index], nums, total, cache) || canPartitionTopDown(index + 1, sum, nums, total, cache);
        cache.put(key, result);

        return cache.get(key);
    }
}
