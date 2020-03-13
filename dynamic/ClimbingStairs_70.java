package dynamic;

//https://leetcode.com/problems/delete-operation-for-two-strings/

import java.util.Arrays;

/** E
 You are climbing a stair case. It takes n steps to reach to the top.
 Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 Note: Given n will be a positive integer.

 Example 1:
 Input: 2
 Output: 2
 Explanation: There are two ways to climb to the top.
 1. 1 step + 1 step
 2. 2 steps
 Example 2:

 Input: 3
 Output: 3
 Explanation: There are three ways to climb to the top.
 1. 1 step + 1 step + 1 step
 2. 1 step + 2 steps
 3. 2 steps + 1 step
 */
public class ClimbingStairs_70 {

    public static void main(String[] args) {
        ClimbingStairs_70 s = new ClimbingStairs_70();
        System.out.println(s.climbStairsBruteForce(4));
        System.out.println(s.climbStairsDynamicMemoization(4));
        System.out.println(s.climbStairsDynamicBottomUp(4));
        System.out.println(s.climbStairsDynamicBottomUp2(4));
    }

    // O(2^n)-time, since on every stage we need to make 2 choices, O(n) - Call stack max depth. Max depth of the recursion tree goes n calls deep.
    public int climbStairsBruteForce(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }

        int a = climbStairsBruteForce(n - 1);
        int b = climbStairsBruteForce(n - 2);
        return a + b;
    }

    // O(n) - time and space
    public int climbStairsDynamicMemoization(int n) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        return climbStairsDynamicMemoization(n, cache);
    }

    // O(n) - time, O(n) - space
    public int climbStairsDynamicBottomUp(int n) {
        if (n <= 2) {
            return n;
        }
        int[] cache = new int[n+1];
        cache[0] = 0;
        cache[1] = 1;
        cache[2] = 2;
        for (int i = 3; i <= n; i++) {
            cache[i] = cache[i-1] + cache[i-2];
        }

        return cache[n];
    }

    // O(n) - time, O(1) - space
    public int climbStairsDynamicBottomUp2(int n) {
        if (n <= 2) {
            return n;
        }

        int s1 = 1;
        int s2 = 2;
        int s3 = 0;
        for (int i = 3; i <= n; i++) {
            s3 = s1 + s2;
            s1 = s2;
            s2 = s3;
        }

        return s3;
    }

    private int climbStairsDynamicMemoization(int n, int[] cache) {
        if (n == 1 || n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        if (cache[n] != -1) {
            return cache[n];
        }

        cache[n] = climbStairsDynamicMemoization(n-1, cache) + climbStairsDynamicMemoization(n-2, cache);

        return cache[n];
    }

}
