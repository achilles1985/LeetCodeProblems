package dynamic.medium;

/**M
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner
 * (row1, col1) and lower right corner (row2, col2).
 *
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which
 * contains sum = 8.
 *
 * Example:
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
/*
    Tips: useful tecnic while creating a dp matrix to add one extra row and column.
    The value in current position is calculated as value on the left + value on the right - top-left value.
 */
public class RangeSumQuery2DImmutable_304 {

    private int[][] dp;

    // O(n*m) - time, space
    public RangeSumQuery2DImmutable_304(int[][] matrix) {
        //1.
        int rows = matrix.length;
        int cols = matrix[0].length;
        dp = new int[rows +1][cols +1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dp[i+1][j+1] = matrix[i][j] + dp[i][j+1] + dp[i+1][j] - dp[i][j];
            }
        }

        //2. Populate dp without extra row and column
/*        dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dp[i][j] = matrix[i][j];
                if (i-1 >= 0) {
                    dp[i][j] += dp[i-1][j]; // add left value
                } if (j-1 >= 0) {
                    dp[i][j] += dp[i][j-1]; // add up value
                } if (i-1>= 0 && j-1 >= 0) {
                    dp[i][j] -= dp[i-1][j-1]; // substruct top-left value
                }
            }
        }*/
    }

    // O(1)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2+1][col2+1] - dp[row2+1][col1] - dp[row1][col2+1] + dp[row1][col1];
        //return dp[row2][col2] - dp[row2][col1-1] - dp[row1-1][col2] + dp[row1-1][col1-1]; // 2.
    }

    public static void main(String[] args) {
        RangeSumQuery2DImmutable_304 s = new RangeSumQuery2DImmutable_304(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        });
        System.out.println(s.sumRegion(2,1,4,3));//8
        System.out.println(s.sumRegion(1,1,2,2));//11
        System.out.println(s.sumRegion(1,2,2,4));//12
    }
}
