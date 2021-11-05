package stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/** M
 Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj.
 Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
 Note: n will be less than 15,000.

 Example 1:
 Input: [1, 2, 3, 4]
 Output: False
 Explanation: There is no 132 pattern in the sequence.

 Example 2:
 Input: [3, 1, 4, 2]
 Output: True
 Explanation: There is a 132 pattern in the sequence: [1, 4, 2].

 Example 3:
 Input: [-1, 3, 2, 0]
 Output: True
 Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */
/*
 1. should the order be preserved? (yes)
 2. can distance between numbers be more then one? (yes)
 */
public class Pattern132_456 {

    public static void main(String[] args) {
        Pattern132_456 s = new Pattern132_456();
        System.out.println(s.find132patternBF(new int[]{3,5,0,3,4})); // true
        System.out.println(s.find132patternBF(new int[]{1,2,3,4})); // false
        System.out.println(s.find132patternBF(new int[]{3,1,4,2})); // true
        System.out.println(s.find132patternBF(new int[]{-1,3,2,0})); // true
    }

    // O(n^3) - time, O(1) - space
    public boolean find132patternBF(int[] nums) {
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i+1; j < nums.length-1; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if (nums[i] < nums[j] && nums[k] < nums[j] && nums[i] < nums[k]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // O(n^2) - time, O(1) - space
    public boolean find132patternBF2(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length; j++) {
            min = Math.min(min, nums[j]);
            for (int k = j + 1; k < nums.length; k++) {
                if (nums[k] < nums[j] && min < nums[k]) {
                    return true;
                }
            }
        }

        return false;
    }

    // https://leetcode.com/problems/132-pattern/discuss/350264/Easy-Java-Solution
    // O(n) - time, O(n) - space
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }
        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] > min[j]) {
                while (!stack.isEmpty() && stack.peek() <= min[j]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }
                stack.push(nums[j]);
            }
        }
        return false;
    }
}
