package array.ContainerWithMostWater_11;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 *
 * Example:
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */
public class Solution {

    // O(n) The idea is to find the second largest element in an array with the help of the heap
    public int maxArea(int[] nums) {
        int firstMax = 0;
        int secondMax = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > firstMax) {
                secondMax = firstMax;
                firstMax = nums[i];
            } else if (nums[i] != firstMax && nums[i] > secondMax) {
                secondMax = nums[i];
            }
        }

        return secondMax*secondMax;
    }
}
