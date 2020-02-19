package binarySearch;

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
        SolutionUtils.print(s.searchRange2(new int[]{2,2}, 2)); // [0,1]
        SolutionUtils.print(s.searchRange2(new int[]{1}, 1)); // [0,0]
        SolutionUtils.print(s.searchRange2(new int[]{5,7,7,8,8,10}, 8)); //[3,4]
        SolutionUtils.print(s.searchRange2(new int[]{5,7,7,8,8,10}, 6)); // [-1,-1]
        SolutionUtils.print(s.searchRange2(new int[]{8,8,8,8,8,8}, 8)); // [0,5]
        SolutionUtils.print(s.searchRange2(new int[]{8,8,8,8,8,8}, 9)); // [-1,-1]
    }

    // O(n) - time - brute force when we iterate via an array and find min and max index

    public int[] searchRange2(int[] nums, int target) {
        int idx = Arrays.binarySearch(nums, target);
        if (idx < 0) {
            return new int[]{-1,-1};
        }
        if (nums.length == 1) {
            return new int[]{0,0};
        }
        int leftIndex = idx;
        int loLeft = 0;
        int hiLeft = idx;
        while (loLeft < hiLeft) {
            int mid = loLeft + (hiLeft - loLeft)/2;
            if (nums[mid] == target) {
                leftIndex = mid;
                hiLeft--;
            } else {
                loLeft++;
            }
        }
        int rightIndex = idx;
        int loRight = idx;
        int hiRight = nums.length;
        while (loRight < hiRight) {
            int mid = loRight + (hiRight-loRight)/2;
            if (nums[mid] == target) {
                rightIndex = mid;
                loRight++;
            } else {
                hiRight--;
            }
        }
        return new int[]{leftIndex, rightIndex};
    }

    // O(log(n)) - time, O(1) - space
    public int[] searchRange(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        if (nums == null || nums.length == 0 || index < 0) {
            return new int[]{-1,-1};
        }

        int minLow = 0;
        int minHigh = index;
        int min = -1;
        while (minLow <= minHigh) {
            int mid = minLow + (minHigh-minLow)/2;
            if (nums[mid] == target) {
                min = mid;
                minHigh = mid-1;
            } else {
                minLow = mid+1;
            }
        }
        int maxLow = index;
        int maxHigh = nums.length-1;
        int max = -1;
        while (maxLow <= maxHigh) {
            int mid = maxLow + (maxHigh-maxLow)/2;
            if (nums[mid] == target) {
                max = mid;
                maxLow = mid+1;
            } else {
                maxHigh = mid-1;
            }
        }

        return new int[]{min, max};
    }
}
