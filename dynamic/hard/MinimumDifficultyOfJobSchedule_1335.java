package dynamic.hard;

/** H
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
 * You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 * Example 1:
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 *
 * Example 2:
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 *
 * Example 3:
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 *
 * Constraints:
 *     1 <= jobDifficulty.length <= 300
 *     0 <= jobDifficulty[i] <= 1000
 *     1 <= d <= 10
 */
public class MinimumDifficultyOfJobSchedule_1335 {

    public static void main(String[] args) {
        MinimumDifficultyOfJobSchedule_1335 s = new MinimumDifficultyOfJobSchedule_1335();
        System.out.println(s.minDifficulty(new int[]{6,5,4,3,2,1}, 2)); //7
        System.out.println(s.minDifficulty(new int[]{9,9,9}, 4)); //-1
        System.out.println(s.minDifficulty(new int[]{1,1,1}, 3)); //3
    }

    // O(m*n) - time, space, m - job difficulties, n - days
    public int minDifficulty(int[] jobDifficulty, int d) {
        if (d > jobDifficulty.length) {
            return -1;
        }

        int[] hardestRemaining = new int[jobDifficulty.length];
        int max = 0;
        for (int i = jobDifficulty.length-1; i >= 0; i--) {
            hardestRemaining[i] = Math.max(max, jobDifficulty[i]);
        }

        int[][] dp = new int[jobDifficulty.length][d+1];

        return helper(jobDifficulty, d, 0, 1, hardestRemaining, dp);
    }

    private int helper(int[] jobDifficulty, int days, int i, int d, int[] hardestRemaining, int[][] dp) {
        int n = jobDifficulty.length;
        if (d == days) {
            return hardestRemaining[i];
        }
        if (dp[i][d] != 0) {
            return dp[i][d];
        }

        int hardest = 0, best = Integer.MAX_VALUE;
        for (int j = i; j < n - (days-d); j++) {
            hardest = Math.max(jobDifficulty[j], hardest);
            best = Math.min(best, hardest + helper(jobDifficulty, days, j+1, d+1, hardestRemaining, dp));
        }
        dp[i][d] = best;

        return dp[i][d];
    }
}
