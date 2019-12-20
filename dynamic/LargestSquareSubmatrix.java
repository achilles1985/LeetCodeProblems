package dynamic;

import java.util.HashMap;
import java.util.Map;

public class LargestSquareSubmatrix {

    public static void main(String[] args) {
        LargestSquareSubmatrix s = new LargestSquareSubmatrix();
        int[][] matrix0 = new int[][] {
                {1,0,1,0,0},
                {1,0,1,1,1},
                {1,1,1,1,1},
                {1,0,0,1,0}
        };
        int[][] matrix = new int[][] {
                {1,1,1,1,0},
                {0,1,1,1,1},
                {0,1,1,1,1},
                {1,0,1,1,1},
                {0,1,1,1,1}
        };
        int[][] matrix2 = new int[][] {
                {1,1,0},
                {0,1,1},
                {1,1,1}
        };

        int[][] matrix3 = new int[][] {
                {1,1,1,0},
                {0,1,1,1},
                {0,1,1,1},
                {1,1,1,1}
        };
        System.out.println(s.squareSubmatrixDynamicTopDown(matrix0));
        System.out.println(s.squareSubmatrixDynamicBottomUp(matrix0));
        System.out.println(s.squareSubmatrixBruteForce(matrix0));
    }

    // 3^(n*m) - since for each cell we need to check 3 ways
    public int squareSubmatrixBruteForce(int[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    int localMax = squareSubmatrixBruteForceUtils(matrix, i, j);
                    max = Math.max(max, localMax);
                }
            }
        }

        return max * max;
    }

    // O(n*m) - time and space
    public int squareSubmatrixDynamicTopDown(int[][] m) {
        int max = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 1) {
                    int localMax = squareSubmatrixDynamicTopDownUtils(m, i, j, map);
                    max = Math.max(max, localMax);
                }
            }
        }

        return max*max;
    }

    // O(n*matrix) - time and space
    public int squareSubmatrixDynamicBottomUp(int[][] matrix) {
        int[][] res = new int[matrix.length][matrix[0].length];
        // populate the first column
        for (int i = 0; i < matrix.length; i++) {
            res[i][0] = matrix[i][0];
        }
        // populate the first row
        for (int i = 0; i < matrix[0].length; i++) {
            res[0][i] = matrix[0][i];
        }

        int max = 0;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                } else {
                    int localMax = Math.min(res[i][j-1], Math.min(res[i-1][j-1], res[i-1][j])) + 1;
                    res[i][j] = localMax;
                    max = Math.max(max, localMax);
                }
            }
        }

        return max;
    }
    private int squareSubmatrixDynamicTopDownUtils(int[][] m, int i, int j, Map<String, Integer> map) {
        if (i == m.length || j == m[0].length || m[i][j] == 0) {
            return 0;
        }

        String key = i+":"+j;
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

    private int squareSubmatrixBruteForceUtils(int[][] m, int i, int j) {
        if (i == m.length || j == m[0].length || m[i][j] == 0) {
            return 0;
        }

        int m1 = squareSubmatrixBruteForceUtils(m, i, j+1); // right
        int m2 = squareSubmatrixBruteForceUtils(m, i+1, j+1); // bottom right
        int m3 = squareSubmatrixBruteForceUtils(m, i+1, j); // bottom

        int res = 1 + Math.min(m1, Math.min(m2, m3));
        return res;
    }

}
