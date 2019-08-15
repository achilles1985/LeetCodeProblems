package binarySearch;

import utils.SolutionUtils;

/**
 Given an array of comparable objects, you can find either the min or the max of the
 elements in the array with n -1comparisons, where n is the length of the array.
 */
public class FindMinAndMaxSimultaneously {

    public static void main(String[] args) {
        FindMinAndMaxSimultaneously s = new FindMinAndMaxSimultaneously();
        SolutionUtils.print(s.findMinMax(new int[]{3,2,5,1,2,4})); // [1,5]
        SolutionUtils.print(s.findMinMax(new int[]{3,2,5,1,2})); // [1,5]
        SolutionUtils.print(s.findMinMax(new int[]{3,3,3,3})); // [3,3]
    }

    public int[] findMinMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i+=2) {
            int localMin = nums[i-1];
            int localMax = nums[i];
            if (nums[i] < nums[i-1]) {
                localMin = nums[i];
                localMax = nums[i-1];
            }
            min = Math.min(min, localMin);
            max = Math.max(max, localMax);
        }
        return new int[]{min,max};
    }
}
