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
        System.out.println(s.searchMatrix(new int[][]{{1,3,5,7}, {10,11,16,20}, {23,30,34,60}}, 3)); // true
        System.out.println(s.searchMatrix(new int[][]{{1,2,3,4,5}, {6,7,8,9,10}, {12,13,14,15,16}}, 5)); // true

        System.out.println(s.searchMatrix2(new int[][]{{1}}, 1)); // true
        System.out.println(s.searchMatrix2(new int[][]{{1}, {2}, {3}}, 4)); // false
        System.out.println(s.searchMatrix2(new int[][]{{1,2}, {}, {4}}, 4)); // true
    }

    // O(rows+cols) - time, O(1) - space
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0, col = cols-1;
        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    // O(log(rows*cols)) - time (since it's standard binary search), O(1) - space
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int cols = matrix[0].length;
        int left = 0, right = matrix.length * matrix[0].length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int value = matrix[middle / cols][middle % cols];
            if (value == target) {
                return true;
            } else if (value < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return false;
    }
}
