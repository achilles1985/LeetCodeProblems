package array.easy;

import utils.SolutionUtils;

/**E
 * Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
 * Return any array that satisfies this condition.

 * Example 1:
 * Input: nums = [3,1,2,4]
 * Output: [2,4,3,1]
 * Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 *
 * Constraints:
 *     1 <= nums.length <= 5000
 *     0 <= nums[i] <= 5000
 */
public class SortArrayByParity_905 {

    public static void main(String[] args) {
        SortArrayByParity_905 s = new SortArrayByParity_905();
        SolutionUtils.print(s.sortArrayByParity(new int[]{3,1,2,4})); //[2,4,3,1]
        SolutionUtils.print(s.sortArrayByParity(new int[]{3,1,5,7})); //[3,1,5,7]
        SolutionUtils.print(s.sortArrayByParity(new int[]{4,2,6,8})); //[4,2,6,8]
    }

    public int[] sortArrayByParity(int[] nums) {
        int even = 0, odd = nums.length-1;
        while (even < odd) {
            if (nums[even]%2 != 0 && nums[odd]%2 == 0) {
                swap(odd,even,nums);
            }
            while (even < nums.length && nums[even]%2 == 0) {
                even++;
            }
            while (odd >= 0 && nums[odd]%2 != 0) {
                odd--;
            }
        }
        return nums;
    }

    private void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
