package slidingWindow.hard;

import utils.SolutionUtils;

import java.util.*;

/**H [marked]
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

        SolutionUtils.print(s.maxSlidingWindow2(new int[]{1,3,-1,-3,5,3,6,7}, 3)); //[3,3,5,5,6,7]
        SolutionUtils.print(s.maxSlidingWindow2(new int[]{1,3,1,2,0,5}, 3)); //[3,3,2,5]
        SolutionUtils.print(s.maxSlidingWindow2(new int[]{1,3,-1,-2,-3,-4,-5,-6}, 3)); //[3,3,5,5,6,7]

        SolutionUtils.print(s.maxSlidingWindowDP(new int[]{1,3,-1,-3,5,3,6,7}, 3)); //[3,3,5,5,6,7]

        SolutionUtils.print(s.maxSlidingWindowBF(new int[]{1,3,-1,-3,5,3,6,7}, 3)); //[3,3,5,5,6,7]
        SolutionUtils.print(s.maxSlidingWindowBF(new int[]{1, -1}, 1)); //[1,-1]
        SolutionUtils.print(s.maxSlidingWindowBF(new int[]{1}, 1)); //[1]
    }

    // O(n*k) - time, O(1) - space
    // Time can be improve to O(n*log(k)) with heap
    public int[] maxSlidingWindowBF(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return new int[]{};
        }
        int[] result = new int[nums.length-k+1];
        int length = nums.length;
        for (int i = 0; i <= length - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }

    // https://leetcode.com/problems/sliding-window-maximum/discuss/947027/Java-O(nlogk)-Heap-solution
    // O(n*log(k)) - time, O(n) - space
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Queue<Node> maxHeap = new PriorityQueue<>(Comparator.comparing(Node::getVal).reversed());
        for(int i = 0; i < k; i++) {
            maxHeap.offer(new Node(nums[i], i));
        }
        res[0] = maxHeap.peek().val;
        for(int curStartIdx = 1; curStartIdx < nums.length - k + 1; curStartIdx++) {
            int curEndIndex = k + curStartIdx - 1;
            while(!maxHeap.isEmpty() && maxHeap.peek().index <= curEndIndex - k) {
                maxHeap.poll();
            }
            maxHeap.offer(new Node(nums[curEndIndex], curEndIndex));
            res[curStartIdx] = maxHeap.peek().val;
        }

        return res;
    }

    // O(n) - time, O(n) - space
    // Solution based on dequeue. Keep max element at queue head, if it's index is out of windows, remove.
    // If tail elements < nums[current] remove them.
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int[] result = new int[nums.length - k + 1];
        int idx = 0;
        // store index
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peekFirst() < i - k + 1) {
                q.pollFirst();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                result[idx++] = nums[q.peekFirst()];
            }
        }
        return result;
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

    private static class Node {
        int val;
        int index;
        Node(int val, int index) {
            this.val = val;
            this.index = index;
        }

        public int getVal() {
            return val;
        }

        public int getIndex() {
            return index;
        }
    }
}
