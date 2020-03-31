package array;

import utils.SolutionUtils;

/** M
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 The replacement must be in-place and use only constant extra memory.
 Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 */
/*
    One should observe a patten. Find the first number smaller to the left then last number. Find the next greater number for that number and swap them. Then reverse subarray.
 */
public class NextPermutation_31 {

    public static void main(String[] args) {
        NextPermutation_31 s = new NextPermutation_31();
        s.nextPermutation(new int[]{1,5,8,4,7,6,5,3,1,9,5}); //
        s.nextPermutation(new int[]{1,3,2,6,5,7,9}); // 1326579
        s.nextPermutation(new int[]{1,2,5,4,7,6}); // 125647
        s.nextPermutation(new int[]{1,1}); // 1,1
        s.nextPermutation(new int[]{1,2,3}); // 1,3,2
        s.nextPermutation(new int[]{2,1,5,4,3}); // 2,3,1,4,5
        s.nextPermutation(new int[]{3,2,1}); // 1,2,3
        s.nextPermutation(new int[]{1,1,5}); // 1,5,1
    }

    // O(n) - time, O(1) - space
    public void nextPermutation(int[] nums) {
        int i = nums.length-2;
        while (i >= 0 && nums[i] >= nums[i+1]) {
            i--;
        }
        int j = nums.length-1;
        if (i >= 0) {
            while (j > 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(i, j, nums);
        }
        reverse(nums, i+1);

        SolutionUtils.print(nums);
    }

    private void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length-1;
        while (i < j) {
            swap(i, j, nums);
            i++;
             j--;
        }
    }

    private void swap(int from, int to, int[] nums) {
        int temp  = nums[from];
        nums[from] = nums[to];
        nums[to] = temp;
    }
}
