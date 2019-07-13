package dynamic.WildcardMatching_44;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence). (not only the same as previous one, e.g. a* does not mean only aaaa, but also abcde)
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
public class Solution {

    public boolean isMatch(String s, String p) {
        char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();
        if (pattern.length == 1 && pattern[0] == '*') {
            return true;
        }

        boolean[][] m = new boolean[text.length + 1][pattern.length + 1];
        m[0][0] = true;

        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[i].length; j++) {
                if (pattern[j-1] == text[i-1] || pattern[j-1] == '?') {
                    m[i][j] = m[i-1][j-1];
                } else if (pattern[j-1] == '*') {
                    m[i][j] = m[i][j-1] | m[i-1][j];
                }
            }

        }

        return m[text.length][pattern.length];
    }
}
