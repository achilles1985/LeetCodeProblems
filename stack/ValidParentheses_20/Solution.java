package stack.ValidParentheses_20;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 An input string is valid if:
 Open brackets must be closed by the same type of brackets.
 Open brackets must be closed in the correct order.
 Note that an empty string is also considered valid.

 Example 1:
 Input: "()"
 Output: true

 Example 2:
 Input: "()[]{}"
 Output: true

 Example 3:
 Input: "(]"
 Output: false

 Example 4:
 Input: "([)]"
 Output: false

 Example 5:
 Input: "{[]}"
 Output: true
 */
public class Solution {

    // O(n) - time, O(n) - space
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.empty() || map.containsKey(c)) {
                stack.push(c);
            } else {
                if (map.get(stack.peek()) == c) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
