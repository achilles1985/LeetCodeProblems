package binarySearch;

/** M
 * 240. Search a 2D Matrix II
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * For example,
 * Consider the following matrix:
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * Given target = 20, return false.
 */
public class SearchA2DMatrix2 {

    public static void main(String[] args) {
        SearchA2DMatrix2 s = new SearchA2DMatrix2();
        System.out.println(s.searchMatrix( new int[][]{{3, 3, 8, 13,13,18},
                        {4, 5, 11,13,18,20},
                        {9, 9, 14,15,23,23},
                        {13,18,22,22,25,27},
                        {18,22,23,28,30,33},
                        {21,25,28,30,35,35},
                        {24,25,33,36,37,40}}, 21));
    }

    // O(rows+cols) - time, O(1) - space
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length - 1;
        int columns = matrix[0].length - 1;

        int r = 0;
        int c = columns;
        while(c >= 0 && r <= rows) {
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] < target) {
                r++;
            } else {
                c--;
            }
        }
        return false;
    }
}
