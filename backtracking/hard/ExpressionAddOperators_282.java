package backtracking.hard;

import java.util.ArrayList;
import java.util.List;

/** H
 * Given a string num that contains only digits and an integer target,
 * return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num
 * so that the resultant expression evaluates to the target value.
 * Note that operands in the returned expressions should not contain leading zeros.
 *
 * Example 1:
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 *
 * Example 2:
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 *
 * Example 3:
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 *
 * Constraints:
 *     1 <= num.length <= 10
 *     num consists of only digits.
 *     -231 <= target <= 231 - 1
 */
public class ExpressionAddOperators_282 {

    public static void main(String[] args) {
        ExpressionAddOperators_282 s = new ExpressionAddOperators_282();
        System.out.println(s.addOperators("123", 6)); //[1+2+3, 1*2*3]
        System.out.println(s.addOperators("232", 8)); //[2+3*2, 2*3+2]
        System.out.println(s.addOperators("3456237490", 9191)); //[]
    }

    // O(4^n) - time, O(n) - space
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, num, 0, target, 0, 0);

        return res;
    }

    private void dfs(List<String> res, StringBuilder sb, String num, int pos, int target, long prev, long multi) {
        if (pos == num.length()) {
            if (target == prev) {
                res.add(sb.toString());
            }
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (num.charAt(pos) == '0' && i != pos) {
                break;
            }
            long curr = Long.parseLong(num.substring(pos, i + 1));
            int len = sb.length();
            if (pos == 0) {
                dfs(res, sb.append(curr), num, i + 1, target, curr, curr);
                sb.setLength(len);
            } else {
                dfs(res, sb.append("+").append(curr), num, i + 1, target, prev + curr, curr);
                sb.setLength(len);
                dfs(res, sb.append("-").append(curr), num, i + 1, target, prev - curr, -curr);
                sb.setLength(len);
                dfs(res, sb.append("*").append(curr), num, i + 1, target, prev - multi + multi * curr, multi * curr);
                sb.setLength(len);
            }
        }
    }
}
