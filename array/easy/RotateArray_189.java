package array.easy;

import utils.SolutionUtils;

/** E
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * Follow up:
 *     Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 *     Could you do it in-place with O(1) extra space?
 *
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 * Constraints:
 *     1 <= nums.length <= 2 * 10^4
 *     It's guaranteed that nums[i] fits in a 32 bit-signed integer.
 *     k >= 0
 */
public class RotateArray_189 {

    public static void main(String[] args) {
        RotateArray_189 s = new RotateArray_189();
        int[] nums3 = {1,2}; //[2,1]
        int[] nums2 = {-1,-100,3,99}; //[3,99,-1,-100]
        int[] nums1 = {1,2,3,4,5,6,7}; //[5,6,7,1,2,3,4]
        s.rotate2(nums1, 3);
        s.rotate2(nums2, 2);
        s.rotate2(nums3, 3);

        SolutionUtils.print(nums1);
        SolutionUtils.print(nums2);
        SolutionUtils.print(nums3);
    }

    // O(n) - time, space
    public void rotateBF(int[] nums, int k) {
        if (nums.length == k) {
            return;
        }
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }

    // O(n) - time, O(1) - space
    public void rotate2(int[] nums, int k) {
        if (nums.length == k) {
            return;
        }
        int k1 = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k1 - 1);
        reverse(nums, k1, nums.length - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }



}
