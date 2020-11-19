package binarySearch.medium;

import java.util.Arrays;

/**M
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4

 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 */
/*
    Pay attention! Difficult question!
 */
public class SearchInRotatedSortedArray_33 {

    public static void main(String[] args) {
        SearchInRotatedSortedArray_33 s = new SearchInRotatedSortedArray_33();
        System.out.println(s.search(new int[]{5,1,3}, 3)); //2
        System.out.println(s.search(new int[]{3,1}, 1)); //1
        System.out.println(s.search(new int[]{1,3}, 1)); //0
        System.out.println(s.search(new int[]{1}, 1)); //0
        System.out.println(s.search(new int[]{4,5,6,7,0,1,2}, 0)); //4
        System.out.println(s.search(new int[]{4,5,6,7,0,1,2}, 3)); //-1
    }

    // O(log(n)) - time, O(1) - space
    public int search(int[] nums, int target) {
        int pivot = findPivot(nums);
        if (pivot == 0) {
            int res = Arrays.binarySearch(nums, 0, nums.length, target);
            return res < 0 ? -1 : res;
        }
        if (target > nums[nums.length-1]) {
            int res = Arrays.binarySearch(nums, 0, pivot, target);
            return res < 0 ? -1 : res;
        }
        int res = Arrays.binarySearch(nums, pivot, nums.length, target);
        return res < 0 ? -1 : res;
    }

    private int findPivot(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while(low < high){
            int mid = (low + high) / 2;
            if(nums[high] < nums[mid]){
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high;
    }
}
