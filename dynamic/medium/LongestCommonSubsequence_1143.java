package dynamic.medium;

import java.util.HashMap;
import java.util.Map;

/** M [marked]
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * A subsequence of a string is a new string generated from the original string with some characters(can be none)
 * deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not).
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *  If there is no common subsequence, return 0.
 *
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * Constraints:
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * The input strings consist of lowercase English characters only.
 */
public class LongestCommonSubsequence_1143 {

    public static void main(String[] args) {
        LongestCommonSubsequence_1143 s = new LongestCommonSubsequence_1143();
        System.out.println(s.longestCommonSubsequence("bl", "yby")); //1
        System.out.println(s.longestCommonSubsequence("abcde", "ace")); //3
        System.out.println(s.longestCommonSubsequence("psnw", "vozsh")); //1

        System.out.println(s.lcsBF("abcde", "ace")); //3
        System.out.println(s.lcsBF("abc", "abc")); //3
        System.out.println(s.lcsBF("abc", "def")); //0

        System.out.println(s.lcsDynamicTomDown("abcdefg", "acbdfghe"));
        System.out.println(s.lcsDynamicBottomUp("abcde", "ace"));
        System.out.println(s.lcsDynamicBottomUp("abcdefg", "acbdfghe"));
    }

    // O(2^(n+m)) - time, O(n) - space, where n - the length of the longest string
    public int lcsBF(String s1, String s2) {
        return lcsBFUtils(s1, s2, 0, 0);
    }

    private int lcsBFUtils(String s1, String s2, int i, int j) {
        if (i >= s1.length() || j >= s2.length()) {
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + lcsBFUtils(s1, s2, i+1, j+1);
        }

        return Math.max(lcsBFUtils(s1, s2, i+1, j), lcsBFUtils(s1, s2, i, j+1));
    }

    // O(n*m) - time and space, n,m - length of strings
    public int lcsDynamicTomDown(String s1, String s2) {
        Map<String, Integer> map = new HashMap<>();
        return lcsDynamicTomDownUtils(s1, s2, 0, 0, map);
    }

    // O(n*m) - time and space, n,m - length of strings
    public int lcsDynamicBottomUp(String s1, String s2) {
        int[][] res = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    res[i][j] = 1 + res[i-1][j-1];
                } else {
                    res[i][j] = Math.max(res[i-1][j], res[i][j-1]);
                }
            }
        }

        return res[s1.length()][s2.length()];
    }

    private int lcsDynamicTomDownUtils(String s1, String s2, int i, int j, Map<String, Integer> map) {
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }
        String key = i + ":" + j;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + lcsBFUtils(s1, s2, i+1, j+1);
        }

        int max = Math.max(lcsBFUtils(s1, s2, i+1, j), lcsBFUtils(s1, s2, i, j+1));
        map.put(key, max);

        return map.get(key);
    }


    // Incorrect
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        String min = text1;
        String max = text2;
        if (text2.length() < text1.length()) {
            min = text2;
            max = text1;
        }
        int iMax = 0, iMin = 0;
        while (iMax < max.length() && iMin < min.length()) {
            if (max.charAt(iMax) == min.charAt(iMin)) {
                iMin++;
            }
            iMax++;
        }
        return iMin;
    }

}
