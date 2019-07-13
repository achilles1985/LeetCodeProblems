package array.RemoveDuplicatesFromSortedArray_56;

/**
 Given a sorted array, remove the duplicates in-place such that each element appear only once and return the new length.
 Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

 Example:
 Given nums = [1,1,2],
 Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 It doesn't matter what you leave beyond the new length.
 */
public class Solution {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[j] != nums[i]) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }


    public int removeDuplicates2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int k = 1;
        int i = 0;
        while (k < nums.length) {
            if (nums[i] == nums[k]) {
                k++;
            } else {
                nums[++i] = nums[k];
            }
        }

        return i + 1;
    }

}
