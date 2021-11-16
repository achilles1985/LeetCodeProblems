package slidingWindow.medium;

import java.util.*;

/**
 * M
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that
 * the absolute difference between any two elements of this subarray is less than or equal to limit.
 * <p>
 * Example 1:
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 * <p>
 * Example 2:
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 * <p>
 * Example 3:
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 * <p>
 * Constraints:
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 0 <= limit <= 109
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit_1438 {

    public static void main(String[] args) {
        LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit_1438 s = new LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit_1438();
        System.out.println(s.longestSubarray(new int[]{1, 5, 6, 7, 8, 10, 6, 5, 6}, 4)); //5
        System.out.println(s.longestSubarray(new int[]{8, 2, 4, 7}, 4)); //2
        System.out.println(s.longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5)); //4
        System.out.println(s.longestSubarray(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0)); //3
    }

    // Intuitively, the question can be solved by sliding window, and we need two priority queues to maintain max and min values for each window.
    // When the condition is satisfied we record the result and move right end, otherwise move the left end.
    // But to remove an element in a queue takes O(n) time in Java, so the over all time complexity is O(n^2).
    // O(n^2) - time, O(n) - space
    public int longestSubarrayBF(int[] nums, int limit) {
        Queue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> min = new PriorityQueue<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < nums.length) {
            max.offer(nums[right]);
            min.offer(nums[right]);
            while (max.peek() - min.peek() > limit) {
                max.remove(nums[left]);
                min.remove(nums[left]);
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    // Actually, the previous solution just needs to be modified a little bit to become O(nlong(n)). We store the index into the priority queues with the value together.
    // When the condition is not satisfied, we simply move the left end one step after the position of min(min_index, max_index), and pop out values before left end.
    // O(n*log(n)) - time, O(n) - space
    public int longestSubarray(int[] nums, int limit) {
        PriorityQueue<int[]> max = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> min = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int left = 0, right = 0;
        int res = 0;
        while (right < nums.length) {
            max.offer(new int[]{nums[right], right});
            min.offer(new int[]{nums[right], right});
            while (max.peek()[0] - min.peek()[0] > limit) {
                left = Math.min(max.peek()[1], min.peek()[1]) + 1;
                while (max.peek()[1] < left) {
                    max.poll();
                }
                while (min.peek()[1] < left) {
                    min.poll();
                }
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    // The good thing is that we can do better. If you are familiar with questions like [239.Sliding Window Maximum] or [480.Sliding Window Median], you will quickly come up with the idea of deque.
    // This solution provide a way to maintain the max/min/median value within a window with O(1) time dynamically, which leads to an over all time complexity of O(n).
    // We have 2 dequeue, min always keeps min element at head, max always keeps max at head. The difference is calculated based on min(head)-max(head). If diff > limit, remove from head min or max to minimize diff.
    // O(n) - time, space
    public int longestSubarray2(int[] nums, int limit) {
        Deque<Integer> max = new ArrayDeque<>();
        Deque<Integer> min = new ArrayDeque<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < nums.length) {
            while (!max.isEmpty() && nums[max.peekLast()] <= nums[right]) {
                max.pollLast();
            }
            while (!min.isEmpty() && nums[min.peekLast()] >= nums[right]) {
                min.pollLast();
            }
            max.offerLast(right);
            min.offerLast(right);
            while (nums[max.peekFirst()] - nums[min.peekFirst()] > limit) {
                if (min.peekFirst() == left) {
                    min.pollFirst();
                }
                if (max.peekFirst() == left) {
                    max.pollFirst();
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
