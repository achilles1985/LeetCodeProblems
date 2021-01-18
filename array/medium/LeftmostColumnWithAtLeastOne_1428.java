package array.medium;

import java.util.ArrayList;
import java.util.List;

/** M
 A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.
 Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it.
 If such index doesn't exist, return -1.
 You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:

 BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 BinaryMatrix.dimensions() returns a list of 2 elements [rows, cols], which means the matrix is rows * cols.

 Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.
 Also, any solutions that attempt to circumvent the judge will result in disqualification.
 For custom testing purposes you're given the binary matrix mat as input in the following four examples.
 You will not have access the binary matrix directly.

 Example 1:
 Input: mat = [[0,0],[1,1]]
 Output: 0

 Example 2:
 Input: mat = [[0,0],[0,1]]
 Output: 1

 Example 3:
 Input: mat = [[0,0],[0,0]]
 Output: -1

 Example 4:
 Input: mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
 Output: 1

 Constraints:
    rows == mat.length
    cols == mat[i].length
    1 <= rows, cols <= 100
    mat[i][j] is either 0 or 1.
    mat[i] is sorted in a non-decreasing way.
 */
public class LeftmostColumnWithAtLeastOne_1428 {

    // O(rows + cols) - time, O(1) - space
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimension = binaryMatrix.dimensions();
        int rows = dimension.get(0);
        int cols = dimension.get(1);
        int row = 0, col = cols-1;
        while (row < rows && col >= 0) {
            if (binaryMatrix.get(row, col) == 1) {
                col--;
            } else {
                row++;
            }
        }
        // If we never left the last column, this is because it was all 0's.
        return (col == cols - 1) ? -1 : col + 1;
    }

    // O(rows*log(cols)) - time, O(1) - space
    public int leftMostColumnWithOneBF(BinaryMatrix binaryMatrix) {
        List<Integer> dimention = binaryMatrix.dimensions();
        int rows = dimention.get(0);
        int cols = dimention.get(1);
        int min = 101;
        for (int row = 0; row < rows; row++) {
            int idx = leftMostOne(row, cols, binaryMatrix);
            if (idx >= 0) {
                min = Math.min(min, idx);
            }
        }
        return min == 101 ? -1 : min;
    }

    private int leftMostOne(int row, int cols, BinaryMatrix matrix) {
        int left = 0, right = cols-1;
        if (matrix.get(row, right) == 0) {
            return -1;
        }
        while (left < right) {
            int mid = left + (right - left)/2;
            if (matrix.get(row, mid) == 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    private class BinaryMatrix {
        public int get(int row, int col) {
            return 0;
        }

        public List<Integer> dimensions() {
            return new ArrayList<>();
        }
    }
}
