package dynamic.medium;

//https://leetcode.com/problems/paint-house/#/description
/** M
 There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
 Find the minimum cost to paint all houses.

 Note:
 All costs are positive integers.

 Example:
 Input: [[17,2,17],[16,16,5],[14,3,19]]
 Output: 10
 Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 Minimum cost: 2 + 5 + 3 = 10.
 */
// Just draw the matrix and you'll find the min path sum
// Or draw the recursion tree then relation is: root/dp[house][color] = cost[house][color] + min(findMin(leftSubtree), findMin(rightSubtree))
public class PainHouse_256 {

    public static void main(String[] args) {
        PainHouse_256 s = new PainHouse_256();
        System.out.println(s.minCost(new int[][] {{17,2,17}, {16,16,5}, {14,3,19}})); //10
        System.out.println(s.minCost(new int[][] {{1,2,3}, {5,2,3}, {7,2,3}})); //6
        System.out.println(s.minCost(new int[][] {{3,5,3}, {6,17,6}, {7,13,18}, {9,10,18}})); //26
        System.out.println(s.minCost(new int[][] {{1,2,3}, {3,4,5}, {2,3,1}, {1,4,5}})); //7
    }

    // O(n) - time, O(n) - space
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            min = Math.min(min, findMin(costs, 0, i, new int[costs.length][3]));
        }
        return min;
        //return Math.min(findMin(costs, 0, 2), Math.min(findMin(costs, 0, 0), findMin(costs, 0, 1)));
    }

    // O(n) - time, O(1) - space
    public int minCost2(int[][] costs) {
        if(costs==null||costs.length==0) {
            return 0;
        }
        for(int i=1; i<costs.length; i++) {
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
        }
        int size = costs.length-1;

        return Math.min(Math.min(costs[size][0], costs[size][1]), costs[size][2]);
    }

    private int findMin(int[][] costs, int i, int j, int[][] dp) {
        if (i == costs.length) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int left, right;
        if (j == 0) {
            left = findMin(costs, i+1, 1, dp);
            right = findMin(costs, i+1, 2, dp);
        } else if (j == 2) {
            left = findMin(costs, i+1, 1, dp);
            right = findMin(costs, i+1, 0, dp);
        } else {
            left = findMin(costs, i+1, 0, dp);
            right = findMin(costs, i+1, 2, dp);
        }
        dp[i][j] = costs[i][j] + Math.min(left, right);

        return dp[i][j];
    }
}
