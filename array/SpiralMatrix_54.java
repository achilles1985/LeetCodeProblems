package array;

import java.util.ArrayList;
import java.util.List;

/** M
 Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 Example 1:
 Input:
 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 Output: [1,2,3,6,9,8,7,4,5]

 Example 2:
 Input:
 [
 [1, 2, 3, 4],
 [5, 6, 7, 8],
 [9,10,11,12]
 ]
 Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix_54 {

    public static void main(String[] args) {
        SpiralMatrix_54 s = new SpiralMatrix_54();

        System.out.println(s.spiralOrder(new int[][]{{2},{3}})); //[3,2]
        System.out.println(s.spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}})); //[1,2,3,6,9,8,7,4,5]
        System.out.println(s.spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}})); //[1,2,3,4,8,12,11,10,9,5,6,7]
    }

    // O(n^2) - time, O(1) - space
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1, right = matrix[0].length - 1, left = 0, direction = 0;
        while(top <= bottom && left <= right) {
            // Left to Right
            if (direction == 0) {
                for (int i = left; i <= right; i++) {
                    list.add(matrix[top][i]);
                }
                top++;
            }
            //Top to Bottom
            if (direction == 1) {
                for (int i = top; i <= bottom; i++) {
                    list.add(matrix[i][right]);
                }
                right--;
            }
            //Right to Left
            if (direction == 2) {
                for (int i = right; i >= left; i--) {
                    list.add(matrix[bottom][i]);
                }
                bottom--;
            }
            //Bottom to Top
            if (direction == 3) {
                for (int i = bottom; i >= top; i--) {
                    list.add(matrix[i][left]);
                }
                left++;
            }
            direction = (direction + 1)%4;
        }

        return list;
    }


}
