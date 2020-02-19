package binarySearch;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/** H
 On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.
 Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
 Return the smallest possible value of D.

 1.stations.length will be an integer in range [10, 2000].
 2.stations[i] will be an integer in range [0, 10^8].
 3.K will be an integer in range [1, 10^6].
 4.Answers within 10^-6 of the true value will be accepted as correct.

 Example
 Example 1:
 Input：stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]，K = 9
 Output：0.50
 Explanation：The distance between adjacent gas stations is 0.50

 Example 2:
 Input：stations = [3,6,12,19,33,44,67,72,89,95]，K = 2
 Output：14.00
 Explanation：construction of gas stations at 86 locations
 */
public class MinimizeMaxDistanceToGasStation_774 {

    public static void main(String[] args) {
        MinimizeMaxDistanceToGasStation_774 s = new MinimizeMaxDistanceToGasStation_774();
        System.out.println(s.minmaxGasDist(new int[]{1,2,3,4,5,6,7,8,9,10}, 9)); // 0.50
        System.out.println(s.minmaxGasDist(new int[]{3,6,12,19,33,44,67,72,89,95}, 2)); // 14.00
    }

    // Create max heap of distances, take max distance and insert a new station by dividing the distance by halve. O(n*log(k)) - time, O(n) - space
    public double minmaxGasDist2(int[] stations, int K) {
        Queue<Double> maxHeap = new PriorityQueue<>((a,b) -> (double)b < (double)a ? -1 : 1);
        for (int i = 1; i < stations.length; i++) {
            maxHeap.add((double)stations[i] - stations[i-1]);
        }
        for (int i = 0; i < K; i++) {
            double polled = maxHeap.poll();
            maxHeap.add(polled/2);
        }
        return maxHeap.poll();
    }

    // O(n*log(n[n.length-1]-n[0])) - time, O(1) - space
    public double minmaxGasDist(int[] stations, int K) {
        double left = 0;
        double right = stations[stations.length-1] - stations[0];
        double eps = Math.pow(10,-6);
        double mid = right;
        while ((right-left) >= eps) {
            mid = left + (right-left)/2;
            if (possible(stations, K, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return mid;
    }

    private boolean possible(int[] stations, int k, double mid) {
        int count = 0;
        for (int i = 1; i < stations.length; i++) {
            count += (stations[i]-stations[i-1])/mid;
            if (count > k) {
                return false;
            }
        }
        return true;
    }
}
