package dynamic.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * M
 * In a country popular for train travel, you have planned some train travelling one year in advance.
 * The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.
 * <p>
 * Train tickets are sold in 3 different ways:
 * <p>
 * a 1-day pass is sold for costs[0] dollars;
 * a 7-day pass is sold for costs[1] dollars;
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.
 * <p>
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 * <p>
 * Example 1:
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 * Output: 11
 * Explanation:
 * For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 * In total you spent $11 and covered all the days of your travel.
 * <p>
 * Example 2:
 * Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * Output: 17
 * Explanation:
 * For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
 * On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
 * In total you spent $17 and covered all the days of your travel.
 * <p>
 * <p>
 * Note:
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days is in strictly increasing order.
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 */
/*
For non-travel days, the cost stays the same as for the previous day.
For travel days, it's a minimum of yesterday's cost plus single-day ticket, or cost for 8 days ago plus 7-day pass,
or cost 31 days ago plus 30-day pass (similar to classic coin-change problem).
 */
public class MinimumCostForTickets_983 {

    // 2 2 2 2 2 2 8,7
    // 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20
    public static void main(String[] args) {
        MinimumCostForTickets_983 s = new MinimumCostForTickets_983();
        System.out.println(s.mincostTickets2(new int[]{1, 4, 6, 7, 8, 20}, new int[]{7, 2, 15})); //6
        System.out.println(s.mincostTickets2(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15})); //11
    }

    // O(1) - time, space
    public int mincostTickets2(int[] days, int[] costs) {
        HashSet<Integer> daySet = new HashSet<>();
        for(int i=0;i<days.length;i++){
            daySet.add(days[i]);
        }

        int dp[] = new int[366];
        dp[0] = 0;
        for(int i=1;i<366;i++){
            if(!daySet.contains(i)){
                dp[i] = dp[i-1];
                continue;
            }
            dp[i] = Math.min(dp[i-1]+costs[0],Math.min(dp[Math.max(0,i-7)]+costs[1],dp[Math.max(0,i-30)]+costs[2]));
        }

        return dp[365];
    }

    // O(1) - time, space
    public int mincostTickets(int[] days, int[] costs) {
        int dp[] = new int[366];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < days.length; i++) {
            dp[days[i]] = 1;
        }
        dp[0] = 0;
        for (int i = 1; i < 366; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1] + costs[0];
            }
            if (i >= 7) {
                dp[i] = Math.min(dp[i], dp[i - 7] + costs[1]);
            }
            if (i >= 30) {
                dp[i] = Math.min(dp[i], dp[i - 30] + costs[2]);
            }
        }
        return dp[365];
    }
}
