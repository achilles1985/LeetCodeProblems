package array.medium;

import utils.SolutionUtils;

/** M
 Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

 Example:
 Input:  [1,2,3,4]
 Output: [24,12,8,6]

 Note: Please solve it without division and in O(n).
 Follow up:
 Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductOfArrayExceptSelf_238 {

    public static void main(String[] args) {
        ProductOfArrayExceptSelf_238 s = new ProductOfArrayExceptSelf_238();
        SolutionUtils.print(s.productExceptSelf(new int[]{1,2,3,4})); // [24,12,8,6]
    }

    // O(n) - time, O(n) - space
    public int[] productExceptSelf(int[] nums) {
        int size = nums.length;
        int[] leftProduct = new int[size];
        leftProduct[0] = 1;
        for (int i = 1; i < size; i++) {
            leftProduct[i] = leftProduct[i-1]*nums[i-1];
        }

        int[] rightProduct = new int[size];
        rightProduct[size-1] = 1;
        for (int i = size-2; i >= 0; i--) {
            rightProduct[i] = rightProduct[i+1]*nums[i+1];
        }

        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = leftProduct[i]*rightProduct[i];
        }

        return res;
    }
}
