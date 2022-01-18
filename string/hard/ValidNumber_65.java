package string.hard;

/** H
 * A valid number can be split up into these components (in order):
 *     A decimal number or an integer.
 *     (Optional) An 'e' or 'E', followed by an integer.
 *
 * A decimal number can be split up into these components (in order):
 *     (Optional) A sign character (either '+' or '-').
 *     One of the following formats:
 *         One or more digits, followed by a dot '.'.
 *         One or more digits, followed by a dot '.', followed by one or more digits.
 *         A dot '.', followed by one or more digits.
 *
 * An integer can be split up into these components (in order):
 *     (Optional) A sign character (either '+' or '-').
 *     One or more digits.
 *
 * For example, all the following are valid numbers:
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"],
 * while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].
 * Given a string s, return true if s is a valid number.
 *
 * Example 1:
 * Input: s = "0"
 * Output: true
 *
 * Example 2:
 * Input: s = "e"
 * Output: false
 *
 * Example 3:
 * Input: s = "."
 * Output: false
 *
 * Constraints:
 *     1 <= s.length <= 20
 *     s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.
 */
/*
The key is to set rules
 */
public class ValidNumber_65 {

    public static void main(String[] args) {
        ValidNumber_65 s = new ValidNumber_65();
        System.out.println(s.isNumber("0")); //true
        System.out.println(s.isNumber("-123.456e789")); //true
        System.out.println(s.isNumber("+6e-1")); //true
        System.out.println(s.isNumber("1a")); //false
    }

    // O(n) - time, O(1) - space
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenExponent = false;
        boolean seenDot = false;

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (Character.isDigit(curr)) {
                seenDigit = true;
            } else if (curr == '+' || curr == '-') {
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (curr == 'e' || curr == 'E') {
                if (seenExponent || !seenDigit) {
                    return false;
                }
                seenExponent = true;
                seenDigit = false;
            } else if (curr == '.') {
                if (seenDot || seenExponent) {
                    return false;
                }
                seenDot = true;
            } else {
                return false;
            }
        }

        return seenDigit;
    }
}
