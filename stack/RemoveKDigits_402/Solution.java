package stack.RemoveKDigits_402;

import java.util.Stack;

public class Solution {

    public String removeKdigits(String num, int k) {
        if (k == num.length()) return "0";
        Stack<Character> stack = new Stack<>();
        int i = 0;
        for (; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!stack.isEmpty() && c < stack.peek() && k > 0) {
                k--;
                stack.pop();
            }
            if (c != '0') {
                stack.push(c);
            }
            if (k == 0) {
                break;
            }
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        res = k == 0 ? res + num.substring(i + 1) : res.substring(0, res.length() - k);
        if (res.length() == 0) {
            return "0";
        }

        return res;
    }
}
