package heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/** H
 Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.

 Example 1:
 Input:
 nums = [1,3,1]
 k = 1
 Output: 0
 Explanation:
 Here are all the pairs:
 (1,3) -> 2
 (1,1) -> 0
 (3,1) -> 2
 Then the 1st smallest distance pair is (1,1), and its distance is 0.

 Note:
 2 <= len(nums) <= 10000.
 0 <= nums[i] < 1000000.
 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 */
// https://leetcode.com/problems/find-k-th-smallest-pair-distance/
public class FindKthSmallestPairDistance_719 {

    public static void main(String[] args) {
        FindKthSmallestPairDistance_719 s = new FindKthSmallestPairDistance_719();
        System.out.println(s.smallestDistancePair2(new int[] {1,3,1}, 1)); //0
    }

    // O(n^2*log(k)) - time, O(k) - space
    public int smallestDistancePair(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Queue<Pair> heap = new PriorityQueue<>((p1, p2) -> p2.diff-p1.diff);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                heap.add(new Pair(nums[i], nums[j]));
                if (heap.size() > k) {
                    heap.poll();
                }
            }
        }

        return heap.peek().diff;
    }

    // O(n*log(n)) - time, O(1) - space, https://leetcode.com/problems/find-k-th-smallest-pair-distance/solution/
    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            int count = 0, left = 0;
            for (int right = 0; right < nums.length; ++right) {
                while (nums[right] - nums[left] > mi) {
                    left++;
                }
                count += right - left;
            }
            //count = number of pairs with distance <= mi
            if (count >= k) {
                hi = mi;
            } else {
                lo = mi + 1;
            }
        }
        return lo;
    }

    private static class Pair {
        int left;
        int right;
        int diff;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
            this.diff = Math.abs(left-right);
        }
    }
}
