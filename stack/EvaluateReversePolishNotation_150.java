package stack;

import java.util.Stack;

/**M
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
        System.out.println(s.evalRPN(new String[] { "2", "1", "+", "3", "*" })); // 9
        System.out.println(s.evalRPN(new String[] { "4", "13", "5", "/", "+" })); // 6
        System.out.println(s.evalRPN(new String[] { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" })); // 22
    }

    public static double evaluate(String expr) {
        String[] tokens = expr.split(" ");
        Stack<Double> stack = new Stack<>();
        for(String token : tokens) {
            switch(token) {
                case "+" : {
                    double val1 = stack.pop();
                    double val2 = stack.pop();
                    stack.push(val1 + val2);
                    break;
                }
                case "-" : {
                    double val2 = stack.pop();
                    double val1 = stack.pop();
                    stack.push(val1 - val2);
                    break;
                }
                case "*" : {
                    double val1 = stack.pop();
                    double val2 = stack.pop();
                    stack.push(val1 * val2);
                    break;
                }
                case "/" : {
                    double val2 = stack.pop();
                    double val1 = stack.pop();
                    stack.push(val1 / val2);
                    break;
                }
                default :
                    stack.push(Double.parseDouble(token));
            }
        }
        return stack.pop();
    }

    // O(n) - time, O(n) - space because of the stack
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (isOperator(tokens[i])) {
                stack.push(eval(stack.pop(), stack.pop(), tokens[i]));
            } else {
                stack.push(Integer.valueOf(tokens[i]));
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String value) {
        return "+".equals(value) || "-".equals(value) || "*".equals(value) || "/".equals(value);
    }

    private int eval(int value1, int value2, String operator) {
        switch (operator) {
            case "+": return value1 + value2;
            case "-": return value2 - value1;
            case "*": return value1 * value2;
            case "/": return value2 / value1;
            default: throw new IllegalArgumentException("Wrong operator");
        }
    }
}
