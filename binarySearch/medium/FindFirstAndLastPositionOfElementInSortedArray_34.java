package binarySearch.medium;

import utils.SolutionUtils;

/** M
 Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 Your algorithm's runtime complexity must be in the order of O(log n).
 If the target is not found in the array, return [-1, -1].

 Example 1:
 Input: nums = [5,7,7,8,8,10], target = 8
 Output: [3,4]

 Example 2:
 Input: nums = [5,7,7,8,8,10], target = 6
 Output: [-1,-1]
 */
public class FindFirstAndLastPositionOfElementInSortedArray_34 {

    public static void main(String[] args) {
        FindFirstAndLastPositionOfElementInSortedArray_34 s = new FindFirstAndLastPositionOfElementInSortedArray_34();
        SolutionUtils.print(s.searchRange(new int[]{7,7,7,8,8,10}, 7)); //[0,2]
        SolutionUtils.print(s.searchRange(new int[]{1,2,3,7,7,7}, 7)); //[3,5]
        SolutionUtils.print(s.searchRange(new int[]{7,7,7,7,7,7}, 7)); //[0,5]
        SolutionUtils.print(s.searchRange(new int[]{1,2,7,8,9,10}, 7)); //[2,2]
        SolutionUtils.print(s.searchRange(new int[]{1,2,7,7,9,10}, 7)); //[2,3]
        SolutionUtils.print(s.searchRange(new int[]{7}, 7)); //[0,0]

        SolutionUtils.print(s.searchRange(new int[]{2,2}, 2)); // [0,1]
        SolutionUtils.print(s.searchRange(new int[]{1}, 1)); // [0,0]
        SolutionUtils.print(s.searchRange(new int[]{5,7,7,8,8,10}, 8)); //[3,4]
        SolutionUtils.print(s.searchRange(new int[]{5,7,7,8,8,10}, 6)); // [-1,-1]
        SolutionUtils.print(s.searchRange(new int[]{8,8,8,8,8,8}, 8)); // [0,5]
        SolutionUtils.print(s.searchRange(new int[]{8,8,8,8,8,8}, 9)); // [-1,-1]
        SolutionUtils.print(s.searchRange(new int[]{8,8,8,8,8,8}, 5)); // [-1,-1]
    }

    // O(n) - time, O(1) - space
    public int[] searchRangeBF(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1,-1};
        }
        int left = -1;
        int right = -1;
        for (int i = 0; i < nums.length; i++) {
            if (left == -1 && nums[i] == target) {
                left = i;
            }
            if (nums[i] == target) {
                right = i;
            }
        }

        return new int[]{left, right};
    }

    // O(log(n)) - time, O(1) - space
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int[] range = new int[] {-1, -1};
        int left = 0, right = nums.length - 1;
        // find left bound index
        while (left <= right) {
            int mid = (left + right)/2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (left < nums.length && nums[left] == target) { // for [2,2,2,2,2], 3 - left can go beyond arr.length
          range[0] = left;
        }

        // find right bound index
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right)/2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (right >= 0 && nums[right] == target) { // for [2,2,2,2,2], 1 - right can go beyond 0
            range[1] = right;
        }

        return range;
    }

}
