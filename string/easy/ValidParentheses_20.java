package string.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**E
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is
 * valid.
 *
 * An input string is valid if:
 *     Open brackets must be closed by the same type of brackets.
 *     Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 * Input: "()"
 * Output: true
 *
 * Example 2:
 * Input: "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: "(]"
 * Output: false
 *
 * Example 4:
 * Input: "([)]"
 * Output: false
 *
 * Example 5:
 * Input: "{[]}"
 * Output: true
 */
public class ValidParentheses_20 {

    public static void main(String[] args) {
        ValidParentheses_20 s = new ValidParentheses_20();
        System.out.println(s.isValid2("(])")); //false
        System.out.println(s.isValid2("}])(]}")); //false
        System.out.println(s.isValid2("}])")); //false
        System.out.println(s.isValid2("({[")); //false
        System.out.println(s.isValid2("()")); //true
        System.out.println(s.isValid2("()[]{}")); //true
        System.out.println(s.isValid2("(]")); //false
        System.out.println(s.isValid2("([)]")); //false
        System.out.println(s.isValid2("([])")); //true
    }

    // O(n) - time, space
    public boolean isValid2(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        Set<Character> opens = new HashSet<>(Arrays.asList('(', '[', '{'));
        Map<Character, Character> openToClose = new HashMap<>();
        openToClose.put('(', ')');
        openToClose.put('[', ']');
        openToClose.put('{', '}');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char bracket = s.charAt(i);
            if (opens.contains(bracket)) {
                stack.push(bracket);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!stack.isEmpty() && openToClose.get(stack.peek()) != bracket) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    // O(n) - time, space
    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> closeToOpen = new HashMap<>();
        closeToOpen.put(')', '(');
        closeToOpen.put(']', '[');
        closeToOpen.put('}', '{');
        for (int i = 0; i < s.length(); i++) {
            char item = s.charAt(i);
            if (stack.isEmpty() || !closeToOpen.containsKey(item)) {
                stack.push(item);
            } else if (closeToOpen.get(item).equals(stack.peek())) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
