package dynamic.medium;

/** M [marked]
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
/*
Since whether string is a palindrom depends on where we start expending. We need to try 2 ways.
If the string is odd, start from left==right, even - left, right = left+1 (rotor -> odd, abba - even)

Solution: Monacher's algorithm is the most optimal one, O(n) - time, O(1) - space
 */
public class PalindromicSubstrings_647 {

    public static void main(String[] args) {
        PalindromicSubstrings_647 s = new PalindromicSubstrings_647();
        System.out.println(s.countSubstringsDP("aaa")); //6
        System.out.println(s.countSubstringsDP("abc")); //3
        System.out.println(s.countSubstrings2("racecar")); //10
        System.out.println(s.countSubstrings2("aaa")); //6
    }

    // O(n^3) - time, O(1) - space
    public int countSubstringsBF(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j <= s.length(); j++) {
                if (isPalindrom(i,j,s)) { // n, can be O(1) if use dp[][]
                    count++;
                }
            }
        }
        return count;
    }

    // O(n^2) - time, space
    public int countSubstringsDP(String s) {
        int count = 0;
        boolean dp[][] = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            count++;
            dp[i][i] = true;
        }
        for (int k = 1; k <= s.length() - 1; k++) {
            for (int i = 0, j = i+k; j < s.length(); i++, j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (k == 1 || dp[i+1][j-1]) {
                        dp[i][j] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // O(n^2) - time, O(1) - space
    public int countSubstrings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        for (int center = 0; center < s.length(); center++) {
            count += expand(center, center, s);
            count += expand(center, center+1, s);
        }
        return count;
    }

    private boolean isPalindrom(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    private int expand(int left, int right, String s) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            count++;
        }
        return count;
    }

}
