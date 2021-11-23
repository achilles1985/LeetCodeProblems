package dynamic.medium;

/**
 * M
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * <p>
 * Example:
 * Input:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * Output: 4
 */
/*
    dp[][] = new int[rows+1][cols+1] - because matrix could be [[1]]
    Edge cases:
        a) [[1],[0]] - one row, many columns
        b) [[1], - many rows, one column
            [1]]
        c) [[1]] - one row, one column
 */
public class MaximalSquare_221 {

    public static void main(String[] args) {
        MaximalSquare_221 s = new MaximalSquare_221();
        System.out.println(s.maximalSquareBF(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}})); //4
        System.out.println(s.maximalSquareBF(new char[][]{
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'}})); //0

        System.out.println(s.maximalSquareBottomUp(new char[][]{{'1'}})); //1
        System.out.println(s.maximalSquareBottomUp(new char[][]{
                {'1', '1', '1'},
                {'1', '1', '1'},
                {'1', '1', '1'},
                {'1', '1', '1'}})); //0
        System.out.println(s.maximalSquareBottomUp(new char[][]{
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'}})); //0
        System.out.println(s.maximalSquareBottomUp(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}})); //4
    }

    // O(m*n)^2 - time,
    public int maximalSquareBF(char[][] matrix) {
        int max = 0;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    int localMax = dfs(i, j, matrix);
                    max = Math.max(max, localMax);
                }
            }
        }
        return max*max;
    }

    // O(n^2) - time, O(n^2) - space
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    int local = dfs(matrix,i,j,dp);
                    max = Math.max(max, local);
                }
            }
        }

        return max*max;
    }

    private int dfs(char[][] matrix, int i, int j, int[][] dp) {
        if (i < 0 || i == matrix.length || j < 0 || j == matrix[0].length || matrix[i][j] == '0') {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int right = dfs(matrix,i,j+1,dp);
        int down = dfs(matrix,i+1,j,dp);
        int rightDown = dfs(matrix,i+1,j+1,dp);

        int min = Math.min(rightDown,Math.min(right,down)) + 1;
        dp[i][j] = min;

        return min;
    }

    // O(n^2) - time, O(1) - space
    public int maximalSquareDP(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int max = 0;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == '1') {
                max = 1;
            }
        }
        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == '1') {
                max = 1;
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    int value = (Math.min(Math.min(matrix[i-1][j]-'0', matrix[i][j-1]-'0'), matrix[i-1][j-1]-'0') + 1);
                    matrix[i][j] = (char) (value + '0');
                    max = Math.max(max, value);
                }
            }
        }
        return max*max;
    }

    private int dfs(int i, int j, char[][] matrix) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == '0') {
            return 0;
        }
        int right = dfs(i, j + 1, matrix);
        int down = dfs(i + 1, j, matrix);
        int downRight = dfs(i + 1, j + 1, matrix);

        return Math.min(Math.min(right, down), downRight) + 1;
    }

    // O(m*n) - time, space
    public int maximalSquareBottomUp(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows+1][cols+1];
        int max = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] != '0') {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

}
