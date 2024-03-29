package string.hard;

import java.util.Stack;

/** H [marked] (left path than right path)
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
 * parentheses substring.
 *
 * Example 1:
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 *
 * Example 2:
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParentheses_32 {

    public static void main(String[] args) {
        LongestValidParentheses_32 s = new LongestValidParentheses_32();
        System.out.println(s.subarraysWithMoreZerosThanOnes(new int[]{0,1,1,0,1})); //9

        System.out.println(s.longestValidParentheses(")()())")); //4
        System.out.println(s.longestValidParentheses2("(()")); //2

    }

    public int subarraysWithMoreZerosThanOnes(int[] nums) {
        int count = 0;
        for (int k = 1; k < nums.length; k++) {
            for (int i = 0, j = i+k; j < nums.length; i++, j++) {
                int onces = 0, zeros = 0;
                for (int m = i; m <= j; m++) {
                    if (nums[m] == 1) {
                        onces++;
                    } else {
                        zeros++;
                    }
                }
                if (onces > zeros) {
                    count++;
                }
            }
        }
        return count;
    }

    // O(n^3) - time, O(n) - space
    public int longestValidParenthesesBF(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (isValid(sub)) {
                    int localMax = sub.length();
                    max = Math.max(localMax, max);
                }
            }
        }
        return max;
    }

    // O(n) - time, space
    public int longestValidParenthesesDP(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    // O(n) - time, space
    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    // O(n) - time, O(1) - space
    public int longestValidParentheses2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2*left);
            } else if (right > left) {
                left = 0;
                right = 0;
            }
        }
        left = 0;
        right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2*right);
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return max;
    }

    private boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
