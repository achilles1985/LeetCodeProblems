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
        System.out.println(s.generateParenthesis(2));
        System.out.println(s.generateParenthesis(3));
    }

    // O(exponential) - time, O(n) - space
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
        if (leftParentheseCount < rightParentheseCount) {
            dfs(leftParentheseCount, rightParentheseCount-1, temp + ")", result);
        }
    }
}
