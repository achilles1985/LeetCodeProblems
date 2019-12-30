package string;

import java.util.Stack;

/**M
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
 * exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those
 * repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 * Examples:
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString_394 {

    public static void main(String[] args) {
        DecodeString_394 s = new DecodeString_394();
        System.out.println(s.decodeString("3[a]2[bc]")); //aaabcbc
        System.out.println(s.decodeString("3[a2[c]]")); //accaccacc
        System.out.println(s.decodeString("2[abc]3[cd]ef")); //abcabccdcdcdef
    }

    // O(n) - time, space
    public String decodeString(String s) {
        Stack<Integer> digits = new Stack<>();
        Stack<String> words = new Stack<>();
        String result = "";
        for (int i = 0; i < s.length();) {
            if (Character.isDigit(s.charAt(i))) {
                int num = 0;
                while (Character.isDigit(s.charAt(i))) {
                    num = num*10 + s.charAt(i)-'0';
                    i++;
                }
                digits.push(num);
            } else if (s.charAt(i) == '[') {
                words.push(result);
                result = "";
                i++;
            } else if (Character.isAlphabetic(s.charAt(i))) {
                while (i < s.length() && Character.isAlphabetic(s.charAt(i))) {
                    result += s.charAt(i++);
                }
            } else if (s.charAt(i++) == ']') {
                String word = words.pop();
                Integer digit = digits.pop();
                for (int j = 0; j < digit; j++) {
                    word += result;
                }
                result = word;
            }
        }
        return result;
    }
}
