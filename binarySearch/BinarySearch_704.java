package binarySearch;

import utils.SolutionUtils;

/** E
 * Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to search target in nums.
 If target exists, then return its index, otherwise return -1.

 Example 1:
 Input: nums = [-1,0,3,5,9,12], target = 9
 Output: 4
 Explanation: 9 exists in nums and its index is 4

 Example 2:
 Input: nums = [-1,0,3,5,9,12], target = 2
 Output: -1
 Explanation: 2 does not exist in nums so return -1

 Note:
 You may assume that all elements in nums are unique.
 n will be in the range [1, 10000].
 The value of each element in nums will be in the range [-9999, 9999].
 */
public class BinarySearch_704 {

    public static void main(String[] args) {
        BinarySearch_704 s = new BinarySearch_704();
/*        System.out.println(s.search(new int[] {-1,0,3,5,9,12}, -2)); // 4
        System.out.println(s.search(new int[] {-1,0,3,5,9,12}, 2)); // -1
        System.out.println(s.search(new int[] {5}, 5)); // 0
        System.out.println(s.search(new int[] {2,5}, 5)); // 1*/

/*        System.out.println(s.leftMostIndex(new int[]{2,2}, 3)); //-1
        System.out.println(s.leftMostIndex(new int[]{1,2,3,3,3,4,5,6}, 3)); // 2
        System.out.println(s.leftMostIndex(new int[]{1,2,4,4,4,4,5,6}, 3)); //-1
        System.out.println(s.leftMostIndex(new int[]{3,3,3,3}, 3)); //0
        System.out.println(s.leftMostIndex(new int[]{1,2,3,3,3,3}, 3)); //2
        System.out.println(s.leftMostIndex(new int[]{3,3,3,3,4,5,6,7}, 3)); //0
        System.out.println(s.leftMostIndex(new int[]{3}, 3)); //0*/

        System.out.println(s.leftMostIndex(new int[]{1,2,3,4,5}, 3)); //2
        System.out.println(s.leftMostIndex(new int[]{2,2}, 3)); //-1
        System.out.println(s.rightMostNumber(new int[]{1,2,3,3,3,4,5,6}, 3)); // 4
        System.out.println(s.rightMostNumber(new int[]{1,2,4,4,4,4,5,6}, 3)); //-1
        System.out.println(s.rightMostNumber(new int[]{3,3,3,3}, 3)); //3
        System.out.println(s.rightMostNumber(new int[]{1,2,3,3,3,3}, 3)); //5
        System.out.println(s.rightMostNumber(new int[]{3,3,3,3,4,5,6,7}, 3)); //3
        System.out.println(s.rightMostNumber(new int[]{3}, 3)); //0

/*        SolutionUtils.print(s.leftRightRange(new int[]{3,3}, 4)); // [-1,-1]
        SolutionUtils.print(s.leftRightRange(new int[]{3}, 4)); // [-1,-1]
        SolutionUtils.print(s.leftRightRange(new int[]{1,2,3,3,3,4,5,6}, 3)); // [2,4]
        SolutionUtils.print(s.leftRightRange(new int[]{1,2,4,4,4,4,5,6}, 3)); // [-1,-1]
        SolutionUtils.print(s.leftRightRange(new int[]{3,3,3,3}, 3)); // [0,3]
        SolutionUtils.print(s.leftRightRange(new int[]{1,2,3,3,3,3}, 3)); // [2,5]
        SolutionUtils.print(s.leftRightRange(new int[]{3,3,3,3,4,5,6,7}, 3)); // [0,3]
        SolutionUtils.print(s.leftRightRange(new int[]{3}, 3)); // [0,0]*/
    }

    // Template 1
    // O(log(n)) - time, O(1) - space
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length-1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    public int leftMostIndex2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = -1;
        int right = nums.length;
        while (right - left > 1) {
            int mid = left + (right - left)/2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[right] != target) {
            return -1;
        }
        return right;
    }

    public int leftMostIndex(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if (nums[mid] >= target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        if (lo < nums.length && nums[lo] == target) {
            return lo;
        }
        return -1;
    }

    public int rightMostNumber(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (right < nums.length && nums[right] == target) {
            return right;
        }
        return -1;
    }

    public int[] leftRightRange(int[] nums, int target) {
        if (nums.length == 1) {
            if (nums[0] == target) {
                return new int[]{0,0};
            }
            return new int[]{-1,-1};
        }
        int leftMost = -1;
        int rightMost = -1;

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (left < nums.length && nums[left] == target) {
            leftMost = left;
        }

        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (right < nums.length && nums[right] == target) {
            rightMost = right;
        }
        return new int[] {leftMost, rightMost};
    }

}
