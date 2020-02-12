package array;

/**M
 Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.



 Example 1:

 Input: matrix =
 [
 [0,1,1,1],
 [1,1,1,1],
 [0,1,1,1]
 ]
 Output: 15
 Explanation:
 There are 10 squares of side 1.
 There are 4 squares of side 2.
 There is  1 square of side 3.
 Total number of squares = 10 + 4 + 1 = 15.
 Example 2:

 Input: matrix =
 [
 [1,0,1],
 [1,1,0],
 [1,1,0]
 ]
 Output: 7
 Explanation:
 There are 6 squares of side 1.
 There is 1 square of side 2.
 Total number of squares = 6 + 1 = 7.


 Constraints:

 1 <= arr.length <= 300
 1 <= arr[0].length <= 300
 0 <= arr[i][j] <= 1
 */
public class CountSquareSubmatricesWithAllOnes_1277 {

    public static void main(String[] args) {
        CountSquareSubmatricesWithAllOnes_1277 s = new CountSquareSubmatricesWithAllOnes_1277();
        System.out.println(s.countSquares(new int[][]{
                {1,0,1},
                {1,1,0},
                {1,1,0}
        }));
        System.out.println(s.countSquares(new int[][]{
                {0,1,1,1},
                {1,1,1,1},
                {0,1,1,1}
        }));
    }

    public int countSquares(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int sum = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i];
            sum += dp[0][i];
        }
        for (int i = 1; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0];
            sum += dp[i][0];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                sum += dp[i][j];
            }
        }
        return sum;
    }
}
