package array.medium;

import java.util.ArrayList;
import java.util.List;

/** M
 * https://leetcode.com/problems/pascals-triangle/
 * <p>
 * Given numRows, generate the first numRows of Pascal's triangle.
 * For example, given numRows = 5, Return:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * https://en.wikipedia.org/wiki/Pascal's_triangle
 * https://discuss.leetcode.com/topic/5128/solution-in-java
 * https://discuss.leetcode.com/topic/6805/my-concise-solution-in-java
 */
public class PascalsTriangle_118 {

    public static void main(String[] args) {
        PascalsTriangle_118 s = new PascalsTriangle_118();
        System.out.println(s.generate(5));
    }

    // O(n^2) - time, O(1) - space
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i ; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(rows.get(i-1).get(j-1) + rows.get(i-1).get(j));
                }
            }
            rows.add(row);
        }

        return rows;
    }
}
