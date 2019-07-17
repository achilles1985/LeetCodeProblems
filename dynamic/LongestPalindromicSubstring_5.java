package dynamic;

// https://leetcode.com/problems/longest-palindromic-substring/
/** M
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example 1:
 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.

 Example 2:
 Input: "cbbd"
 Output: "bb"
 */
public class LongestPalindromicSubstring_5 {

    public static void main(String[] args) {
        LongestPalindromicSubstring_5 s = new LongestPalindromicSubstring_5();
        System.out.println(s.longestPalindrome("bbbab")); //bab
        System.out.println(s.longestPalindrome("babad")); //bab
        System.out.println(s.longestPalindrome("cbbd")); //bb

        System.out.println(s.longestPalindrome("bb")); //bb
        System.out.println(s.longestPalindromeDynamicBottomUp("bb")); //bb

        System.out.println(s.longestPalindrome("kbcdedcbl")); //bcdedcb
        System.out.println(s.longestPalindromeDynamicBottomUp("kbcdedcbl")); //bcdedcb
    }

    // O(n^3) - time, O(1) - space
    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        String max = "";
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0, k = i; k <= s.length(); j++, k++) {
                String sub = s.substring(j, k);
                if (isPolindrom(sub)) {
                    if (sub.length() > max.length()) {
                        max = sub;
                    }
                }
            }
        }
        return max;
    }

    // O(n^2) - time, O(n^2) - space
    public String longestPalindromeDynamicBottomUp(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return s;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        String max = s.substring(0,1);
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0, k = i; k < s.length(); j++, k++) {
                if (s.charAt(j) == s.charAt(k) && k-j == 1) {
                    dp[j][k] = true;
                } else if (s.charAt(j) == s.charAt(k) && dp[j+1][k-1]) {
                    dp[j][k] = true;
                } else {
                    dp[j][k] = false;
                }
                String sub = s.substring(j, k+1);
                if (dp[j][k] && sub.length() > max.length()) {
                    max = sub;
                }
            }
        }

        return max;
    }

    private boolean isPolindrom(String str) {
        int i = 0;
        int j = str.length()-1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
