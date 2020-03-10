package dynamic;

import java.util.HashMap;
import java.util.Map;

/** M
 * Given two sequences, find the length of longest subsequence present in both of them.
 * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
 * For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”
 */
public class LongestCommonSubsequence_1143 {

    public static void main(String[] args) {
        LongestCommonSubsequence_1143 s = new LongestCommonSubsequence_1143();
        System.out.println(s.lcs("abcde", "ace")); //3
        System.out.println(s.lcs("abc", "abc")); //3
        System.out.println(s.lcs("abc", "def")); //0

        System.out.println(s.lcsDynamicTomDown("abcdefg", "acbdfghe"));
        System.out.println(s.lcsDynamicBottomUp("abcde", "ace"));
        System.out.println(s.lcsDynamicBottomUp("abcdefg", "acbdfghe"));
    }

    // O(2^n) - time, O(n) - space, where n - the length of the longest string
    public int lcs(String s1, String s2) {
        return lcsUtils(s1, s2, 0, 0);
    }

    private int lcsUtils(String s1, String s2, int i, int j) {
        if (i >= s1.length() || j >= s2.length()) {
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + lcsUtils(s1, s2, i+1, j+1);
        }

        return Math.max(lcsUtils(s1, s2, i+1, j), lcsUtils(s1, s2, i, j+1));
    }

    // O(n*m) - time and space, n,m - length of strings
    public int lcsDynamicTomDown(String s1, String s2) {
        Map<String, Integer> map = new HashMap<>();
        return lcsDynamicTomDownUtils(s1, s2, 0, 0, map);
    }

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
            return 1 + lcsUtils(s1, s2, i+1, j+1);
        }

        int max = Math.max(lcsUtils(s1, s2, i+1, j), lcsUtils(s1, s2, i, j+1));
        map.put(key, max);

        return map.get(key);
    }

}
