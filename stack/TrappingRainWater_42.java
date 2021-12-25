package stack;

// https://leetcode.com/problems/trapping-rain-water/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/** H
 Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

 Example:
 Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 Output: 6
 */
public class TrappingRainWater_42 {

    public static void main(String[] args) {
        TrappingRainWater_42 s = new TrappingRainWater_42();

        System.out.println(s.trapBruteForce(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); //6
        System.out.println(s.trapBruteForce(new int[]{5,2,1,0,4})); //9
        System.out.println(s.trap4(new int[]{5,2,1,0,4})); //9

        System.out.println(s.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); //6
        System.out.println(s.trap(new int[]{3,2,1,2,3})); //4

        System.out.println(s.trap2(new int[]{3,2,1,0,1,2,3})); //4
    }

    // O(n^2) - time, O(1) - space. (Calculate the area of column at the current position)
    // Moving from current position go to left and right and find max height on the left and right.
    // Then min(leftMax, rightMax), area = minHeight - height[1]
    public int trapBruteForce(int[] height) {
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = height[i];
            for (int j = i; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }
            int rightMax = height[i];
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            ans += (Math.min(leftMax, rightMax) - height[i]);
        }

        return ans;
    }

    // Based on stack
    // O(n) - time, O(n) - space
    public int trap2(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int popped = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int w = i - stack.peek()-1;
                int h = Math.min(height[i], height[stack.peek()]) - height[popped]; // calculate the area of row between 2 points
                ans += w*h;
            }
            stack.push(i);
        }

        return ans;
    }

    // Not using stack
    // O(n) - time, O(n) - space
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int size = height.length;
        int[] maxLeft = new int[height.length];
        maxLeft[0] = height[0];
        for (int i = 1; i < size; i++) {
            maxLeft[i] = Math.max(maxLeft[i-1], height[i]);
        }
        int[] maxRight = new int[height.length];
        maxRight[size-1] = height[size-1];
        for (int i = size-2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i+1], height[i]);
        }

        int ans = 0;
        for (int i = 1; i < size-1; i++) {
            ans += (Math.min(maxLeft[i], maxRight[i]) - height[i]);
        }

        return ans;
    }

    // O(n) - time, O(1) - space
    public int trap4(int[] height) {
        int result = 0;
        int left_max = 0, right_max = 0;
        int left = 0, right = height.length-1;
        while (left <= right) {
            if (height[left] < height[right]) {
                if (height[left] > left_max) {
                   left_max = height[left];
                } else {
                  result += left_max - height[left];
                }
                left++;
            }
            else {
                if (height[right] > right_max) {
                    right_max = height[right];
                } else {
                    result += right_max - height[right];
                }
                right--;
            }
        }

        return result;
    }
}
