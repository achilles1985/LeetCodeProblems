package dynamic.medium;

/** M
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 * A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right.
 * Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 *
 * Example 1:
 * Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * Output: 13
 * Explanation: There are two falling paths with a minimum sum as shown.
 *
 * Example 2:
 * Input: matrix = [[-19,57],[-40,-5]]
 * Output: -59
 * Explanation: The falling path with a minimum sum is shown.
 *
 * Constraints:
 *     n == matrix.length == matrix[i].length
 *     1 <= n <= 100
 *     -100 <= matrix[i][j] <= 100
 */
public class MinimumFallingPathSum_931 {

    public static void main(String[] args) {
        MinimumFallingPathSum_931 s = new MinimumFallingPathSum_931();
        System.out.println(s.minFallingPathSum(new int[][]{{2,1,3},{6,5,4},{7,8,9}})); //13
    }

    public int minFallingPathSum(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        for (int j = 0; j < cols; j++) {
            dp[0][j] = matrix[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j == 0) {
                    dp[i][j] = matrix[i][j] + Math.min(dp[i-1][j], dp[i-1][j+1]);
                } else if (j == cols-1) {
                    dp[i][j] = matrix[i][j] + Math.min(dp[i-1][j-1], dp[i-1][j]);
                }  else {
                    dp[i][j] = matrix[i][j] + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i-1][j+1]));
                }

            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < cols; i++) {
            min = Math.min(dp[rows-1][i], min);
        }

        return min;
    }
}
