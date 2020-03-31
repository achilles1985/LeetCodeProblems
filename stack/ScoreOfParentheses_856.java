package stack;

import java.util.Stack;

/** M
 Given a balanced parentheses string S, compute the score of the string based on the following rule:
 () has score 1
 AB has score A + B, where A and B are balanced parentheses strings.
 (A) has score 2 * A, where A is a balanced parentheses string.

 Example 1:
 Input: "()"
 Output: 1

 Example 2:
 Input: "(())"
 Output: 2

 Example 3:
 Input: "()()"
 Output: 2

 Example 4:
 Input: "(()(()))"
 Output: 6

 Note:
 S is a balanced parentheses string, containing only ( and ).
 2 <= S.length <= 50
 */
public class ScoreOfParentheses_856 {

    public static void main(String[] args) {
        ScoreOfParentheses_856 s = new ScoreOfParentheses_856();
        System.out.println(s.scoreOfParentheses2("()")); //1
        System.out.println(s.scoreOfParentheses2("(())")); //2
        System.out.println(s.scoreOfParentheses2("()()")); //2
        System.out.println(s.scoreOfParentheses2("(()(()))")); //6
    }

    public int scoreOfParentheses2(String S) {
        Stack<Character> stack = new Stack<>();
        int counter = 0;
        for (char c: S.toCharArray()) {
            if (c == '(') {
                stack.push('(');
            } else {
                stack.pop();
                counter++;
            }
        }
        return counter*2;
    }

    // O(n) - time, space
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (char c: S.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w + Math.max(v*2, 1));
            }
        }

        return stack.pop();
    }
}
