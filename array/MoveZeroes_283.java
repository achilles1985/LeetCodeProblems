package array;

import java.util.Arrays;

import utils.SolutionUtils;

/** E
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 Example:
 Input: [0,1,0,3,12]
 Output: [1,3,12,0,0]

 Note:
 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.
 */
public class MoveZeroes_283 {

    public static void main(String[] args) {
        MoveZeroes_283 s = new MoveZeroes_283();
        s.moveZeroes2(new int[]{0,1,0,3,12}); //[1,3,12,0,0]
    }

    // O(n*log(n)) - time, not preserve origin order
    public void moveZeroes(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != 0) {
                break;
            }
        }
        int k = 0;
        for (int j = i; j < nums.length; j++) {
            swap(nums, k, j);
            k++;
        }

        SolutionUtils.print(nums);
    }

    // O(n) - time, O(1) - space
    public void moveZeroes2(int[] nums) {
        int i = -1;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                swap(nums, ++i, j);
            }
        }
        SolutionUtils.print(nums);
    }

    private void swap(int[] nums, int from , int to) {
        int temp = nums[from];
        nums[from] = nums[to];
        nums[to] = temp;
    }
}
