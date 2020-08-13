package string.medium;

/** M
 * Implement atoi which converts a string to an integer.
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found.
 * Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 *     Only the space character ' ' is considered as whitespace character.
 *     Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 *     If the numerical value is out of the range of representable values, INT_MAX (2^31 − 1) or INT_MIN (−2^31) is returned.
 *
 * Example 1:
 * Input: "42"
 * Output: 42
 *
 * Example 2:
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 *              Then take as many numerical digits as possible, which gets 42.
 *
 * Example 3:
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 *
 * Example 4:
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 *              digit or a +/- sign. Therefore no valid conversion could be performed.
 *
 * Example 5:
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 *              Thefore INT_MIN (−231) is returned.
 */
public class StringToInteger_atoi_8 {

    public static void main(String[] args) {
        int a1 = 2_147_483_647;
        int a2 = -2_147_483_648;

        StringToInteger_atoi_8 s = new StringToInteger_atoi_8();
        System.out.println(s.myAtoi("  -0012a42")); //-12
        System.out.println(s.myAtoi("  -42")); //-42
        System.out.println(s.myAtoi("42")); //42
        System.out.println(s.myAtoi("  ")); //0
        System.out.println(s.myAtoi("+42")); //42
        System.out.println(s.myAtoi("words and 987")); //0
        System.out.println(s.myAtoi("  words and 987")); //0
        System.out.println(s.myAtoi("123words and 987")); //123
        System.out.println(s.myAtoi("123-words and 987")); //123
        System.out.println(s.myAtoi("  123 wo rds and 987 df 0")); //123
        System.out.println(s.myAtoi("-912834724332")); // Integer.MIN_VALUE
        System.out.println(s.myAtoi("91283472332")); // Integer.MAX_VALUE
    }

    // O(d) - time, O(1) - space
    public int myAtoi(String str) {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }
        String trimmed = str.trim();
        char firstChar = trimmed.charAt(0);
        if (!Character.isDigit(firstChar) && firstChar != '+' && firstChar != '-') {
            return 0;
        }
        long res = 0;
        for (int i = (firstChar == '-' || firstChar == '+') ? 1 : 0; i < trimmed.length(); i++) {
            char symbol = trimmed.charAt(i);
            if (!Character.isDigit(symbol)) {
                return firstChar == '-' ? -(int)res : (int)res;
            }
            res = res * 10 + (symbol - '0');
            if (firstChar == '-' && res > Integer.MAX_VALUE) {
                return Integer.MIN_VALUE;
            }
            if (res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
        }

        return firstChar == '-' ? -(int)res : (int)res;
    }

}
