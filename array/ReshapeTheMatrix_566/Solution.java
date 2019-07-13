package array.ReshapeTheMatrix_566;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    // // time: O(n^m), space: O(n*m) with extra space for list
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int rows = nums.length;
        int columns = nums[0].length;
        if (rows*columns != r*c) {
            return nums;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                list.add(nums[i][j]);
            }
        }
        int[][] shaped = new int[r][c];
        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                shaped[i][j] = list.get(count++);
            }
        }

        return shaped;
    }

    // time: O(n^m), space: O(n*m)
    public int[][] matrixReshapeNoExtraSpace(int[][] nums, int r, int c) {
        int rows = nums.length;
        int columns = nums[0].length;
        if (rows*columns != r*c) {
            return nums;
        }

        int[][] shaped = new int[r][c];
        int newRow = 0;
        int newColumn = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                shaped[newRow][newColumn] = nums[i][j];
                newColumn++;
                if (newColumn == c) {
                    newColumn = 0;
                    newRow++;
                }
            }
        }

        return shaped;
    }

    //  time: O(n^m), space: O(n*m). Using division and modulus
    public int[][] matrixReshapeWithDivisionsAnsModules(int[][] nums, int r, int c) {
        if (nums.length == 0 || nums.length * nums[0].length != r*c) {
            return nums;
        }

        int[][] shaped = new int[r][c];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                shaped[count/c][count%c] = nums[i][j];
                count++;
            }
        }

        return shaped;
    }
}
