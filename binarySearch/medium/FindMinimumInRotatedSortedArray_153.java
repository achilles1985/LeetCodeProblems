package binarySearch.medium;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
/** M [marked]
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
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
// 0 1 2 3 4 5 6
// 4,5,6,7,0,1,2
// One can observe that as soon as curr element > last one, we need to go to the right, otherwise, to the left.
public class FindMinimumInRotatedSortedArray_153 {

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray_153 s = new FindMinimumInRotatedSortedArray_153();
        System.out.println(s.findMin(new int[]{5,4,3,2,1})); //1
        System.out.println(s.findMin(new int[]{1,2,3,4,5})); //1
        System.out.println(s.findMin(new int[]{3,1,2})); //1
        System.out.println(s.findMin(new int[]{3,4,5,1,2})); //1
        System.out.println(s.findMin(new int[]{4,5,6,7,0,1,2})); //0
        System.out.println(s.findMin(new int[]{4,5,6,7,8,9,0,1,2,3})); //0
    }

    // O(n) - time, O(1) - space
    public int findMinBF(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                min = nums[i];
            }
        }
        return min;
    }

    // O(log(n)) - time, O(1) - space
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while(low < high){
            int mid = (low + high) / 2;
            if(nums[mid] > nums[high]){ //nums[mid] is definitely not a pivot
                low = mid + 1;
            } else {
                high = mid; // nums[mid] might be a pivot
            }
        }
        return nums[low];
    }

}
