package dynamic;

/** E
 *  On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of
 * the floor, and you can either start from the step with index 0, or the step with index 1.
 *
 * Example 1:
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 *
 * Example 2:
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 *
 * Note:
 *     cost will have a length in the range [2, 1000].
 *     Every cost[i] will be an integer in the range [0, 999].
 */
public class MinCostClimbingStairs_746 {

    public static void main(String[] args) {
        MinCostClimbingStairs_746 s = new MinCostClimbingStairs_746();
        System.out.println(s.minCostClimbingStairs(new int[]{10,15,20})); //15
        System.out.println(s.minCostClimbingStairsDynamic(new int[]{10,15,20})); //15
        System.out.println(s.minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1})); //6
        System.out.println(s.minCostClimbingStairsDynamic(new int[]{1,100,1,1,1,100,1,1,100,1})); //6
    }

    // O(2^n) - time, O(n) - space
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        int one = minCostClimbingStairsHelper(cost, 0);
        int two = minCostClimbingStairsHelper(cost, 1);
        return Math.min(one, two);
    }

    // O(n) - time, O(1) - space
    public int minCostClimbingStairsDynamic(int[] cost) {
        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.min(cost[i-1], cost[i-2]);
        }
        return Math.min(cost[cost.length-1], cost[cost.length-2]);
    }

    private int minCostClimbingStairsHelper(int[] cost, int idx) {
        if (idx == cost.length-1 || idx == cost.length-2) {
            return cost[idx];
        }
        int one = minCostClimbingStairsHelper(cost, idx+1) + cost[idx];
        int two = minCostClimbingStairsHelper(cost, idx+2) + cost[idx];

        return Math.min(one, two);
    }
}
