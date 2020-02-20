package binarySearch;

/**M
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its
 * sum is no larger than k.
 *
 * Example:
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 * Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
 *              and 2 is the max number no larger than k (k = 2).
 *
 * Note:
 *     The rectangle inside the matrix must have an area > 0.
 *     What if the number of rows is much larger than the number of columns?
 */
public class MaxSumOfRectangleNoLargerThanK_363 {

    public static void main(String[] args) {
        MaxSumOfRectangleNoLargerThanK_363 s = new MaxSumOfRectangleNoLargerThanK_363();
        System.out.println(s.maxSumSubmatrix(new int[][]{
                {1,0,-1},
                {0,-1,3}
        }, 2));
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = i; j < cols; j++) {
                int sum = 0;
                for (int l = j; l < rows; l++) {
                    for (int m = l; m < cols; m++) {
                        sum += matrix[l][m];
                        max = Math.max(max, sum);
                    }
                }
            }
        }
        return max;
    }
}
