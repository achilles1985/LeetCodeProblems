package array;

import java.util.Arrays;

/**M
 * Given a matrix mat where every row is sorted in increasing order, return the smallest common element in all rows.
 * If there is no common element, return -1.
 *
 * Example 1:
 * Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
 * Output: 5
 *
 * Constraints:
 *     1 <= mat.length, mat[i].length <= 500
 *     1 <= mat[i][j] <= 10^4
 *     mat[i] is sorted in increasing order.
 */
public class FindSmallestCommonElementInAllRows_1198 {

    public static void main(String[] args) {
        FindSmallestCommonElementInAllRows_1198 s = new FindSmallestCommonElementInAllRows_1198();
        System.out.println(s.smallestCommonElement(new int[][]{
                {1,1,1,1,5},
                {2,4,5,8,10},
                {3,5,7,9,11},
                {2,3,6,7,5}
        })); //5
        System.out.println(s.smallestCommonElement(new int[][]{
                {1,2,3},
                {2,3,4},
                {2,3,5}
        })); //2
        System.out.println(s.smallestCommonElement(new int[][]{
                {1,2,3,3,5},
                {2,4,5,8,10},
                {3,5,7,9,11},
                {1,3,5,7,9}
        })); //5
        System.out.println(s.smallestCommonElement(new int[][]{
                {1,2,3,3,5},
                {2,4,5,8,10},
                {3,5,7,9,11},
                {1,3,6,7,9}
        })); //-1
    }

    // O(cols*rows*log(rows)) - time, O(1) - space
    public int smallestCommonElementBF(int[][] mat) {
        for (int i = 0; i < mat[0].length; i++) {
            boolean found = true;
            for (int j = 1; j < mat.length && found; j++) {
                found = Arrays.binarySearch(mat[j], mat[0][i]) >= 0;
            }
            if (found) {
                return mat[0][i];
            }
        }
        return -1;
    }

    // O(rows*cols) - time, O(1) - space
    // Count the number of each value row by row.
    public int smallestCommonElement(int[][] mat) {
        int count[] = new int[10001];
        int n = mat.length, m = mat[0].length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                ++count[mat[i][j]];
            }
        }
        for (int k = 1; k <= 10000; ++k) {
            if (count[k] == n) {
                return k;
            }
        }
        return -1;
    }

}
