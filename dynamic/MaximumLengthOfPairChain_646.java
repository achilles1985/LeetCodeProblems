package dynamic;

import java.util.Arrays;

import org.omg.CORBA.INTERNAL;

/** M
 You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
 Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

 Example 1:
 Input: [[1,2], [2,3], [3,4]]
 Output: 2
 Explanation: The longest chain is [1,2] -> [3,4]

 Note:
 The number of given pairs will be in the range [1, 1000].
 */
public class MaximumLengthOfPairChain_646 {

    public static void main(String[] args) {
        MaximumLengthOfPairChain_646 s = new MaximumLengthOfPairChain_646();

        System.out.println(s.findLongestChain(new int[][] {{1,2}, {2,3}, {3,4}})); // 2
        System.out.println(s.findLongestChainDynamic(new int[][] {{1,2}, {2,3}, {3,4}})); // 2

        System.out.println(s.findLongestChain(new int[][] {{0,10}, {1,2}, {3,4}, {2,3}, {5,7}, {8,15}})); // 4
        System.out.println(s.findLongestChainDynamic(new int[][] {{0,10}, {1,2}, {3,4}, {2,3}, {5,7}, {8,15}})); // 4

        System.out.println(s.findLongestChain(new int[][] {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}})); // 4
        System.out.println(s.findLongestChainDynamic(new int[][] {{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}})); // 4

        System.out.println(s.findLongestChain(new int[][] {{7,9},{4,5},{7,9},{-7,-1},{0,10},{3,10},{3,6},{2,3}})); // 4
        System.out.println(s.findLongestChainDynamic(new int[][] {{7,9},{4,5},{7,9},{-7,-1},{0,10},{3,10},{3,6},{2,3}})); // 4
    }

    // greedy method, O(n*logn) - time, O(n) - space taken by sorting algorithm
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (p1, p2) -> p1[1] - p2[1]);
        int max = Integer.MIN_VALUE;
        int count = 0;
        for (int[] pair: pairs) {
            if (pair[0] > max) {
                count++;
                max = pair[1];
            }
        }

        return count;
    }

    // O(n^2) - time, O(n) - space
    public int findLongestChainDynamic(int[][] pairs) {
        int[] dp = new int[pairs.length];
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }

        int max = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1] ) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
                max = Math.max(max, dp[i]);
            }
        }

        return max;
    }
}
