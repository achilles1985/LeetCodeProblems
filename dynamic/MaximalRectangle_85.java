package dynamic;

/** H
 Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 Example:
 Input:
 [
 ["1","0","1","0","0"],
 ["1","0","1","1","1"],
 ["1","1","1","1","1"],
 ["1","0","0","1","0"]
 ]
 Output: 6
 */
public class MaximalRectangle_85 {

    public static void main(String[] args) {
        MaximalRectangle_85 s = new MaximalRectangle_85();
        System.out.println(s.maximalRectangle(new char[][] {{'1','0','1','0','0'}, {'1','0','1','1','1'}, {'1','1','1','1','1'}, {'1','0','0','1','0'}})); //6
    }

    // https://leetcode.com/problems/maximal-rectangle/discuss/327682/Java-faster-than-63-less-than-99
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;
        int[] count = new int[columns];
        int result = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int num = Character.getNumericValue(matrix[row][column]);
                count[column] = count[column] * num + num;
            }

            for (int column = 0; column < columns; column++) {
                int minCount = count[column];
                for (int index = column; index < columns && count[index] > 0; index++) {
                    minCount = Math.min(minCount, count[index]);
                    result = Math.max(result, minCount * (index - column + 1));
                }
            }
        }

        return result;
    }
}
