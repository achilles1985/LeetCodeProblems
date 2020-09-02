package array.easy;

import java.util.List;

import utils.SolutionUtils;

/** E
 Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 You may assume the integer does not contain any leading zero, except the number 0 itself.

 Example 1:
 Input: [1,2,3]
 Output: [1,2,4]
 Explanation: The array represents the integer 123.

 Example 2:
 Input: [4,3,2,1]
 Output: [4,3,2,2]
 Explanation: The array represents the integer 4321.
 */
public class PlusOne_66 {

    public static void main(String[] args) {
        PlusOne_66 s = new PlusOne_66();
        SolutionUtils.print(s.plusOne2(new int[]{9,9,9})); //[1,0,0,0]
        SolutionUtils.print(s.plusOne2(new int[]{1,2,3})); //[1,2,4]
        SolutionUtils.print(s.plusOne2(new int[]{1,9,9,9})); //[2,0,0,0]
        SolutionUtils.print(s.plusOne2(new int[]{4,3,2,1})); //[4,3,2,2]
    }

    // O(n) - time, O(1) - space
    public int[] plusOne2(int[] digits) {
        if (digits == null || digits.length == 0) {
            return new int[]{};
        }
        int carry = 0;
        digits[digits.length - 1] = digits[digits.length - 1] + 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            int d = sum%10;
            carry = sum/10;
            digits[i] = d;
        }
        if (carry == 1) {
            int[] resized = new int[digits.length + 1];
            System.arraycopy(digits, 0, resized, 1, digits.length);
            resized[0] = 1;
            digits = resized;
        }
        return digits;
    }

    public List<Integer> plusOne(List<Integer> digits) {
        int lastIndex = digits.size();
        digits.set(lastIndex, digits.get(lastIndex) + 1);
        for (int i = lastIndex; i > 0 && digits.get(i) == 10; i--) {
            digits.set(i, 0);
            digits.set(i-1, digits.get(i-1) + 1);
        }
        if (digits.get(0) == 10) {
            digits.set(0,0);
            digits.add(0,1);
        }

        return digits;
    }
}
