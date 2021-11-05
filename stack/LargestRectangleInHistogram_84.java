package stack;

// https://leetcode.com/problems/largest-rectangle-in-histogram/

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/** H
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 The largest rectangle is shown in the shaded area, which has area = 10 unit.

 Example:
 Input: [2,1,5,6,2,3]
 Output: 10
 */
public class LargestRectangleInHistogram_84 {

    public static void main(String[] args) {
        LargestRectangleInHistogram_84 s = new LargestRectangleInHistogram_84();

        System.out.println(s.largestRectangleArea2(new int[]{4,2,0,3,2,5})); // 6
        System.out.println(s.largestRectangleArea2(new int[]{2,1,5,6,2,3})); // 10
        System.out.println(s.largestRectangleArea2(new int[]{2,1,2})); // 3
        System.out.println(s.largestRectangleArea2(new int[]{3,2,1})); // 4
        System.out.println(s.largestRectangleArea2(new int[]{1,2,3})); // 4
        System.out.println(s.largestRectangleArea2(new int[]{1,2,3,2,1})); // 6
    }

    // O(n^2) - time, O(1) - space
    public int largestRectangleAreaBF(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = heights[i];
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight*(j-i+1));
            }
        }

        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // to avoid NPE
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] <= heights[stack.peek()]) {
                Integer popped = stack.pop();
                int area = heights[popped]*(i - stack.peek()-1);
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            Integer popped = stack.pop();
            int area = heights[popped]*(heights.length - stack.peek()-1);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    // O(n) - time, O(n) - space
    public int largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int i;
        for (i = 0; i < heights.length;) {
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                int area;
                if (stack.isEmpty()) {
                    area = heights[top]*i;
                } else {
                    area = heights[top]*(i-stack.peek()-1);
                }
                maxArea = Math.max(maxArea, area);
            }
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            int area;
            if (stack.isEmpty()) {
                area = heights[top]*i;
            } else {
                area = heights[top]*(i-stack.peek()-1);
            }
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public int maxHistogram(int input[]){
        Deque<Integer> stack = new LinkedList<Integer>();
        int maxArea = 0;
        int area = 0;
        int i;
        for(i=0; i < input.length;){
            if(stack.isEmpty() || input[stack.peekFirst()] <= input[i]){
                stack.offerFirst(i++);
            }else{
                int top = stack.pollFirst();
                //if stack is empty means everything till i has to be
                //greater or equal to input[top] so get area by
                //input[top] * i;
                if(stack.isEmpty()){
                    area = input[top] * i;
                }
                //if stack is not empty then everythin from i-1 to input.peek() + 1
                //has to be greater or equal to input[top]
                //so area = input[top]*(i - stack.peek() - 1);
                else{
                    area = input[top] * (i - stack.peekFirst() - 1);
                }
                if(area > maxArea){
                    maxArea = area;
                }
            }
        }
        while(!stack.isEmpty()){
            int top = stack.pollFirst();
            //if stack is empty means everything till i has to be
            //greater or equal to input[top] so get area by
            //input[top] * i;
            if(stack.isEmpty()){
                area = input[top] * i;
            }
            //if stack is not empty then everything from i-1 to input.peek() + 1
            //has to be greater or equal to input[top]
            //so area = input[top]*(i - stack.peek() - 1);
            else{
                area = input[top] * (i - stack.peekFirst() - 1);
            }
            if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }
}
