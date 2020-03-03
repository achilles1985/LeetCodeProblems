package algorithmstechniqes;

import java.util.Stack;

public class MonotonousIncreaseStack {

    public static void main(String[] args) {

    }

    // O(n) - time, space
    public void monotonouslyIncreasingStack(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                stack.pop();
            }
            stack.push(nums[i]);
        }
    }
}
