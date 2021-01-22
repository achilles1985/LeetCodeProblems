package slidingWindow.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * H [marked]
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 * If there is no non-empty subarray with sum at least K, return -1.
 * <p>
 * Example 1:
 * Input: A = [1], K = 1
 * Output: 1
 * <p>
 * Example 2:
 * Input: A = [1,2], K = 4
 * Output: -1
 * <p>
 * Example 3:
 * Input: A = [2,-1,2], K = 3
 * Output: 3
 * <p>
 * Note:
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 */
// https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/143726/C%2B%2BJavaPython-O(N)-Using-Deque
public class ShortestSubarrayWithSumAtLeastK_862 {

    public static void main(String[] args) {
        ShortestSubarrayWithSumAtLeastK_862 s = new ShortestSubarrayWithSumAtLeastK_862();
        //System.out.println(s.shortestSubarray3(new int[] {84,-37,32,40,95}, 167)); //3
        System.out.println(s.shortestSubarray(new int[] {84,-37,32,40,95}, 167)); //3

        System.out.println(s.shortestSubarray(new int[] {1,1,1,3,-2,3,-1,1,1,1,1}, 4)); //3
        System.out.println(s.shortestSubarray(new int[] {2,-1,2,-3,5,4,2,2,2}, 8)); //2
        System.out.println(s.shortestSubarray(new int[] {77, 19, 35, 10, -14}, 19)); //1
        System.out.println(s.shortestSubarray(new int[] {1}, 1)); //1
        System.out.println(s.shortestSubarray(new int[] {1, 2}, 4)); //-1
        System.out.println(s.shortestSubarray(new int[] {2, -1, 2}, 3)); //3
    }

    // O(n^2) - time, O(1) - space
    public int shortestSubarrayBF(int[] A, int K) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            int sum = 0;
            for (int j = i; j < A.length; j++) {
                sum += A[j];
                if (sum >= K) {
                    minLength = Math.min(minLength, j - i + 1);
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    // O(n) - time, O(n) - space
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        long[] sums = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + A[i - 1];
        }
        int answer = n + 1;
        Deque<Integer> deque = new LinkedList<>();
        for (int index = 0; index <= n; index++) {
            long sum = sums[index];
            while (!deque.isEmpty() && sums[deque.getLast()] >= sum) {
                deque.removeLast();
            }
            while (!deque.isEmpty() && sums[deque.getFirst()] + K <= sum) {
                answer = Math.min(answer, index - deque.removeFirst());
            }
            deque.addLast(index);
        }

        return answer == n + 1 ? -1 : answer;
    }

    // Incorrect
    public int shortestSubarray3(int[] A, int K) {
        int sum = 0;
        int j = 0; int min = A.length+1;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            while(sum >= K) {
                min = Math.min(min, i+1-j);
                sum -= A[j];
                j++;
            }
        }

        return min == A.length+1 ? -1 : min;
    }
}
