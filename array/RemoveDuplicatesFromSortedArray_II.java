package array;

import java.util.Arrays;

import utils.SolutionUtils;

public class RemoveDuplicatesFromSortedArray_II {

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray_II s = new RemoveDuplicatesFromSortedArray_II();
        SolutionUtils.print(s.removeDuplicates(new int[] {1,1,1,2,2,3,3,4}));
        SolutionUtils.print(s.removeDuplicates(new int[] {1,2,3,4}));
    }

    // O(n) - time, O(1) - space
    public int[] removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }

        return Arrays.copyOf(nums, i + 1);
    }
}
