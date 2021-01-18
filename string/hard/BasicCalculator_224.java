package string.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * H
 * Given a string s representing an expression, implement a basic calculator to evaluate it.
 * <p>
 * Example 1:
 * Input: s = "1 + 1"
 * Output: 2
 * <p>
 * Example 2:
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * <p>
 * Example 3:
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * <p>
 * Constraints:
 * 1 <= s.length <= 3 * 105
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 */
public class BasicCalculator_224 {

    public static void main(String[] args) {
        BasicCalculator_224 s = new BasicCalculator_224();
        System.out.println(s.calculate("(1-(4+5+2)-3)+(6+8)")); //
        System.out.println(s.calculate("1 + 1")); //2
        System.out.println(s.calculate(" 2-1 + 2 ")); //3
    }

    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis
            }
        }
        if (number != 0) {
            result += sign * number;
        }
        return result;
    }
}
