package dynamic;

import java.time.LocalDate;
import java.util.Deque;
import java.util.LinkedList;

/**
 * H
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the
 * area of largest rectangle in the histogram.
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * <p>
 * Example:
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
// https://github.com/mission-peace/interview/blob/master/src/com/interview/stackqueue/MaximumHistogram.java
public class LargestRectangleInHistogram_84 {

    public static void main(String[] args) {
        LocalDate d1 = LocalDate.of(1900, 1, 1);
        LocalDate d2 = LocalDate.of(2000, 1, 1);
        System.out.println(d1.isBefore(d2));


        LargestRectangleInHistogram_84 s = new LargestRectangleInHistogram_84();
        System.out.println(s.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3})); //10

        System.out.println(s.largestRectangleAreaStack(new int[]{2, 1, 5, 6, 2, 3})); //10
        System.out.println(s.largestRectangleAreaStack(new int[]{1})); //1
        System.out.println(s.largestRectangleAreaStack(new int[]{1,1})); //2
        System.out.println(s.largestRectangleAreaStack(new int[]{2,1,2})); //3
    }

    // O(n^2) - time, O(1) - space
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int maxA = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            min = heights[i];
            for (int j = i; j < n; j++) { // consider the segment [i..j] with length (j-i+1)
                min = Math.min(min, heights[j]); // the min value in this segment
                maxA = Math.max(maxA, min * (j - i + 1)); // the global max area value so far
            }
        }

        return maxA;
    }

    // O(n) - time, O(n) - space
    public int largestRectangleAreaStack(int[] heights) {
        Deque<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        int area = 0;
        int i;
        for(i=0; i < heights.length;){
            if(stack.isEmpty() || heights[stack.peekFirst()] <= heights[i]){
                stack.offerFirst(i++);
            }else{
                int top = stack.pollFirst();
                //if stack is empty means everything till i has to be
                //greater or equal to input[top] so get area by
                //input[top] * i;
                if(stack.isEmpty()){
                    area = heights[top] * i;
                }
                //if stack is not empty then everythin from i-1 to input.peek() + 1
                //has to be greater or equal to input[top]
                //so area = input[top]*(i - stack.peek() - 1);
                else{
                    area = heights[top] * (i - stack.peekFirst() - 1);
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
                area = heights[top] * i;
            }
            //if stack is not empty then everything from i-1 to input.peek() + 1
            //has to be greater or equal to input[top]
            //so area = input[top]*(i - stack.peek() - 1);
            else{
                area = heights[top] * (i - stack.peekFirst() - 1);
            }
            if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }
}
