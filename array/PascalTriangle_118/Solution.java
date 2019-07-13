package array.PascalTriangle_118;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergii on 25.07.2016.
 * 118. Pascal's Triangle
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

public class Solution {

    // NOT working solution
    public List<List<Integer>> generate(int rows) {
        int k = 1;
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>(k);
            for (int j = 0; j < k; j++) {
                if (j == 0 || j == k - 1) {
                    row.add(1);
                    results.add(row);
                    k++;
                    continue;
                }
                List<Integer> upper = results.get(i);
                int size = upper.size();
                int index = 0;
                while (size > 0) {
                    int left = upper.get(index);
                    int right = 0;
                    if (--size > 0) {
                        right = upper.get(index + 1);
                    }
                    row.add(left + right);
                }
            }
        }
        return results;
    }

    //    GOOD SOLUTION
    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> allrows = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
            allrows.add(new ArrayList<>(row));
        }
        return allrows;

    }
}
