package string.medium;

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
        //System.out.println(s.decodeString("2[a[3[bc[4[d]]]]]")); //aaabcbc
        System.out.println(s.decodeString2("3[a]2[bc]")); //aaabcbc
        System.out.println(s.decodeString2("3[a2[c]]")); //accaccacc
        System.out.println(s.decodeString2("2[abc]3[cd]ef")); //abcabccdcdcdef
    }

    // incorrect
    public String decodeString2(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        Stack<Integer> numbers = new Stack<>();
        Stack<String> letters = new Stack<>();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int number = 0;
                number = number * 10 + c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    c = s.charAt(++i);
                    number = number * 10 + c - '0';
                }
                numbers.push(number);

            } else if (Character.isAlphabetic(c)) {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (i + 1 < s.length() && Character.isAlphabetic(s.charAt(i+1))) {
                    c = s.charAt(++i);
                    sb.append(c);
                }
                letters.push(sb.toString());
            } else if (c == ']') {
                int poppedNumber = numbers.pop();
                String poppedWord = letters.pop();
                for (int j = 1; j <= poppedNumber; j++) {
                    res.append(poppedWord);
                }
            }
        }
        return res.toString();
    }

    // incorrect
    // O(n) - time, space
    public String decodeString(String s) {
        Stack<Integer> digits = new Stack<>();
        Stack<String> words = new Stack<>();
        String result = "";
        for (int i = 0; i < s.length();) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = 0;
                while (Character.isDigit(c)) {
                    num = num*10 + c -'0';
                    i++;
                    c = s.charAt(i);
                }
                digits.push(num);
            } else if (c == '[') {
                words.push(result);
                result = "";
                i++;
            } else if (Character.isAlphabetic(c)) {
                while (i < s.length() && Character.isAlphabetic(c)) {
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
