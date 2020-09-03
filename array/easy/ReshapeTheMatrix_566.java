package array.easy;

import java.util.LinkedList;
import java.util.Queue;

import utils.SolutionUtils;

/** E
 In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.
 You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.
 The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.
 If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

 Example 1:
 Input:
 nums =
 [[1,2],
 [3,4]]
 r = 1, c = 4
 Output:
 [[1,2,3,4]]
 Explanation:
 The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.

 Example 2:
 Input:
 nums =
 [[1,2],
 [3,4]]
 r = 2, c = 4
 Output:
 [[1,2],
 [3,4]]
 Explanation:
 There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.

 Note:
 The height and width of the given matrix is in range [1, 100].
 The given r and c are all positive.
 */
public class ReshapeTheMatrix_566 {

    public static void main(String[] args) {
        ReshapeTheMatrix_566 s = new ReshapeTheMatrix_566();

        SolutionUtils.print(s.matrixReshape2(new int[][]{{1,2},{3,4}},1,4)); //[[1,2,3,4]]
        SolutionUtils.print(s.matrixReshape2(new int[][]{{1,2},{3,4}},2,4)); //[[1,2],[3,4]]
        SolutionUtils.print(s.matrixReshape2(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}},2,8)); //[[1,2,3,4,5,6,7,8],[9,10,11,12,13,14,15,16]]
    }

    // O(n*m) - time, O(2*n*m) - space
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null || r*c != nums.length*nums[0].length) {
            return nums;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                queue.add(nums[i][j]);
            }
        }

        int[][] reshaped = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                reshaped[i][j] = queue.poll();
            }
        }

        return reshaped;
    }

    // O(n*m) - time, O(n*m) - space
    public int[][] matrixReshape2(int[][] nums, int r, int c) {
        if (nums == null ||  r*c != nums.length*nums[0].length) {
            return nums;
        }

        int reshaped[][] = new int[r][c];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                reshaped[count/c][count%c] = nums[i][j];
                count++;
            }
        }

        return reshaped;
    }
}
