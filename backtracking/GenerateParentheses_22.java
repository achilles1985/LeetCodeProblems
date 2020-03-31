package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** M
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
        //System.out.println(s.generateParenthesisBF(2)); //[(()), ()()]

        System.out.println(s.generateParenthesis(2)); //[(()), ()()]
        System.out.println(s.generateParenthesis(3)); //[((())), (()()), (())(), ()(()), ()()()]
    }

    // O(2^(2*n)*n) - time, space
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(n, n, "", result);

        return result;
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

    // O(2^(2*n)*n) - time, space
    public List<String> generateParenthesisBF(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
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

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }
}
