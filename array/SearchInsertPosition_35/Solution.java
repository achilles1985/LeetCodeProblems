package array.SearchInsertPosition_35;

/**
 Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 You may assume no duplicates in the array.
 */
public class Solution {

    public int searchInsert(int[] nums, int target) {
        return binarySearch(0, nums.length, nums, target);
    }

    private int binarySearch(int start, int end, int[] nums, int target) {
        int fromIndex = start;
        int toIndex = end-1;

        while(fromIndex <= toIndex) {
            int middle = (fromIndex + toIndex)/2;

            if (nums[middle] == target) {
                return middle;
            } else if (target < nums[middle]) {
                toIndex = middle-1;
            } else {
                fromIndex = middle+1;
            }
        }

        return fromIndex;
    }
}
