package dynamic;

// https://leetcode.com/problems/longest-palindromic-subsequence/

import java.util.HashMap;
import java.util.Map;

/** M
 *  Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

 Example 1:
 Input:
 "bbbab"
 Output: 4
 One possible longest palindromic subsequence is "bbbb".

 Example 2:
 Input:
 "cbbd"
 Output: 2
 One possible longest palindromic subsequence is "bb".
 */
public class LongestPalidromicSubsequance_516 {

    public static void main(String[] args) {
        LongestPalidromicSubsequance_516 s = new LongestPalidromicSubsequance_516();
        System.out.println(s.longestPalindromeSubseq("bbbab")); //4
        System.out.println(s.longestPalindromeSubseqTopDown("bbbab")); //4
        System.out.println(s.longestPalindromeSubseqBottomUp("bbbab")); //4

        System.out.println(s.longestPalindromeSubseq("cbbd")); //2
        System.out.println(s.longestPalindromeSubseqTopDown("cbbd")); //2
        System.out.println(s.longestPalindromeSubseqBottomUp("cbbd")); //2
        System.out.println(s.longestPalindromeSubseqBottomUp("agbdba")); //5
    }

    // O(2^n) - time, O(n) - space (n - depth of the stack)
    public int longestPalindromeSubseq(String s) {
        return longestPalindromeSubseq(s, 0, s.length()-1);
    }

    // O(n^2) - time, space
    public int longestPalindromeSubseqTopDown(String s) {
        Map<String, Integer> map = new HashMap<>();
        return longestPalindromeSubseqTopDown(s, 0, s.length()-1, map);
    }

    // O(n^2) - time, space
    public int longestPalindromeSubseqBottomUp(String s) {
        int[][] res = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            res[i][i] = 1;
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0, k = i; k < s.length(); j++, k++) {
                if (s.charAt(j) == s.charAt(k) && k-j == 1) {
                    res[j][k] = 2;
                }
                else if (s.charAt(j) == s.charAt(k)) {
                    res[j][k] = 2 + res[j+1][k-1];
                } else {
                    res[j][k] = Math.max(res[j+1][k], res[j][k-1]);
                }
            }
        }

        return res[0][s.length()-1];
    }

    private int longestPalindromeSubseqTopDown(String s, int start, int end, Map<String, Integer> map) {
        String key = start+","+end;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        if (start == end) {
            return 1;
        }
        if (s.charAt(start) == s.charAt(end) && start + 1 == end) {
            return 2;
        }
        if (s.charAt(start) == s.charAt(end)) {
            return 2 + longestPalindromeSubseqTopDown(s, start+1, end-1, map);
        }

        int left = longestPalindromeSubseqTopDown(s, start + 1, end, map);
        int right = longestPalindromeSubseqTopDown(s, start, end - 1, map);
        int max = Math.max(left, right);
        map.put(key, max);

        return map.get(key);
    }

    private int longestPalindromeSubseq(String s, int start, int end) {
        if (start == end) {
            return 1;
        }
        if (s.charAt(start) == s.charAt(end) && start + 1 == end) {
            return 2;
        }
        if (s.charAt(start) == s.charAt(end)) {
            return 2 + longestPalindromeSubseq(s, start+1, end-1);
        }

        int left = longestPalindromeSubseq(s, start + 1, end);
        int right = longestPalindromeSubseq(s, start, end - 1);

        return Math.max(left, right);
    }
}
