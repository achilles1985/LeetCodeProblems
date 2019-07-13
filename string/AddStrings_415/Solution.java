package string.AddStrings_415;

import java.util.Stack;
import java.util.stream.Collectors;

/** Easy
 Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

 Note:
 The length of both num1 and num2 is < 5100.
 Both num1 and num2 contains only digits 0-9.
 Both num1 and num2 does not contain any leading zero.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class Solution {

    // wrong solution
    // https://leetcode.com/problems/add-strings/submissions/
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int c = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || c > 0){
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int s = n1 + n2 + c;
            sb.append(s % 10);
            c = s / 10;
            i--;
            j--;
        }
        return sb.reverse().toString();
    }

    public String addStrings2(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        if (num1.length() == 1 && num2.length() == 1) {
            return String.valueOf((num1.charAt(0) - '0') + (num2.charAt(0) - '0'));
        }

        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int sum = 0;
        int remainder = 0;
        Stack<Integer> stack = new Stack<>();
        while (i >= 0 && j >= 0) {
            sum = remainder + ((num1.charAt(i) - '0') + (num2.charAt(j) - '0'));
            remainder = 0;
            if (sum / 10 != 0) {
                remainder = sum / 10;
                sum = sum % 10;
            }
            stack.push(sum);
            i--;
            j--;
        }
        while (i >= 0) {
            stack.push(remainder + (num1.charAt(i) - '0'));
            i--;
            remainder = 0;
        }
        while (j >= 0) {
            stack.push(remainder + (num2.charAt(j) - '0'));
            j--;
            remainder = 0;
        }

        if (remainder != 0) {
            stack.push(remainder);
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }

        return builder.toString();
    }
}
