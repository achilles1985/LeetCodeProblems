package stack.medium;

import java.util.*;

/** M [marked]
 Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

 Note:
 The length of num is less than 10002 and will be ≥ k.
 The given num does not contain any leading zero.

 Example 1:
 Input: num = "1432219", k = 3
 Output: "1219"
 Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

 Example 2:
 Input: num = "10200", k = 1
 Output: "200"
 Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

 Example 3:
 Input: num = "10", k = 2
 Output: "0"
 Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
/*
    1. Keep order? (yes)
    2. Max value?
 */
/*
 Corner cases:
    1. k == 0 or k >= str.length
    2. If all digits in ascending order and k != 0, remove last k digits.
 */
public class RemoveKDigits_402 {

    public static void main(String[] args) {
        RemoveKDigits_402 s = new RemoveKDigits_402();
        String s1 = "000001230012300".replaceFirst("^0*", "");
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(s.removeKdigits("1432219", 3)); // 1219
        System.out.println(s.removeKdigits("1111111", 3)); // 1111
        System.out.println(s.removeKdigits("100", 1)); // 0
        System.out.println(s.removeKdigits("10200", 1)); // 200
        System.out.println(s.removeKdigits("15428901574", 3)); // 12801574
        System.out.println(s.removeKdigits("112", 1)); // 11
        System.out.println(s.removeKdigits("10200", 1)); // 200
        System.out.println(s.removeKdigits("1107", 1)); //107
        System.out.println(s.removeKdigits("10", 2)); //0
    }

    // O(n^n) - time, O(n) - space
    // If input is longer then 10, NumberFormatException
    public String removeKdigitsBF(String num, int k) {
        if (k == 0) {
            return num;
        }
        if (k >= num.length()) {
            return "0";
        }
        Set<Long> set = new HashSet<>();
        helper(num, 0, num.length() - k, set, new StringBuilder());
        long min = Collections.min(set);

        return String.valueOf(min);
    }

    // O(n) - time, space
    public String removeKdigits(String num, int k) {
        if (k == 0) {
            return num;
        }
        if (k >= num.length()) {
            return "0";
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < num.length(); i++) {
            int number = num.charAt(i) - '0';
            while (!stack.isEmpty() && number < stack.peek() && k-- > 0) {
                stack.pop();
            }
            stack.push(number);
        }
        StringBuilder sb = new StringBuilder();
        // remove the remaining digits from the tail
        while (k-- > 0 && !stack.isEmpty()) {
            stack.pop();
        }
        // remove leading zeros
        while (!stack.isEmpty() && stack.peekLast() == 0) {
            stack.removeLast();
        }
        if (stack.isEmpty()) {
            return "0";
        }
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
        }

        return sb.toString();
    }

    private void helper(String input, int start, int k, Set<Long> set, StringBuilder sb) {
        if (sb.length() == k) {
            long number = Integer.parseInt(sb.toString());
            set.add(number);
            return;
        }
        for (int i = start; i < input.length(); i++) {
            sb.append(input.charAt(i));
            helper(input, i+1, k, set, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
