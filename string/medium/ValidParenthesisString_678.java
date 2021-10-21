package string.medium;

/** M [marked]
 *  Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid.
 *  We define the validity of a string by these rules:
 *     Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 *     Any right parenthesis ')' must have a corresponding left parenthesis '('.
 *     Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 *     '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 *     An empty string is also valid.
 *
 * Example 1:
 * Input: "()"
 * Output: True
 *
 * Example 2:
 * Input: "(*)"
 * Output: True
 *
 * Example 3:
 * Input: "(*))"
 * Output: True
 *
 * Note:
 *     The string size will be in the range [1, 100].
 */
public class ValidParenthesisString_678 {

    public static void main(String[] args) {
        ValidParenthesisString_678 s = new ValidParenthesisString_678();
        System.out.println(s.checkValidString("((())")); //false
        System.out.println(s.checkValidString("(()))")); //false
        System.out.println(s.checkValidString(")(")); //false
        System.out.println(s.checkValidString("(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())")); //false
        System.out.println(s.checkValidString("((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()")); //true
        System.out.println(s.checkValidString("()")); //true
        System.out.println(s.checkValidString("(*)")); //true
        System.out.println(s.checkValidString("(*))")); //true
    }

    // O(3^n) - time, O(n) - space, we have 3 choices (skip * symbol or substitute it with either closing or opening bracket)
    public boolean checkValidStringBF(String s) {
        return helper(s, 0, 0);
    }

    private boolean helper(String s, int index, int open) {
        if (index == s.length()) {
            return open == 0;
        }
        if (s.charAt(index) == '(') {
            return helper(s, index + 1, open + 1);
        } else if (s.charAt(index) == ')') {
            return open != 0 && helper(s, index + 1, open - 1);
        } else {
            return helper(s, index + 1, open)
                    || helper(s, index + 1, open + 1)
                    || open != 0 && helper(s, index + 1, open - 1);
        }
    }

    // O(n) - time, O(1) - space
    public boolean checkValidString(String s) {
        if (s == null || s.isEmpty() || "*".equals(s)) {
            return true;
        }
        // go left-> right and treat all * as '('
        int open = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '*') {
                open++;
            } else {
                open--;
            }
            if (open < 0) {
                return false;
            }
        }
        // go right -> left and treat all * as ')'
        int close = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ')' || c == '*') {
                close++;
            } else {
                close--;
            }
            if (close < 0) {
                return false;
            }
        }

        return true;
    }
}
