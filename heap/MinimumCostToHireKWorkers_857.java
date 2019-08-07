package heap;

// https://leetcode.com/problems/minimum-cost-to-hire-k-workers/

import java.util.Arrays;
import java.util.PriorityQueue;

/** H
 There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:
 Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 Every worker in the paid group must be paid at least their minimum wage expectation.
 Return the least amount of money needed to form a paid group satisfying the above conditions.

 Example 1:
 Input: quality = [10,20,5], wage = [70,50,30], K = 2
 Output: 105.00000
 Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.

 Example 2:
 Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 Output: 30.66667
 Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.

 Note:
 1 <= K <= N <= 10000, where N = quality.length = wage.length
 1 <= quality[i] <= 10000
 1 <= wage[i] <= 10000
 Answers within 10^-5 of the correct answer will be considered correct.
 */

public class MinimumCostToHireKWorkers_857 {

    public static void main(String[] args) {
        MinimumCostToHireKWorkers_857 s = new MinimumCostToHireKWorkers_857();
        System.out.println(s.mincostToHireWorkers(new int[] {10,20,5}, new int[] {70,50,30}, 2)); // 105.0000
        System.out.println(s.mincostToHireWorkers(new int[] {3,1,10,10,1}, new int[] {4,8,2,2,7}, 3)); // 30.66667
    }

    // https://leetcode.com/problems/minimum-cost-to-hire-k-workers/solution/
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int N = quality.length;
        Worker[] workers = new Worker[N];
        for (int i = 0; i < N; ++i) {
            workers[i] = new Worker(quality[i], wage[i]);
        }
        Arrays.sort(workers);

        double ans = 1e9;
        int sumq = 0;
        PriorityQueue<Integer> pool = new PriorityQueue();
        for (Worker worker: workers) {
            pool.offer(-worker.quality);
            sumq += worker.quality;
            if (pool.size() > K) {
                sumq += pool.poll();
            }
            if (pool.size() == K) {
                ans = Math.min(ans, sumq * worker.ratio());
            }
        }

        return ans;
    }

    class Worker implements Comparable<Worker> {
        public int quality, wage;

        public Worker(int q, int w) {
            quality = q;
            wage = w;
        }

        public double ratio() {
            return (double) wage / quality;
        }

        public int compareTo(Worker other) {
            return Double.compare(ratio(), other.ratio());
        }
    }
}
