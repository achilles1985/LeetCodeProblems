package heap.hard;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * H
 * Given an integer array, return the k-th smallest distance among all the pairs.
 * The distance of a pair (A, B) is defined as the absolute difference between A and B.
 * <p>
 * Example 1:
 * Input:
 * nums = [1,3,1]
 * k = 1
 * Output: 0
 * Explanation:
 * Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 * <p>
 * Note:
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 */
// https://leetcode.com/problems/find-k-th-smallest-pair-distance/
public class FindKthSmallestPairDistance_719 {

    public static void main(String[] args) {
        FindKthSmallestPairDistance_719 s = new FindKthSmallestPairDistance_719();
        System.out.println(s.smallestDistancePair(new int[] {1, 3, 1}, 1)); //0
        System.out.println(s.smallestDistancePairBF(new int[] {1, 3, 1}, 1)); //0
    }

    // O(n^2*log(k)) - time, O(k) - space
    public int smallestDistancePairBF(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Queue<Pair> heap = new PriorityQueue<>((p1, p2) -> p2.diff - p1.diff);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                heap.add(new Pair(nums[i], nums[j]));
                if (heap.size() > k) {
                    heap.poll();
                }
            }
        }

        return heap.peek().diff;
    }

    // O(n*log(n) + w*log(w)) - time, O(1) - space, where w - diff range from min to max
    public int smallestDistancePair(int a[], int k) {
        Arrays.sort(a);
        int low = 0;
        int high = a[a.length - 1] - a[0];
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (countPairs(a, mid) < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private int countPairs(int[] a, int mid) {
        int n = a.length, res = 0;
        for (int i = 0; i < n; ++i) {
            int j = i;
            while (j < n && a[j] - a[i] <= mid) {
                j++;
            }
            res += j - i - 1;
        }
        return res;
    }

    private static class Pair {
        int left;
        int right;
        int diff;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
            this.diff = Math.abs(left - right);
        }
    }
}
