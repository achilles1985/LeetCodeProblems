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
public class PartitionToKEqualSumSubsets_698 {

    public static void main(String[] args) {
        PartitionToKEqualSumSubsets_698 s = new PartitionToKEqualSumSubsets_698();
        System.out.println(s.canPartitionKSubsets(new int[]{4,3,2,3,5,2,1}, 4)); // true
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length || k == 0) {
            return false;
        }
        return canPartitionKSubsets(0, 0, nums, k);
    }

    private boolean canPartitionKSubsets(int index, int sum, int[] nums, int k) {

        return false;
    }
}
