package dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two sequences, find the length of longest subsequence present in both of them. A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
 * For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        LongestCommonSubsequence s = new LongestCommonSubsequence();
        System.out.println(s.lcs("aab", "azb"));
        System.out.println(s.lcsDynamicTomDown("abcdefg", "acbdfghe"));
        System.out.println(s.lcsDynamicBottomUp("abcdefg", "acbdfghe"));
    }

    // O(2^n) - time, O(n) - space, where n - the length of the longest string
    public int lcs(String s1, String s2) {
        return lcsUtils(s1, s2, s1.length()-1, s2.length()-1);
    }

    private int lcsUtils(String s1, String s2, int i, int j) {
        if (i == 0 || j == 0) {
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + lcsUtils(s1, s2, i-1, j-1);
        }

        return Math.max(lcsUtils(s1, s2, i-1, j), lcsUtils(s1, s2, i, j-1));
    }

    // O(n*m) - time and space, n,m - length of strings
    public int lcsDynamicTomDown(String s1, String s2) {
        Map<Key, Integer> map = new HashMap<>();
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

    private int lcsDynamicTomDownUtils(String s1, String s2, int i, int j, Map<Key, Integer> map) {
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }
        Key key = new Key(i, j);
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

    private static class Key {
        int i;
        int j;

        public Key(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (i != key.i) return false;
            return j == key.j;
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }
}
