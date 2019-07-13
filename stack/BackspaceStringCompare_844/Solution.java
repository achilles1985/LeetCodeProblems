package stack.BackspaceStringCompare_844;

import java.util.Stack;

/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

 Example 1:
 Input: S = "ab#c", T = "ad#c"
 Output: true
 Explanation: Both S and T become "ac".

 Example 2:
 Input: S = "ab##", T = "c#d#"
 Output: true
 Explanation: Both S and T become "".

 Example 3:
 Input: S = "a##c", T = "#a#c"
 Output: true
 Explanation: Both S and T become "c".

 Example 4:
 Input: S = "a#c", T = "b"
 Output: false
 Explanation: S becomes "c" while T becomes "b".

 Note:
 1 <= S.length <= 200
 1 <= T.length <= 200
 S and T only contain lowercase letters and '#' characters.

 Follow up:
 Can you solve it in O(N) time and O(1) space?
 */
public class Solution {

    // O(max(M,N)) - time, O(N+M) - space
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();
        addToStack(s1, S);
        addToStack(s2, T);

        while (!s1.empty() && !s2.empty()) {
            if (s1.pop() != s2.pop()) {
                return false;
            }
        }

        return s1.empty() && s2.empty();
    }

    private void addToStack(Stack<Character> stack, String input) {
        for (int i = 0; i < input.length(); i++) {
            char item = input.charAt(i);
            if (item != '#') {
                stack.push(item);
            } else if (item == '#' && !stack.empty()) {
                stack.pop();
            }
        }
    }
}
