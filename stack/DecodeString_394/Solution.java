package stack.DecodeString_394;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 Given an encoded string, return it's decoded string.
 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 Examples:
 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class Solution {

    public String decodeString(String input) {
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            if (symbol == '[') {
                continue;
            } else if (symbol != ']') {
                stack1.push(symbol);
            } else if (symbol == ']') {
                while (!Character.isDigit(stack1.peek())) {
                    queue.add(stack1.pop());
                }
                char digit = stack1.pop();
                for (int j = 0; j < queue.size() * (digit - '0'); j++) {
                    Character polled = queue.poll();
                    stack2.push(polled);
                    queue.add(polled);
                }
                queue = new LinkedList<>();
            }
        }

        StringBuilder resultBuilder = new StringBuilder(); //
        while (!stack2.empty()) {
           resultBuilder.append(stack2.pop());
        }

        return resultBuilder.toString();
    }

    // https://leetcode.com/problems/decode-string/discuss/87534/simple-java-solution-using-stack
    public String decodeString2(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }

}
