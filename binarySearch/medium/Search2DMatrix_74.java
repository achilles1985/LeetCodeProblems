package binarySearch.medium;

import java.util.Arrays;

/** M
 Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 Integers in each row are sorted from left to right.
 The first integer of each row is greater than the last integer of the previous row.

 Example 1:
 Input:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 3
 Output: true

 Example 2:
 Input:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 13
 Output: false
 */
public class Search2DMatrix_74 {

    public static void main(String[] args) {
        Search2DMatrix_74 s = new Search2DMatrix_74();
        System.out.println(s.searchMatrix2(new int[][]{{1,2,3,4,5}, {6,7,8,9,10}, {12,13,14,15,16}}, 5)); // true
        System.out.println(s.searchMatrix2(new int[][]{{1}}, 1)); // true
        System.out.println(s.searchMatrix2(new int[][]{{1}, {2}, {3}}, 4)); // false
        System.out.println(s.searchMatrix2(new int[][]{{1,2}, {}, {4}}, 4)); // true
    }

    // O(rows) - time, O(1) - space
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
            if (matrix[i] != null && matrix[i].length > 0 && matrix[i][matrix[i].length-1] >= target) {
                int index = Arrays.binarySearch(matrix[i], target);
                if (index < 0) {
                    return false;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    // O(log(rows*cols)) - time (since it's standard binary search), O(1) - space
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
