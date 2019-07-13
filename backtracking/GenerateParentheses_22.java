package backtracking;

import java.util.ArrayList;
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
        //System.out.println(s.generateParenthesis(2));
        System.out.println(s.generateParenthesis2(2));
    }

    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesisUtils2(n, n, "", res);

        return res;
    }

    private void generateParenthesisUtils2(int leftCount, int rightCount, String str, List<String> res) {
        if (leftCount == 0 && rightCount == 0) {
            res.add(str);
            return;
        }

        if (leftCount > 0) {
            generateParenthesisUtils2(leftCount-1, rightCount, str+"(", res);
            int y = 0;
        }
        if (leftCount < rightCount) {
            generateParenthesisUtils2(leftCount, rightCount-1, str+")", res);
            int h = 0;
        }
    }























    // O(n) - space
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
