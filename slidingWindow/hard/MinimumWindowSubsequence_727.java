package slidingWindow.hard;

/**H [marked]
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
/*
    Use two pointers to go forward, when full match found, traverse backword
    There is also DP solution for this problem
 */
public class MinimumWindowSubsequence_727 {

    public static void main(String[] args) {
        MinimumWindowSubsequence_727 s = new MinimumWindowSubsequence_727();
        System.out.println(s.minWindow("abcdebdde", "bde")); //bcde
        System.out.println(s.minWindow("ADOBECODEBANC", "ABC")); //ADOBEC
        System.out.println(s.minWindow("bstl", "l")); //l

        //System.out.println(s.minWindow("jmeqksfrsdcmsiwvaovztaqenprpvnbstl", "u")); //""
        //System.out.println(s.minWindow("wcbsuiyzacfgrqsqsnodwmxzkz", "xwqe")); //""
        //System.out.println(s.minWindow("cnhczmccqouqadqtmjjzl", "mm")); //mccqouqadqtm

        //System.out.println(s.minWindowBF("abcdebdde", "bde")); //deb
        //System.out.println(s.minWindowBF("ADOBECODEBANC", "ABC")); //BANC
        //System.out.println(s.minWindowBF("bstl", "l")); //l
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

    // O(s^2) - time, O(1) - space
    public String minWindow(String S, String T) {
        if (S.length() == 0 || T.length() == 0) {
            return "";
        }
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        String result = "";
        while (right < S.length()) {
            int tIndex = 0;
            // use fast pointer to find the last character of T in S
            while (right < S.length() && tIndex < T.length()) {
                if (S.charAt(right) == T.charAt(tIndex)) {
                    tIndex++;
                }
                if (tIndex == T.length()) {
                    break;
                }
                right++;
            }
            // if right pointer is over than boundary
            if (right == S.length()) {
                break;
            }
            // use another slow pointer to traverse from right to left until find first character of T in S
            int left = right;
            tIndex = T.length() - 1;
            while (left >= 0) {
                if (S.charAt(left) == T.charAt(tIndex)) {
                    tIndex--;
                }
                if (tIndex < 0) {
                    break;
                }
                left--;
            }
            // if we found another subsequence with smaller length, update result
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                result = S.substring(left, right + 1);
            }
            // WARNING: we have to move right pointer to the next position of left pointer, NOT the next position
            // of right pointer
            right = left + 1;
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
