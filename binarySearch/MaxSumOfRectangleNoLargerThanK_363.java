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
        System.out.println(s.maxSumSubmatrix(new int[][]{{1,2,3},{4,5,6},{7,8,9}}, 9)); //9
        System.out.println(s.maxSumSubmatrix(new int[][]{{1,0,-1},{0,-1,3}}, 2)); //2
        System.out.println(s.maxSumSubmatrix(new int[][]{{1}}, 2)); //1
        System.out.println(s.maxSumSubmatrix(new int[][]{{1},{2},{3},{4}}, 2)); //2
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {
        // 1. Find sum of all rectungles having (0,0) top-left coordinate
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int rows = matrix.length, cols = matrix[0].length;
        int[][] areas = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int area = matrix[r][c];
                if (r-1 >= 0)
                    area += areas[r-1][c];
                if (c-1 >= 0)
                    area += areas[r][c-1];
                if (r-1 >= 0 && c-1 >= 0)
                    area -= areas[r-1][c-1];
                areas[r][c] = area;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int r1 = 0; r1 < rows; r1++) {
            for (int c1 = 0; c1 < cols; c1++) {
                for (int r2 = r1; r2 < rows; r2++) {
                    for (int c2 = c1; c2 < cols; c2++) {
                        int area = areas[r2][c2];
                        if (r1-1 >= 0)
                            area -= areas[r1-1][c2];
                        if (c1-1 >= 0)
                            area -= areas[r2][c1-1];
                        if (r1-1 >= 0 && c1 -1 >= 0)
                            area += areas[r1-1][c1-1];
                        if (area <= k)
                            max = Math.max(max, area);
                    }
                }
            }
        }
        return max;
    }
}
