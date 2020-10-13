package slidingWindow.hard;

/**H [TODO]
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
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
        System.out.println(s.minWindow("ADOBECODEBANC", "ABC")); //ADOBEC
        System.out.println(s.minWindow("bstl", "l")); //l
        System.out.println(s.minWindow("jmeqksfrsdcmsiwvaovztaqenprpvnbstl", "u")); //""
        System.out.println(s.minWindow("wcbsuiyzacfgrqsqsnodwmxzkz", "xwqe")); //""
        System.out.println(s.minWindow("cnhczmccqouqadqtmjjzl", "mm")); //mccqouqadqtm

        //System.out.println(s.minWindowBF("abcdebdde", "bde")); //deb
        //System.out.println(s.minWindowBF("ADOBECODEBANC", "ABC")); //BANC
        //System.out.println(s.minWindowBF("bstl", "l")); //l
    }

    // O(S*T) - time, O(S) - space
    public String minWindow(String S, String T) {
        if (S == null || S.length() == 0 || T == null || T.length() == 0) {
            return "";
        }
        int[][] dp = new int[S.length() + 1][T.length() + 1];
        for (int i = 1; i <= T.length(); i++) {
            dp[0][i] = -1;
        }
        for (int i = 1; i <= S.length(); i++) {
            dp[i][0] = i;
        }
        int minLen = Integer.MAX_VALUE;
        int startPos = -1;
        for (int i = 1; i <= S.length(); i++) {
            for (int j = 1; j <= T.length(); j++) {
                dp[i][j] = -1;
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            if (dp[i][T.length()] != -1) {
                int len = i - dp[i][T.length()];
                if (len < minLen) {
                    startPos = dp[i][T.length()];
                    minLen = len;
                }
            }
        }
        return startPos == -1? "" : S.substring(startPos, startPos + minLen);
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
