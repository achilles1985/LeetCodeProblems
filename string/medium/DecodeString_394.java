package string.medium;

import java.util.*;

/**M [marked]
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
        System.out.println(s.decodeString("3[a2[c]]")); //accaccacc
        System.out.println(s.decodeString("3[a]2[bc]")); //aaabcbc
        System.out.println(s.decodeString("2[abc]3[cd]ef")); //abcabccdcdcdef
    }

    // O(maxK^countK*N) - time, maxK - max value of k, countK - count of nested k, n - is the maximum length of encoded string.
    // example: 20[a10[bc]], maxK - 20, countK - 2. There are 2 nested k values, 20 and 10. There are 2 encoded strings "a" and "bc with max length of encoded string n=2.
    public String decodeStringBF(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                List<Character> decodedString = new ArrayList<>();
                // get the encoded string
                while (stack.peek() != '[') {
                    decodedString.add(stack.pop());
                }
                // pop [ from the stack
                stack.pop();
                int base = 1;
                int k = 0;
                // get the number k
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + (stack.pop() - '0') * base;
                    base *= 10;
                }
                // decode k[decodedString], by pushing decodedString k times into stack
                while (k != 0) {
                    for (int j = decodedString.size() - 1; j >= 0; j--) {
                        stack.push(decodedString.get(j));
                    }
                    k--;
                }
            }
            // push the current character to stack
            else {
                stack.push(s.charAt(i));
            }
        }
        // get the result from stack
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);
    }

    // O(maxK*n) - time, maxK - max value of k (numbers in a string), n - string length
    // O(n+m) - n - number of letters, m - number of digits
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) { //n
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (Character.isAlphabetic(ch)) {
                currentString.append(ch);
            } else if (ch == '[') {
                // push the number k to countStack
                countStack.push(k);
                // push the currentString to stringStack
                stringStack.push(currentString);
                // reset currentString and k
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decodedString = stringStack.pop();
                int count = countStack.pop();
                // decode currentK[currentString] by appending currentString k times
                while (count-- > 0) { //maxK
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            }
        }
        return currentString.toString();
    }

}
