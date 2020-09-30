package dynamic.medium;

import java.util.HashMap;
import java.util.Map;

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
public class MaximalSquare_221 {

    public static void main(String[] args) {
        MaximalSquare_221 s = new MaximalSquare_221();
        System.out.println(s.maximalSquareBruteForce(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}})); //4
        System.out.println(s.maximalSquareBruteForce(new char[][]{
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'},
                {'0', '0', '0'}})); //0

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
    public int maximalSquareBruteForce(char[][] matrix) {
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

    private int dfs(int i, int j, char[][] matrix) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == '0') {
            return 0;
        }
        int right = dfs(i, j + 1, matrix);
        int down = dfs(i + 1, j, matrix);
        int downRight = dfs(i + 1, j + 1, matrix);

        return Math.min(Math.min(right, down), downRight) + 1;
    }

    public int maximalSquareBottomUp(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i] - '0';
            max = Math.max(max, dp[0][i]);
        }
        for (int i = 0; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0] - '0';
            max = Math.max(max, dp[i][0]);
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] != '0') {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

    public int maximalSquareTopDown(char[][] matrix) {
        int max = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    int localMax = squareSubmatrixDynamicTopDownUtils(matrix, i, j, map);
                    max = Math.max(max, localMax);
                }
            }
        }

        return max * max;
    }

    private int squareSubmatrixDynamicTopDownUtils(char[][] m, int i, int j, Map<String, Integer> map) {
        if (i == m.length || j == m[0].length || m[i][j] == '0') {
            return 0;
        }

        String key = i + ":" + j;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int m1 = squareSubmatrixDynamicTopDownUtils(m, i, j + 1, map);
        int m2 = squareSubmatrixDynamicTopDownUtils(m, i + 1, j + 1, map);
        int m3 = squareSubmatrixDynamicTopDownUtils(m, i + 1, j, map);
        int min = 1 + Math.min(Math.min(m1, m2), m3);

        map.put(key, min);

        return min;
    }
}
