package dynamic;

/** M
 Given a string, your task is to count how many palindromic substrings in this string.
 The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

 Example 1:
 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".

 Example 2:
 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

 Note:
 The input string length won't exceed 1000.
 */
public class PalindromicSubstrings_647 {

    public static void main(String[] args) {
        PalindromicSubstrings_647 s = new PalindromicSubstrings_647();
        System.out.println(s.countSubstrings("abc")); //3
        System.out.println(s.countSubstrings("aaa")); //6
    }

    // O(n^2) - time, space
    public int countSubstrings(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            count++;
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0, k = i; k < s.length(); j++, k++) {
                if (s.charAt(j) == s.charAt(k) && k-j == 1) {
                    dp[j][k] = true;
                    count++;
                } else if (s.charAt(j) == s.charAt(k) && dp[j+1][k-1]) {
                    dp[j][k] = true;
                    count++;
                } else {
                    dp[j][k] = false;
                }
            }
        }

        return count;
    }
}
