package binarySearch.medium;

import java.util.Arrays;
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
        SolutionUtils.print(s.searchRange3(new int[]{5,7,7,8,8,10}, 8)); //[3,4]
        SolutionUtils.print(s.searchRange3(new int[]{1,2,3,5,5,6,7}, 5)); //[3,4]
        SolutionUtils.print(s.searchRange3(new int[]{2,2}, 2)); // [0,1]
        SolutionUtils.print(s.searchRange3(new int[]{1}, 1)); // [0,0]
        SolutionUtils.print(s.searchRange3(new int[]{5,7,7,8,8,10}, 8)); //[3,4]
        SolutionUtils.print(s.searchRange3(new int[]{5,7,7,8,8,10}, 6)); // [-1,-1]
        SolutionUtils.print(s.searchRange3(new int[]{8,8,8,8,8,8}, 8)); // [0,5]
        SolutionUtils.print(s.searchRange3(new int[]{8,8,8,8,8,8}, 9)); // [-1,-1]
        SolutionUtils.print(s.searchRange3(new int[]{8,8,8,8,8,8}, 5)); // [-1,-1]

        SolutionUtils.print(s.searchRange(new int[]{1,2,3,5,5,6,7}, 5)); //[3,4]
        SolutionUtils.print(s.searchRange2(new int[]{5,7,7,8,8,10}, 8)); //[3,4]

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
        int[] range = new int[]{-1,-1};
        int idx = Arrays.binarySearch(nums, target); // make sure target exists
        if (idx < 0) {
            return range;
        }
        int left = 0;
        int right = idx;
        while (left < right) { // if target is not within the range, we can get Lo=0 or Hi=array.length-1
            int mid = left + (right - left)/2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        range[0] = right;
        left = idx;
        right = nums.length-1;
        while (left < right) {
            int mid = left + (right - left)/2 + 1; // +1 so that l = mid; doesn't result in an infinite loop when l + 1 == r.
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        range[1] = left;

        return range;
    }

    // O(log(n)) - time, O(1) - space
    public int[] searchRange3(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1,-1};
        }

        int[] range = new int[]{-1,-1};
        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid = left + (right-left)/2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        if (nums[left] == target) {
            range[0] = left;
        }
        left = 0;
        right = nums.length-1;
        while (left < right) {
            int mid = left + (right-left)/2 + 1; // +1 so that l = mid; doesn't result in an infinite loop when l + 1 == r.
            if (nums[mid] == target) {
                left = mid;
            } else if (nums[mid] > target) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        if (nums[right] == target) {
            range[1] = right;
        }

        return range;
    }

    public int[] searchRange2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int[] range = new int[] {-1, -1};
        int left = 0, right = nums.length - 1;
        // find left bound index
        while (left <= right) { // if target is not within the range, we can get Lo=-1 or Hi=array.length
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
