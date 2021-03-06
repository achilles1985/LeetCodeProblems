package bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**H+
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible
 * results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 *
 * Example 2:
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 *
 * Example 3:
 * Input: ")("
 * Output: [""]
 */
/*
    Firstly, remove one parenthesis at a time, then 2 parenthesis, and so on.
    It looks like a graph: first level contains one string, second level n-1 string (after removing only one char),
    second level n-2 strings and we should check each character for validity.
 */
public class RemoveInvalidParentheses_301 {

    public static void main(String[] args) {
        RemoveInvalidParentheses_301 s = new RemoveInvalidParentheses_301();
        System.out.println(s.removeInvalidParentheses3("()())()")); //["()()()", "(())()"]
        System.out.println(s.removeInvalidParentheses3("(a)())()")); //["(a)()()", "(a())()"]
        System.out.println(s.removeInvalidParentheses3(")(")); //[]
    }

    // O(n*2^n or n*n!) - time, 2^n - all possible strings, O(?) - space, probably n!
    public List<String> removeInvalidParentheses2(String s) {
        if (s == null || s.isEmpty()) {
            return Collections.emptyList();
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        visited.add(s);
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String str = queue.poll();
                visited.add(str);
                if (isValid(str)) { //n
                    result.add(str);
                }
                for (int i = 0; i < str.length(); i++) {
                    StringBuilder sb = new StringBuilder(str);
                    if (sb.charAt(i) != '(' && sb.charAt(i) != ')') {
                        continue;
                    }
                    sb.deleteCharAt(i);
                    String newStr = sb.toString();
                    if (!visited.contains(newStr)) {
                        queue.add(newStr);
                        visited.add(newStr);
                    }
                }
            }
            if (!result.isEmpty()) {
                return result;
            }
        }
        return Collections.emptyList();
    }

    // O(n*2^n) - time, ? - space
    // https://zxi.mytechroad.com/blog/searching/leetcode-301-remove-invalid-parentheses/
    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return Collections.emptyList();
        }
        List<String> results = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(s);
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (isValid(curr)) {
                results.add(curr);
                found = true;
            }
            if (found) {
                continue;
            }
            for (int i = 0; i < curr.length(); i++) {
                if (curr.charAt(i) != '(' && curr.charAt(i) != ')') { // do not remove letters
                    continue;
                }
                String next = curr.substring(0, i) + curr.substring(i+1);
                if (!visited.contains(next)) {
                    queue.add(next);
                    visited.add(next);
                }
            }
        }
        return results;
    }

    public List<String> removeInvalidParentheses3(String s) {
        if (s == null || s.isEmpty()) {
            return Collections.emptyList();
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        visited.add(s);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<String> temp = new ArrayList<>();
            while (size-- > 0) {
                String curr = queue.poll();
                if (isValid(curr)) {
                    temp.add(curr);
                }
                for (int i = 0; i < curr.length(); i++) {
                    char toRemove = curr.charAt(i);
                    if (toRemove != ')' && toRemove !='(') {
                        continue;
                    }
                    String start = curr.substring(0,i);
                    String end = curr.substring(i+1);
                    String newStr = start + end;
                    if (!visited.contains(newStr)) {
                        queue.add(newStr);
                        visited.add(newStr);
                    }
                }
            }
            if (!temp.isEmpty()) {
                return temp;
            }
        }
        return Collections.emptyList();
    }

    private boolean isValid(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                count++;
            } else if (str.charAt(i) == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

}
