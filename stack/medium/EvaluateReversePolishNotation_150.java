package stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**M [marked]
 Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 Valid operators are +, -, *, /. Each operand may be an integer or another expression.

 Note:
 Division between two integers should truncate toward zero.
 The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.

 Example 1:
 Input: ["2", "1", "+", "3", "*"]
 Output: 9
 Explanation: ((2 + 1) * 3) = 9

 Example 2:
 Input: ["4", "13", "5", "/", "+"]
 Output: 6
 Explanation: (4 + (13 / 5)) = 6

 Example 3:
 Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 Output: 22
 Explanation:
 ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 = ((10 * (6 / (12 * -11))) + 17) + 5
 = ((10 * (6 / -132)) + 17) + 5
 = ((10 * 0) + 17) + 5
 = (0 + 17) + 5
 = 17 + 5
 = 22
 */
public class EvaluateReversePolishNotation_150 {

    public static void main(String[] args) {
        EvaluateReversePolishNotation_150 s = new EvaluateReversePolishNotation_150();
        System.out.println(s.evalRPN2(new String[] { "2", "1", "+", "3", "*" })); // 9
        System.out.println(s.evalRPN2(new String[] { "4", "13", "5", "/", "+" })); // 6
        System.out.println(s.evalRPN2(new String[] { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" })); // 22
    }

    // O(n) - time, space
    public int evalRPN2(String[] tokens) {
        Deque<Integer> numbers = new ArrayDeque<>();
        for (String token: tokens) {
            if (token.equals("+")) {
                int num1 = numbers.pop();
                int num2 = numbers.pop();
                numbers.push(num1 + num2);
            } else if (token.equals("*")) {
                int num1 = numbers.pop();
                int num2 = numbers.pop();
                numbers.push(num1*num2);
            } else if (token.equals("-")) {
                int num1 = numbers.pop();
                int num2 = numbers.pop();
                numbers.push(num2 - num1);
            } else if (token.equals("/")) {
                int num1 = numbers.pop();
                int num2 = numbers.pop();
                numbers.push(num2 / num1);
            } else {
                int digit = 0;
                boolean isNegative = false;
                if (token.charAt(0) == '-') {
                    isNegative = true;
                }
                for (int i = isNegative ? 1 : 0; i < token.length(); i++) {
                    char c = token.charAt(i);
                    digit = digit*10 + (c - '0');
                }
                numbers.push(isNegative ? -digit : digit);
            }
        }
        return numbers.pop();
    }
}
