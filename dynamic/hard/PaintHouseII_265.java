package dynamic.hard;

/** H
 * There are a row of n houses, each house can be painted with one of the k colors.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by an n x k cost matrix costs.
 * For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
 * Return the minimum cost to paint all houses.
 *
 * Example 1:
 * Input: costs = [[1,5,3],[2,9,4]]
 * Output: 5
 * Explanation:
 * Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
 * Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
 *
 * Example 2:
 * Input: costs = [[1,3],[2,4]]
 * Output: 5
 *
 * Constraints:
 *     costs.length == n
 *     costs[i].length == k
 *     1 <= n <= 100
 *     2 <= k <= 20
 *     1 <= costs[i][j] <= 20
 */
// Just draw the matrix and you'll find the min path sum
public class PaintHouseII_265 {

    public static void main(String[] args) {
        PaintHouseII_265 s = new PaintHouseII_265();
        System.out.println(s.minCostII(new int[][] {{1,2,3}, {5,2,3}, {7,2,3}})); //6
        System.out.println(s.minCostII(new int[][] {{1,3}, {2,4}})); //5
    }

    // O(n*k^2) - time, O(1) - space, if it's ok to modify existing matrix.
    public int minCostII(int[][] costs) {
        int rows = costs.length, cols = costs[0].length;
        if(rows == 0 || cols == 0) {
            return 0;
        }
        for(int i = 1; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                costs[i][j] = costs[i][j] + findMinInOneRow(i - 1, j, costs);
            }
        }

        return findMinInOneRow(rows - 1, -1, costs);
    }

    private int findMinInOneRow(int i, int j, int[][] matrix) {
        int min = Integer.MAX_VALUE;
        for(int k = 0; k < matrix[0].length; k++) {
            if(k == j) {
                continue;
            }
            min = Math.min(min, matrix[i][k]);
        }
        return min;
    }
}
