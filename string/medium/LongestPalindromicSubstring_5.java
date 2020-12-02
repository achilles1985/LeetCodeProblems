package string.medium;

/** M
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: "cbbd"
 * Output: "bb"
 */
/*
 Edge cases: "a", ""
 */
public class LongestPalindromicSubstring_5 {

    public static void main(String[] args) {
        LongestPalindromicSubstring_5 s = new LongestPalindromicSubstring_5();
        System.out.println(s.longestPalindrome("abb")); // bb
        System.out.println(s.longestPalindrome("bb")); // bb
        System.out.println(s.longestPalindrome("abcba")); // abcba
        System.out.println(s.longestPalindrome("abcdddmnl")); // ddd
        System.out.println(s.longestPalindrome("cbbd")); // bb
        System.out.println(s.longestPalindrome("babad")); // bab
        System.out.println(s.longestPalindrome("cbbd")); // bb
    }

    // O(n^3) - time, O(1) - space
    public String longestPalindromeBF(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }

        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                String substr = s.substring(i, j);
                if (isPalindrom(substr)) {
                    if (substr.length() > result.length()) {
                        result = substr;
                    }
                }
            }
        }
        return result;
    }

    // O(n^2) - time, O(1) - space, https://leetcode.com/problems/longest-palindromic-substring/solution/ (Approach 4: Expand Around Center)
     public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); //odd
            int len2 = expandAroundCenter(s, i, i + 1); //even
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len-1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // O(n^2) - time, space
    public String longestPalindromeDP(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        String res = s.substring(0,1);
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0, k = i; k < s.length(); j++, k++) {
                if (s.charAt(j) == s.charAt(k) && k-j == 1) {
                    dp[j][k] = true;
                } else if (s.charAt(j) == s.charAt(k) && dp[j+1][k-1]) {
                    dp[j][k] = true;
                }
                String substr = s.substring(j, k+1);
                if (dp[j][k] && substr.length() > res.length()) {
                    res = substr;
                }
            }
        }
        return res;
    }

    // O(n) - time, Manacher's algorithms
    public String longestPalindromeMA(String s) {
        return "";
    }

    private boolean isPalindrom(String substr) {
        int i = 0;
        int j = substr.length()-1;
        while (i < j) {
            if (j < substr.length() && substr.charAt(i) != substr.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
