package backtracking;

/**M
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D
 * coordinate on this grid.
 *
 * We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their
 * assigned bike is minimized.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 *
 * Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
 *
 * Example 1:
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: 6
 * Explanation:
 * We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output
 * is 6.
 *
 * Example 2:
 * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * Output: 4
 * Explanation:
 * We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1.
 * Both assignments lead to sum of the Manhattan distances as 4.
 *
 * Note:
 *     0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
 *     All worker and bike locations are distinct.
 *     1 <= workers.length <= bikes.length <= 10
 */
/*
Brute Force Solution is a Permutation Problem's solution
https://leetcode.com/problems/campus-bikes-ii/discuss/305218/DFS-%2B-Pruning-And-DP-Solution
 */
public class CampusBikes_II_1066 {

    public static void main(String[] args) {
        CampusBikes_II_1066 s = new CampusBikes_II_1066();
        System.out.println(s.assignBikes(new int[][]{{0,0},{2,1},{3,2}}, new int[][]{{1,2},{3,3},{4,4}})); //6
    }

    // O(n*m!) - time, n - number of workers, m - number of bikes
    public int assignBikes(int[][] workers, int[][] bikes) {
        MinValue min = new MinValue();
        dfs(0, workers, bikes, new boolean[bikes.length], 0, min);

        return min.value;
    }

    private void dfs(int workerIdx, int[][] workers, int[][] bikes, boolean[] used, int distance, MinValue min) {
        if (workerIdx == workers.length) {
            min.value = Math.min(min.value, distance);
            return;
        }
        // optimization
        if (distance > min.value) {
            return;
        }
        for (int i = 0; i < bikes.length; i++) {
            if (!used[i]) {
                used[i] = true;
                dfs(workerIdx+1, workers, bikes, used, distance + getDistance(workers[workerIdx], bikes[i]), min);
                used[i] = false;
            }
        }
    }

    private int getDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    private static class MinValue {
        int value = Integer.MAX_VALUE;
    }
}
