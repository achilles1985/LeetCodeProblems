package binarySearch;

// https://leetcode.com/problems/binary-search/discuss/205763/Share-Three-Common-Templates-For-Binary-Search

/**
 * E
 * Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to search target in nums.
 * If target exists, then return its index, otherwise return -1.
 * <p>
 * Example 1:
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * <p>
 * Example 2:
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 * <p>
 * Note:
 * You may assume that all elements in nums are unique.
 * n will be in the range [1, 10000].
 * The value of each element in nums will be in the range [-9999, 9999].
 */
public class BinarySearch_704 {

    public static void main(String[] args) {
        BinarySearch_704 s = new BinarySearch_704();
/*        System.out.println(s.template2(new int[] {1, 2, 3, 4, 5, 6, 7}, 8)); // -1
        System.out.println(s.template2(new int[] {7, 6, 5, 4, 3, 2, 1}, 2)); // -1
        System.out.println(s.template2(new int[] {7, 7, 7, 7, 7, 7, 7, 7}, 2)); // -1
        System.out.println(s.template2(new int[] {1, 2, 3, 4, 5, 6, 7}, 7)); // 6
        System.out.println(s.template2(new int[] {1, 2, 3, 4, 5, 6, 7}, 1)); // 0
        System.out.println(s.template2(new int[] {1, 2, 3, 4, 5, 6, 7}, 5)); // 4
        System.out.println(s.template2(new int[] {5}, 5)); // 0
        System.out.println(s.template2(new int[] {2, 5}, 5)); // 1*/

/*        System.out.println(s.leftMostIndex3(new int[]{2,2}, 3)); //-1
        System.out.println(s.leftMostIndex3(new int[]{1,2,3,3,3,4,5,6}, 3)); // 2
        System.out.println(s.leftMostIndex3(new int[]{1,2,4,4,4,4,5,6}, 3)); //-1
        System.out.println(s.leftMostIndex3(new int[]{3,3,3,3}, 3)); //0
        System.out.println(s.leftMostIndex3(new int[]{1,2,3,3,3,3}, 3)); //2
        System.out.println(s.leftMostIndex3(new int[]{3,3,3,3,4,5,6,7}, 3)); //0
        System.out.println(s.leftMostIndex3(new int[]{3}, 3)); //0*/

        System.out.println(s.rightMostIndex(new int[]{1,2,3,3,3,4,5,6}, 3)); // 4
        System.out.println(s.rightMostIndex(new int[]{1,2,3,4,5}, 3)); //2
        System.out.println(s.rightMostIndex(new int[]{2,2}, 3)); //-1
        System.out.println(s.rightMostIndex(new int[]{1,2,4,4,4,4,5,6}, 3)); //-1
        System.out.println(s.rightMostIndex(new int[]{3,3,3,3}, 3)); //3
        System.out.println(s.rightMostIndex(new int[]{1,2,3,3,3,3}, 3)); //5
        System.out.println(s.rightMostIndex(new int[]{3,3,3,3,4,5,6,7}, 3)); //3
        System.out.println(s.rightMostIndex(new int[]{3}, 3)); //0

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
    /*
        if target is not within the range, we can get Lo=-1 or Hi=array.length
     */
    public int template1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) { // break when left becomes > right
            int mid = left + (right - left) / 2;
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

    // Template 2
    /*
        if target is not within the range, we can get Lo=0 or Hi=array.length-1
        But if we change:
            if(nums[mid]<t) start=mid else end=mid-1
            if(nums[mid]>t) end=mid-1 else start=mid
            if(nums[mid]>t) end=mid else start=mid+1
        we've got 0, or infinite cycle
     */
    public int template2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        while (start < end) { // break when left == right
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }

    public int leftMostIndex3(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length-1;
        while (left < right) {
            int mid = (left + right)/ 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target){
                right = mid - 1;
            } else {
                right = mid;
            }
        }

        return nums[right] == target ? right : -1;
    }

    public int leftMostIndex2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = -1;
        int right = nums.length;
        while (right - left > 1) {
            int mid = left + (right - left) / 2;
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
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
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

    public int rightMostIndex(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length-1;
        while (left < right) {
            int mid = (left + right)/2+1; // +1 so that l = mid; doesn't result in an infinite loop when l + 1 == r.
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        return nums[left] == target ? left : -1;
    }

    public int rightMostIndex2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
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
                return new int[] {0, 0};
            }
            return new int[] {-1, -1};
        }
        int leftMost = -1;
        int rightMost = -1;

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
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
            int mid = left + (right - left) / 2;
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
