package stack.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 * <p>
 * Example 1:
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * <p>
 * Example 2:
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * <p>
 * Example 3:
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * <p>
 * Example 4:
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * <p>
 * Note:
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 * <p>
 * Follow up:
 * Can you solve it in O(N) time and O(1) space?
 */
public class BackspaceStringCompare_844 {

    public static void main(String[] args) {
        BackspaceStringCompare_844 s = new BackspaceStringCompare_844();
        System.out.println(s.backspaceCompare2("abcd", "bbcd")); //false
        System.out.println(s.backspaceCompare2("y#fo##f", "y#f#o##f")); //true

        System.out.println("ab#c == ad#c " + s.backspaceCompare("ab#c", "ad#c"));//true
        System.out.println("ab## == c#d# " + s.backspaceCompare("ab##", "c#d#"));//true
        System.out.println("a##c == #a#c " + s.backspaceCompare("a##c", "#a#c"));//true
        System.out.println("a#c == b " + s.backspaceCompare("a#c", "b")); //false
    }

    public boolean backspaceCompare2(String s, String t) {
        Deque<Character> stack1 = populateStack(s);
        Deque<Character> stack2 = populateStack(t);
        if (stack1.size() != stack2.size()) {
            return false;
        }
        int size = stack1.size();
        for (int i = 0; i < size; i++) {
            if (!stack1.pop().equals(stack2.pop())) {
                return false;
            }
        }

        return true;
    }

    private Deque<Character> populateStack(String s) {
        Deque<Character> stack1 = new ArrayDeque<>();
        for (char c: s.toCharArray()) {
            if (!stack1.isEmpty() && c == '#') {
                stack1.pop();
            } else if (c != '#'){
                stack1.push(c);
            }
        }
        return stack1;
    }


    // O(M+N) - time, O(N+M) - space
    public boolean backspaceCompareBF(String S, String T) {
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

    // O(n+m) - time, O(1) - space
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
            while (i >= 0) { // Find position of next possible char in build(S)
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            while (j >= 0) { // Find position of next possible char in build(T)
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            // If two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) {
                return false;
            }
            // If expecting to compare char vs nothing
            if ((i >= 0) != (j >= 0)) {
                return false;
            }
            i--;
            j--;
        }
        return true;
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
