package array;

import utils.SolutionUtils;

/** M
 You are given an n x n 2D matrix representing an image.
 Rotate the image by 90 degrees (clockwise).

 Note:
 You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 DO NOT allocate another 2D matrix and do the rotation.

 Example 1:
 Given input matrix =
 [
 [1,2,3],
 [4,5,6],
 [7,8,9]
 ],
 rotate the input matrix in-place such that it becomes:
 [
 [7,4,1],
 [8,5,2],
 [9,6,3]
 ]

 Example 2:
 Given input matrix =
 [
 [ 5, 1, 9,11],
 [ 2, 4, 8,10],
 [13, 3, 6, 7],
 [15,14,12,16]
 ],
 rotate the input matrix in-place such that it becomes:
 [
 [15,13, 2, 5],
 [14, 3, 4, 1],
 [12, 6, 8, 9],
 [16, 7,10,11]
 ]
 */
public class RotateImage_48 {

    public static void main(String[] args) {
        RotateImage_48 s = new RotateImage_48();

        s.rotate(new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}});
        s.rotate(new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}});
    }

    // O(n^2) - time, O(1) - space
    public void rotate(int[][] matrix) {
        int rows = matrix.length-1;
        int cols = matrix[0].length-1;
        for (int i = 0; i <= rows/2; i++) {
            for (int j = i; j < cols - i; j++) {
                int temp1 = matrix[i][j];
                int temp2 = matrix[j][cols-i];
                int temp3 = matrix[rows-i][cols-j];
                int temp4 = matrix[rows-j][i];
                matrix[i][j] = temp4;
                matrix[j][cols-i] = temp1;
                matrix[rows-i][cols-j] = temp2;
                matrix[rows-j][i] = temp3;
            }
        }
        SolutionUtils.print(matrix);
    }
}
