package dynamic;

/** H
 Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).
 The matching should cover the entire input string (not partial).

 Note:
 s could be empty and contains only lowercase letters a-z.
 p could be empty and contains only lowercase letters a-z, and characters like ? or *.

 Example 1:
 Input:
 s = "aa"
 p = "a"
 Output: false
 Explanation: "a" does not match the entire string "aa".

 Example 2:
 Input:
 s = "aa"
 p = "*"
 Output: true
 Explanation: '*' matches any sequence.

 Example 3:
 Input:
 s = "cb"
 p = "?a"
 Output: false
 Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

 Example 4:
 Input:
 s = "adceb"
 p = "*a*b"
 Output: true
 Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".

 Example 5:
 Input:
 s = "acdcb"
 p = "a*c?b"
 Output: false
 */
// https://leetcode.com/problems/wildcard-matching/
public class WildcardMatching {

    public static void main(String[] args) {
        WildcardMatching s = new WildcardMatching();

        System.out.println(s.isMatchRecursive("abbcd", "a*b?")); // false

        System.out.println(s.isMatchRecursive("aa", "a")); // false
        System.out.println(s.isMatch("aa", "a")); // false

        System.out.println(s.isMatchRecursive("aa", "*")); // true
        System.out.println(s.isMatch("aa", "*")); // true

        System.out.println(s.isMatchRecursive("cb", "?a")); // false
        System.out.println(s.isMatch("cb", "?a")); // false

        System.out.println(s.isMatchRecursive("adceb", "*a*b")); // true
        System.out.println(s.isMatch("adceb", "*a*b")); // true

        System.out.println(s.isMatchRecursive("acdcb", "a*c?b")); // false
        System.out.println(s.isMatch("acdcb", "a*c?b")); // false
    }

    public boolean isMatchRecursive(String s, String p) {
        return isMatchRecursiveUtil(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    private boolean isMatchRecursiveUtil(char[] text, char[] pattern, int pos1, int pos2) {
        if (pos2 == pattern.length) {
            return text.length == pos1;
        }

        if (pattern[pos2] != '*') {
            if (pos1 < text.length && (text[pos1] == pattern[pos2]) || pattern[pos2] == '?') {
                return isMatchRecursiveUtil(text, pattern, pos1 + 1, pos2 + 1);
            } else {
                return false;
            }
        } else {
            pos1--;
            while (pos1 < text.length) {
                if (isMatchRecursiveUtil(text, pattern, pos1 + 1, pos2 + 1)) {
                    return true;
                }
                pos1++;
            }
            return false;
        }
    }

    // O(s*p) - time, space
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 1; i < dp[0].length; i++) {
            if (p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-1];
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }
            }
        }

        return dp[s.length()][p.length()];
    }
}
