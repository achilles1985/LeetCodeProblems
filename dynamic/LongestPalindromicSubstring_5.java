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
/*
Since whether string is a palindrom depends on where we start expending. We need to try 2 ways.
If the string is odd, start from left==right, even - left, right = left+1 (rotor -> odd, abba - even)
 */
public class LongestPalindromicSubstring_5 {

    public static void main(String[] args) {
        LongestPalindromicSubstring_5 s = new LongestPalindromicSubstring_5();
        System.out.println(s.longestPalindrome("racecar")); //bab
        System.out.println(s.longestPalindrome2("racecar")); //bab
        System.out.println(s.longestPalindrome("nabbam")); //abba
        System.out.println(s.longestPalindrome2("nabbam")); //abba
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


    // O(n^2) - time, O(1) - space
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
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
