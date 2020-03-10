package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/** H
 Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once.
 You must make sure your result is the smallest in lexicographical order among all possible results.

 Example 1:
 Input: "bcabc"
 Output: "abc"

 Example 2:
 Input: "cbacdcbc"
 Output: "acdb"
 */
/*
    Maintain ascending ordered string of unique characters. The character can be removed if the same one exists further in the string.
 */
public class RemoveDuplicateLetters_316 {

    public static void main(String[] args) {
        RemoveDuplicateLetters_316 s = new RemoveDuplicateLetters_316();

        System.out.println(s.removeDuplicateLetters("bcabc")); //abc
        System.out.println(s.removeDuplicateLetters("cbacdcbc")); //acdb

        System.out.println(s.removeDuplicateConsequitiveLetters("aabbbcdeeeffghaab")); //abcdefghab
    }

    // O(n) - time, O(n) - space
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> charToLastIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            charToLastIndex.put(s.charAt(i), i);
        }
        Set<Character> seen = new HashSet<>();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!seen.contains(c)) {
                while (!stack.isEmpty() && c < stack.peek() && charToLastIndex.get(stack.peek()) > i) {
                    seen.remove(stack.pop());
                }
                stack.push(c);
                seen.add(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c: stack) {
            sb.append(c);
        }

        return sb.toString();
    }

    // O(n) - time, space
    public String removeDuplicateConsequitiveLetters(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
                continue;
            }
            if (!stack.isEmpty() & stack.peek() != s.charAt(i)) {
                stack.push(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c: stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
