package array.FindDuplicateNumber_287;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * Example 1:
 * Input: [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: [3,1,3,4,2]
 * Output: 3
 * Note:
 *
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class Solution {

    // O(n) - time, O(1) - space
    public int findDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length-1;
        while (l <= r) {
            int m = (l+r)/2;
            if (nums[m] == nums[l] || nums[m] == nums[r]) {
                return nums[m];
            }
            l++;
        }

        return -1;
    }

    // https://leetcode.com/problems/find-the-duplicate-number/solution/ (Cycle detection)
    public int findDuplicate2(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        return slow;
    }
}
