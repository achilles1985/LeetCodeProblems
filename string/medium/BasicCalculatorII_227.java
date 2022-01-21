package string.medium;

import java.util.Stack;

/**
 * M
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer
 * division should truncate toward zero.
 * <p>
 * Example 1:
 * Input: "3+2*2"
 * Output: 7
 * <p>
 * Example 2:
 * Input: " 3/2 "
 * Output: 1
 * <p>
 * Example 3:
 * Input: " 3+5 / 2 "
 * Output: 5
 * <p>
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class BasicCalculatorII_227 {

    public static void main(String[] args) {
        BasicCalculatorII_227 s = new BasicCalculatorII_227();
        System.out.println(s.calculateBF("12+1*2*3-4+6/2-5")); //
        System.out.println(s.calculateBF("-42")); //-42
        System.out.println(s.calculateBF("42")); //42
        System.out.println(s.calculateBF("3/2")); //1
        System.out.println(s.calculateBF("2+3*2-2+5")); //11

        System.out.println(s.calculate("2+3*2-2+5")); //8
        System.out.println(s.calculate("3/2")); //1
    }

    // O(n) - time, space
    public int calculateBF(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0;
        char operation = '+';
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber);
                } else if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    // O(n) - time, O(1) - space
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int length = s.length();
        int currentNumber = 0, lastNumber = 0, result = 0;
        char operation = '+';
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) { // if the last num, process
                if (operation == '+' || operation == '-') {
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        result += lastNumber;

        return result;
    }

}
