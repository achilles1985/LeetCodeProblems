package string.medium;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * M [marked]
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
 * so that the resulting parentheses string is valid and return any valid string.
 * Formally, a parentheses string is valid if and only if:
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * <p>
 * Example 1:
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * <p>
 * Example 2:
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * <p>
 * Example 3:
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * <p>
 * Example 4:
 * Input: s = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is one of  '(' , ')' and lowercase English letters.
 */
public class MinimumRemoveToMakeValidParentheses_1249 {

    public static void main(String[] args) {
        MinimumRemoveToMakeValidParentheses_1249 s = new MinimumRemoveToMakeValidParentheses_1249();
        System.out.println(s.minRemoveToMakeValid2("a)b(c)d")); // "ab(c)d"

        System.out.println(s.minRemoveToMakeValid("lee(t(c)o)de)")); // lee(t(c)o)de
        System.out.println(s.minRemoveToMakeValid("))((")); // ""

        System.out.println(s.minRemoveToMakeValid("())()(((")); // ()()
        System.out.println(s.minRemoveToMakeValid("((())")); // (()))
        System.out.println(s.minRemoveToMakeValid("((()))))")); // (()))

        System.out.println(s.minRemoveToMakeValid("a)b(c)d")); // ab(c)d
        System.out.println(s.minRemoveToMakeValid("))((")); // ""
        System.out.println(s.minRemoveToMakeValid("(a(b(c)d)")); // a(b(c)d)

        System.out.println(s.minRemoveToMakeValid("((())")); // "(())"
        System.out.println(s.minRemoveToMakeValid("(()))")); // "(())"
        System.out.println(s.minRemoveToMakeValid("))((")); // ""
        System.out.println(s.minRemoveToMakeValid("(a(b(c)d)")); // a(b(c)d)
    }

    // O(n) - time, space
    public String minRemoveToMakeValidBF(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        Set<Integer> toRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            }
            if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    toRemove.add(i);
                }
            }
        }
        while (!stack.isEmpty()) {
            toRemove.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!toRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }

    // O(n) - time, O(1) - space
    public String minRemoveToMakeValid(String s) {
        // Parse 1: Remove all invalid ")"
        StringBuilder sb = new StringBuilder();
        int openSeen = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openSeen++;
                balance++;
            }
            if (c == ')') {
                if (balance == 0) {
                    continue;
                }
                balance--;
            }
            sb.append(c);
        }

        // Parse 2: Remove the rightmost "("
        StringBuilder result = new StringBuilder();
        int openToKeep = openSeen - balance;
        for (int i = 0; i < sb.length(); i++) { // we cannot traverse via the same sb, that's why instantiated result
            char c = sb.charAt(i);
            if (c == '(') {
                openToKeep--;
            }
            if (c == '(' && openToKeep < 0) {
                continue;
            }
            result.append(c);
        }

        return result.toString();
    }

    public String minRemoveToMakeValid2(String s) {
        // remove all invalid ')'
        int open = 0, balance = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c  = s.charAt(i);
            if (c == '(') {
                open++;
                balance++;
            }
            if (c == ')') {
                if (balance == 0) {
                    continue;
                }
                balance--;
            }
            sb.append(c);
        }

        // 2. remove right most '('
        int openToKeep = open - balance;
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                openToKeep--;
            }
            if (sb.charAt(i) == '(' && openToKeep < 0) {
                continue;
            }
            sb2.append(sb.charAt(i));
        }

        return sb2.toString();
    }
}
