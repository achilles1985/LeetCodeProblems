package array.medium;

import utils.SolutionUtils;

import java.util.*;

/** M
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 *
 * Example 1:
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,4,7,5,3,6,8,9]
 *
 * Example 2:
 * Input: mat = [[1,2],[3,4]]
 * Output: [1,2,3,4]
 *
 * Constraints:
 *     m == mat.length
 *     n == mat[i].length
 *     1 <= m, n <= 104
 *     1 <= m * n <= 104
 *     -105 <= mat[i][j] <= 105
 */
public class DiagonalTraverse_489 {

    public static void main(String[] args) {
        DiagonalTraverse_489 s = new DiagonalTraverse_489();
        SolutionUtils.print(s.findDiagonalOrder(new int[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        })); // [1,2,4,7,5,3,6,8,9]
    }

    // O(n*m) - time, space
    public int[] findDiagonalOrder(int[][] mat) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                map.computeIfAbsent(i+j, k -> new ArrayList<>()).add(mat[i][j]);
            }
        }
        int min = map.keySet().stream().min(Comparator.naturalOrder()).get();
        int max = map.keySet().stream().max(Comparator.naturalOrder()).get();
        List<Integer> res = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            List<Integer> temp = map.get(i);
            if (i%2 == 0) {
                Collections.reverse(temp);
            }
            res.addAll(temp);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
