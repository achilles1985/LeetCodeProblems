package bitManipulation.SingleNumberIII_260;

/** M
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 Example:
 Input:  [1,2,1,3,2,5]
 Output: [3,5]

 Note:
 The order of the result is not important. So in the above example, [5, 3] is also correct.
 Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */
public class Solution {

    // O(n) - time, O(1) - space
    // https://www.geeksforgeeks.org/find-the-two-numbers-with-odd-occurences-in-an-unsorted-array/
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num: nums) {
            xor ^= num;
        }

        int x = 0;
        int y = 0;
        int rightMostBit = xor & -(xor - 1);
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & rightMostBit) > 0) {
                x ^= nums[i];
            } else {
                y ^= nums[i];
            }
        }

        return new int[] {x,y};
    }
}
