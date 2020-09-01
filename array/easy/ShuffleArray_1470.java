package array.easy;

import utils.SolutionUtils;

/** E
 Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
 Return the array in the form [x1,y1,x2,y2,...,xn,yn].

 Example 1:
 Input: nums = [2,5,1,3,4,7], n = 3
 Output: [2,3,5,4,1,7]
 Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].

 Example 2:
 Input: nums = [1,2,3,4,4,3,2,1], n = 4
 Output: [1,4,2,3,3,2,4,1]

 Example 3:
 Input: nums = [1,1,2,2], n = 2
 Output: [1,2,1,2]

 Constraints:
 1 <= n <= 500
 nums.length == 2n
 1 <= nums[i] <= 10^3
 */
public class ShuffleArray_1470 {

    public static void main(String[] args) {
        ShuffleArray_1470 s = new ShuffleArray_1470();
        SolutionUtils.print(s.shuffle(new int[]{2,5,1,3,4,7}, 3)); //[2,3,5,4,1,7]
        SolutionUtils.print(s.shuffle(new int[]{1,2,3,4,4,3,2,1}, 4)); //[1,4,2,3,3,2,4,1]
        SolutionUtils.print(s.shuffle(new int[]{1,1,2,2}, 2)); //[1,2,1,2]
    }

    // O(n) - time, O(1) - space
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[nums.length];
        int j = 0;
        for (int i = n; i < nums.length; i++) {
            result[j++] = nums[i-n];
            result[j++] = nums[i];
        }
        return result;
    }
}
