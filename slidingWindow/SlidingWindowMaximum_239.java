package slidingWindow;

import utils.SolutionUtils;

import java.util.*;

/**H
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 *
 * Example:
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
 *
 * Follow up:
 * Could you solve it in linear time?
 */
public class SlidingWindowMaximum_239 {

    public static void main(String[] args) {
        SlidingWindowMaximum_239 s = new SlidingWindowMaximum_239();
        SolutionUtils.print(s.maxSlidingWindow2(new int[]{1,3,-1,-3,5,3,6,7}, 3)); //[3,3,5,5,6,7]
        SolutionUtils.print(s.maxSlidingWindow2(new int[]{1,3,1,2,0,5}, 3)); //[3,3,2,5]
        SolutionUtils.print(s.maxSlidingWindow2(new int[]{1,3,-1,-2,-3,-4,-5,-6}, 3)); //[3,3,5,5,6,7]

        SolutionUtils.print(s.maxSlidingWindowDP(new int[]{1,3,-1,-3,5,3,6,7}, 3)); //[3,3,5,5,6,7]

        SolutionUtils.print(s.maxSlidingWindowBF(new int[]{1,3,-1,-3,5,3,6,7}, 3)); //[3,3,5,5,6,7]
        SolutionUtils.print(s.maxSlidingWindowBF(new int[]{1, -1}, 1)); //[1,-1]
        SolutionUtils.print(s.maxSlidingWindowBF(new int[]{1}, 1)); //[1]
    }

    // O(n) - time, O(n) - space
    public int[] maxSlidingWindowDP(int[] nums, int k) {
        int length = nums.length;
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        left[0] = nums[0];
        right[length-1] = nums[length-1];
        for (int i = 1; i < nums.length; i++) {
            if (i%k == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i-1], nums[i]);
            }
            int j = length - i - 1;
            if ((j + 1)%k == 0) {
                right[j] = nums[j];
            } else {
                right[j] = Math.max(right[j+1], nums[j]);
            }
        }
        int[] result = new int[length];
        for (int i = 0; i <= length - k; i++) {
            result[i] = Math.max(left[i + k - 1], right[i]);
        }

        return result;
    }

    // O(n) - time, O(n) - space
    // Solution based on dequeue
    public int[] maxSlidingWindow2(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n-k+1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.pollFirst();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = a[q.peek()];
            }
        }
        return r;
    }

    // O(n*k) - time, O(1) - space
    public int[] maxSlidingWindowBF(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return new int[]{};
        }
        List<Integer> result = new ArrayList<>();
        int length = nums.length;
        for (int i = 0; i <= length - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result.add(max);
        }
        return result.stream()
                .mapToInt(num -> num)
                .toArray();
    }
}
