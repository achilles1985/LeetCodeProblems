package stack.medium;

import java.util.Stack;

/**
 * M
 * Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.
 * Formally, a parentheses string is valid if and only if:
 * <p>
 * It is the empty string, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * <p>
 * Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.
 * Example 1:
 * Input: "())"
 * Output: 1
 * <p>
 * Example 2:
 * Input: "((("
 * Output: 3
 * <p>
 * Example 3:
 * Input: "()"
 * Output: 0
 * <p>
 * Example 4:
 * Input: "()))(("
 * Output: 4
 * <p>
 * Note:
 * S.length <= 1000
 * S only consists of '(' and ')' characters.
 */
public class MinimumAddToMakeParenthesesValid_921 {

    public static void main(String[] args) {
        MinimumAddToMakeParenthesesValid_921 s  = new MinimumAddToMakeParenthesesValid_921();
        System.out.println(s.minAddToMakeValid("(()")); // 1
        System.out.println(s.minAddToMakeValid("(((")); // 3
        System.out.println(s.minAddToMakeValid("()")); // 0
        System.out.println(s.minAddToMakeValid("()))((")); // 4
    }

    // O(n) - time, O(n) - space
    public int minAddToMakeValidBF(String S) {
        if (S == null || S.isEmpty()) {
            return 0;
        }
        int close = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                stack.push('(');
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    close++;
                }
            }
        }
        return stack.size() + close;
    }

    // O(n) - time, O(1) - space
    public int minAddToMakeValid(String S) {
        int open = 0;
        int close = 0;

        for (char c : S.toCharArray()) {
            if (c == '(') {
                open++;
            } else {
                if (open == 0) {
                    close++;
                } else {
                    open--;
                }
            }
        }

        return close + open;
    }
}
