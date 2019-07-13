package array.FindMinimumInRotatedSortedArray_153;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 Find the minimum element.
 You may assume no duplicate exists in the array.

 Example 1:
 Input: [3,4,5,1,2]
 Output: 1

 Example 2:
 Input: [4,5,6,7,0,1,2]
 Output: 0
 */
public class Solution {

    // O(log(n)) - time, O(1) - space
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int mid;
        while (l < r) {
            mid = (l+r)/2;
            if (nums[mid] < nums[l] && nums[mid] < nums[r]) {
                return nums[mid];
            }
            if (nums[mid] > nums[r]) {
                l = mid;
            } else {
                r = mid;
            }
        }

        return -1;
    }
}
