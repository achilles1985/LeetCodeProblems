package binarySearch.SearchA2DMatrix_74;

/**
 * 74. Search a 2D Matrix
 * https://leetcode.com/problems/search-a-2d-matrix/
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 * Consider the following matrix:
 * [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 */
public class Solution {

    // Great solution, treating matrix as array, time complexity O(log(n+m)), is better than O(log n + log m)
    // https://discuss.leetcode.com/topic/3227/don-t-treat-it-as-a-2d-matrix-just-treat-it-as-a-sorted-list
    // https://discuss.leetcode.com/topic/51589/two-java-solutons-o-log-m-n-is-better-than-o-log-m-log-n - good description
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int columns = matrix[0].length;
        int size = matrix.length * matrix[0].length - 1;

        int lo = 0;
        int hi = size;
        while (lo <= hi) {
            int middle = lo + (hi - lo) / 2;
            int value = matrix[middle / columns][middle % columns];
            if (value == target) {
                return true;
            } else if (value < target) {
                lo = middle + 1;
            } else {
                hi = middle - 1;
            }
        }

        return false;
    }
}
