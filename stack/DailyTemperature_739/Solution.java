package stack.DailyTemperature_739;

import java.util.Stack;

/**
 Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

 Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class Solution {

    // O(n^2) - time, O(1) - memory (result array is not counted)
    public int[] dailyTemperatures(int[] input) {
        int[] res = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            for (int j = i+1; j < input.length; j++) {
                if (input[j] > input[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    // O(m) public int[] dailyTemperatures(int[] input) {
    public int[] dailyTemperatures2(int[] input) {
        int[] res = new int[input.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < input.length; i++) {
            while (!stack.empty() && input[i] > input[stack.peek()]) {
                int idx = stack.pop();
                res[idx] = i - idx;
            }
            stack.push(i);
        }

        return res;
    }
}
