package backtracking.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** M [marked]
 *  Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 For example, given n = 3, a solution set is:

 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]
 */
public class GenerateParentheses_22 {

    public static void main(String[] args) {
        GenerateParentheses_22 s = new GenerateParentheses_22();
        System.out.println(s.generateParenthesisBF2(3)); //[(()), ()()]
        System.out.println(s.generateParenthesisBF(3)); //[(()), ()()]

        System.out.println(s.generateParenthesis2(3)); //[(()), ()()]
        System.out.println(s.generateParenthesis(2)); //[(()), ()()]
        System.out.println(s.generateParenthesis(3)); //[((())), (()()), (())(), ()(()), ()()()]
    }

    // O(2^(2*n)*n) - time, space
    // Generate all possible choices
    public List<String> generateParenthesisBF2(int n) {
        List<String> result = new ArrayList<>();
        dfs3(n, result, "", 0);

        return result;
    }

    // O(2^(2*n)*n) - time, space (generate all sequences and validate every one).
    // At each step we decide if to add '(' or ')' to the result till str.length == 2*n
    public List<String> generateParenthesisBF(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    // less then O(2^(2*n)*n) - time, space
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(n, n, "", result);

        return result;
    }

    // Instead of adding '(' or ')' every time as in Approach 1, let's only add them when we know it will remain a valid sequence.
    public List<String> generateParenthesis2(int n) {
        if (n < 1) {
            return Collections.emptyList();
        }
        List<String> res = new ArrayList<>();
        dfs2(n, 0, 0, "", res);

        return res;
    }

    private void dfs(int leftParentheseCount, int rightParentheseCount, String temp, List<String> result) {
        if (leftParentheseCount == 0 && rightParentheseCount == 0) {
            result.add(temp);
            return;
        }

        if (leftParentheseCount > 0) {
            dfs(leftParentheseCount-1, rightParentheseCount, temp + "(", result);
        }
        if (rightParentheseCount > leftParentheseCount) {
            dfs(leftParentheseCount, rightParentheseCount-1, temp + ")", result);
        }
    }

    private void dfs2(int n, int open, int close, String str, List<String> res) {
        if (open + close == 2*n) {
            res.add(str);
            return;
        }
        if (open < n) {
            dfs2(n, open+1, close, str + "(", res);
        }
        if (close < open) {
            dfs2(n, open, close+1, str + ")", res);
        }
    }

    private void dfs3(int n, List<String> result, String str, int count) {
        if (count == 2*n) {
            if (valid(str.toCharArray())) {
                result.add(str);
            }
            return;
        }
        dfs3(n, result, str + '(', count + 1);
        dfs3(n, result, str + ')', count + 1);
    }

    private void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    private boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') {
                balance++;
            }
            else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return (balance == 0);
    }
}
