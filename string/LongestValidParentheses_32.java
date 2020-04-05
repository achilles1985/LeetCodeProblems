package string;

import java.util.Stack;

/** H
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
 * parentheses substring.
 *
 * Example 1:
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 *
 * Example 2:
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParentheses_32 {

    public static void main(String[] args) {
        LongestValidParentheses_32 s = new LongestValidParentheses_32();
        System.out.println(s.longestValidParentheses(")()())")); //4
        System.out.println(s.longestValidParentheses2(")()())")); //4

        System.out.println(s.longestValidParentheses("(()")); //2
        System.out.println(s.longestValidParentheses2("(()")); //2
    }

    // O(n) - time, space
    public int longestValidParentheses2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    // O(n^3) - time, O(n) - space
    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (isValid(sub)) {
                    int localMax = sub.length();
                    max = Math.max(localMax, max);
                }
            }
        }
        return max;
    }

    private boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
