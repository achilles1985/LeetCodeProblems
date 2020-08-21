package string.medium;

/** M
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
        System.out.println(s.checkValidString(")(")); //false
        System.out.println(s.checkValidString("()")); //true
        System.out.println(s.checkValidString("(*)")); //true
        System.out.println(s.checkValidString("(*))")); //true
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
        open = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ')' || c == '*') {
                open++;
            } else {
                open--;
            }
            if (open < 0) {
                return false;
            }
        }

        return true;
    }
}