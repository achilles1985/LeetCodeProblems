package binarySearch;

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
        System.out.println(s.smallestCommonElement2(new int[][]{
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
        System.out.println(s.smallestCommonElement2(new int[][]{
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
        System.out.println(s.smallestCommonElement2(new int[][]{
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
        System.out.println(s.smallestCommonElement2(new int[][]{
                {1,2,3,3,5},
                {2,4,5,8,10},
                {3,5,7,9,11},
                {1,3,6,7,9}
        })); //-1
    }

    // O(rows*cols) - time, O(1) - space
    // Count the number of each value and as long as it's == number of rows, return. Handle duplicates as you go.
    public int smallestCommonElement(int[][] mat) {
        int[] dp = new int[10000];
        int rows = mat.length;
        int cols = mat[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j > 0 && j < cols && mat[i][j] == mat[i][j-1]) {
                    continue;
                }
                if (++dp[mat[i][j]] == rows) {
                    return mat[i][j];
                };
            }
        }
        return -1;
    }

    // O(cols*rows*log(rows)) - time, O(1) - space
    public int smallestCommonElement2(int[][] mat) {
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
}
