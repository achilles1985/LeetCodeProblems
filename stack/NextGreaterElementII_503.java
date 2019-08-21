package stack;

import java.util.Arrays;
import java.util.Stack;

import utils.SolutionUtils;

/** M
 Given a circular array (the next element of the last element is the first element of the array), findAllPaths the Next Greater Number for every element.
 The Next Greater Number of a number x is the first greater number to its traversing-order next in the array,
 which means you could search circularly to find its next greater number.
 If it doesn't exist, output -1 for this number.

 Example 1:
 Input: [1,2,1]
 Output: [2,-1,2]

 Explanation: The first 1's next greater number is 2;
 The number 2 can't find next greater number;
 The second 1's next greater number needs to search circularly, which is also 2.

 Note: The length of given array won't exceed 10000.
 */
public class NextGreaterElementII_503 {

    public static void main(String[] args) {
        NextGreaterElementII_503 s = new NextGreaterElementII_503();

        SolutionUtils.print(s.nextGreaterElements(new int[]{5,4,3,2,1})); // [-1,5,5,5,5]
        SolutionUtils.print(s.nextGreaterElements(new int[]{1,2,1})); // [2,-1,2]
        SolutionUtils.print(s.nextGreaterElements(new int[]{2,4})); // [4,-1]
        SolutionUtils.print(s.nextGreaterElements(new int[]{1,3,4,2})); // [3,4,-1,3]
    }

    // O(n^2) - time, O(1) - space
    public int[] nextGreaterElementsBF(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < nums.length; i++) {
            int j = i;
            do {
                j++;
                j %= res.length;
                if (nums[j] > nums[i]) {
                    res[i] = nums[j];
                    break;
                }
            } while (j!= i);
        }

        return res;
    }

    // Just traverse an array twice
    // O(n) - time, O(n) - space
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        int length = nums.length;
        Arrays.fill(res, -1);
        for (int i = 0; i < 2*nums.length; i++) {
            while (!stack.isEmpty() && nums[i%length] > nums[stack.peek()%length]) {
                int idx = stack.pop();
                res[idx%length] = nums[i%length];
            }
            stack.push(i%length);
        }

        return res;
    }

}
