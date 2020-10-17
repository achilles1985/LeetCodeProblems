package array.medium;

import java.util.ArrayList;
import java.util.List;

/** M
 * Given the following details of a matrix with n columns and 2 rows :
 *
 * The matrix is a binary matrix, which means each element in the matrix can be 0 or 1.
 * The sum of elements of the 0-th(upper) row is given as upper.
 * The sum of elements of the 1-st(lower) row is given as lower.
 * The sum of elements in the i-th column(0-indexed) is colsum[i], where colsum is given as an integer array with length n.
 * Your task is to reconstruct the matrix with upper, lower and colsum.
 *
 * Return it as a 2-D integer array.
 * If there are more than one valid solution, any of them will be accepted.
 * If no valid solution exists, return an empty 2-D array.
 *
 * Example 1:
 * Input: upper = 2, lower = 1, colsum = [1,1,1]
 * Output: [[1,1,0],[0,0,1]]
 * Explanation: [[1,0,1],[0,1,0]], and [[0,1,1],[1,0,0]] are also correct answers.
 *
 *  Example 2:
 * Input: upper = 2, lower = 3, colsum = [2,2,1,1]
 * Output: []
 * Example 3:
 * Input: upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
 * Output: [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
 *
 *
 * Constraints:
 * 1 <= colsum.length <= 10^5
 * 0 <= upper, lower <= colsum.length
 * 0 <= colsum[i] <= 2
 */
public class Reconstruct2RowBinaryMatrix_1253 {

    public static void main(String[] args) {
        Reconstruct2RowBinaryMatrix_1253 s = new Reconstruct2RowBinaryMatrix_1253();
        System.out.println(s.reconstructMatrix(2,1, new int[]{1,1,1}));
    }

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<Integer> u = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        for (int col: colsum) {
            if (col == 0) {
                u.add(0);
                l.add(0);
            } else if (col == 2) {
                u.add(1);
                l.add(1);
                upper--;
                lower--;
            } else if (lower == 0 && upper == 0) {
                return new ArrayList<>();
            } else {
                if (upper > lower) {
                    u.add(1);
                    l.add(0);
                    upper--;
                } else {
                    u.add(0);
                    l.add(1);
                    lower--;
                }
            }
        }
        if (upper != 0 || lower != 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        result.add(u);
        result.add(l);

        return result;
    }
}
