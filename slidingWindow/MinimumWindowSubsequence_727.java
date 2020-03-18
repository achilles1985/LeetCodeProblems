package slidingWindow;

/**H
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 *
 * If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple
 * such minimum-length windows, return the one with the left-most starting index.
 *
 * Example 1:
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 *
 * Note:
 *     All the strings in the input will only contain lowercase letters.
 *     The length of S will be in the range [1, 20000].
 *     The length of T will be in the range [1, 100].
 */
public class MinimumWindowSubsequence_727 {

    public static void main(String[] args) {
        MinimumWindowSubsequence_727 s = new MinimumWindowSubsequence_727();
        System.out.println(s.minWindow("abcdebdde", "bde")); //bcde
        System.out.println(s.minWindow("bstl", "l")); //l

        System.out.println(s.minWindowDP("abcdebdde", "bde")); //bcde
        System.out.println(s.minWindowDP("bstl", "l")); //l
    }

    public String minWindowDP(String S, String T) {
        int m = T.length(), n = S.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        int start = 0, len = n + 1;
        for (int j = 1; j <= n; j++) {
            if (dp[m][j] != 0) {
                if (j - dp[m][j] + 1 < len) {
                    start = dp[m][j] - 1;
                    len = j - dp[m][j] + 1;
                }
            }
        }
        return len == n + 1 ? "" : S.substring(start, start + len);
    }

    // O(S*T) - time, O(S) - spacem
    public String minWindow(String S, String T) {
        String output = "";
        int minLen = 20001;
        for (int i = 0; i <= S.length() - T.length(); i++) {
            while (i < S.length() && S.charAt(i) != T.charAt(0)) {
                i++;
            }
            //int pos = find(S.substring(i, Math.min(i + minLen, S.length())), T);
            int pos = find(S.substring(i), T);
            if (pos != -1 && pos < minLen) {
                minLen = pos;
                output = S.substring(i, i + pos);
            }
        }
        return output;
    }

    private int find(String S, String T) {
        for (int i = 0, j = 0; i < S.length() && j < T.length();) {
            if (S.charAt(i) == T.charAt(j)) {
                j++;
            }
            i++;
            if (j == T.length()) {
                return i;
            }
        }
        return -1;
    }

    // O(n^3) - time, O(1) - space
    public String minWindowBF(String S, String T) {
        if (S == null || S.length() == 0 || T == null || T.length() == 0) {
            return "";
        }
        int min = Integer.MAX_VALUE;
        String result = "";
        for (int i = 0; i < S.length(); i++) {
            for (int j = i; j <= S.length(); j++) {
                String substr = S.substring(i, j);
                if (match(substr, T)) {
                    if (substr.length() < min) {
                        min = substr.length();
                        result = substr;
                    }
                }
            }
        }
        return result;
    }

    private boolean match(String substr, String target) {
        int i = 0, j = 0;
        int count = 0;
        while (i < substr.length() && j < target.length()) {
            if (substr.charAt(i) == target.charAt(j)) {
                count++;
                j++;
            }
            i++;
        }
        return count == target.length();
    }
}
