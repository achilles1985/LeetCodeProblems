package slidingWindow;

import java.util.*;

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
        System.out.println(s.minWindow("abcdebdde", "bde")); //deb
        System.out.println(s.minWindow("ADOBECODEBANC", "ABC")); //BANC
        System.out.println(s.minWindow("bstl", "l")); //l

        System.out.println(s.minWindowBF("abcdebdde", "bde")); //deb
        System.out.println(s.minWindowBF("ADOBECODEBANC", "ABC")); //BANC
        System.out.println(s.minWindowBF("bstl", "l")); //l
    }

    // O(s + t) - time, space
    public String minWindow(String s, String t) {
        Map<Character, Integer> sourceToCounter = new HashMap<>();
        Map<Character, Integer> targetToCounter = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            targetToCounter.put(c, targetToCounter.getOrDefault(c, 0) + 1);
        }

        int targetSize = targetToCounter.size();
        int currentSize = 0;
        int right = 0, left = 0;
        int min = Integer.MAX_VALUE;
        String result = "";
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            sourceToCounter.put(rightChar, sourceToCounter.getOrDefault(rightChar, 0) + 1);
            if (targetToCounter.containsKey(rightChar) && targetToCounter.get(rightChar) == sourceToCounter.get(rightChar)) {
                currentSize++;
            }
            while (left <= right && currentSize == targetSize) {
                char leftChar = s.charAt(left);
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    result = s.substring(left, right + 1);
                }
                sourceToCounter.put(leftChar, sourceToCounter.getOrDefault(leftChar, 0) - 1);
                if (targetToCounter.containsKey(leftChar) && sourceToCounter.get(leftChar) < targetToCounter.get(leftChar)) {
                    currentSize--;
                }
                left++;
            }
            right++;
        }
        return result;
    }

    // O(n^3) - time, O(1) - space
    public String minWindowBF(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        int min = Integer.MAX_VALUE;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j <= s.length(); j++) {
                String substr = s.substring(i, j);
                if (match(substr, t)) {
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
