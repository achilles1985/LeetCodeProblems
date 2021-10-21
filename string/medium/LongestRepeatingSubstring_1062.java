package string.medium;

import java.util.HashSet;
import java.util.Set;

/**M [marked]
 * Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.
 *
 * Example 1:
 * Input: "abcd"
 * Output: 0
 * Explanation: There is no repeating substring.
 *
 * Example 2:
 * Input: "abbaba"
 * Output: 2
 * Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
 *
 * Example 3:
 * Input: "aabcaabdaab"
 * Output: 3
 * Explanation: The longest repeating substring is "aab", which occurs 3 times.
 *
 * Example 4:
 * Input: "aaaaa"
 * Output: 4
 * Explanation: The longest repeating substring is "aaaa", which occurs twice.
 *
 * Constraints:
 *     The string S consists of only lowercase English letters from 'a' - 'z'.
 *     1 <= S.length <= 1500
 */
public class LongestRepeatingSubstring_1062 {

    public static void main(String[] args) {
        LongestRepeatingSubstring_1062 s = new LongestRepeatingSubstring_1062();
        System.out.println(s.longestRepeatingSubstring("aabcaabdaab")); //3
        System.out.println(s.longestRepeatingSubstring("abbaba")); //2
        System.out.println(s.longestRepeatingSubstring("abcd")); //0
        System.out.println(s.longestRepeatingSubstring("aaaaa")); //4
    }

    // O(n^3) - time, O(n^2) - space
    public int longestRepeatingSubstringBF(String s) {
        for (int i = s.length() - 1; i >= 1; i--) { // L
            Set<String> set = new HashSet<>();
            for (int j = 0; j + i <= s.length(); j++) { // (N-L)
                String sub = s.substring(j, j + i); // L
                if (!set.add(sub)) {
                    return sub.length();
                }
            }
        }
        return 0;
    }

    // O(N*logN) - time,
    // O(n^2) - space (to reduce space to O(N), one can store string hashes (int) instead of strings (String) themselves).
    public int longestRepeatingSubstring(String S) {
        if (S == null || S.isEmpty()) {
            return 0;
        }
        int lo = 1, hi = S.length();
        while (lo <= hi) {
            int mid = (lo + hi)/2;
            if (hasRepeated(mid, S)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return lo - 1;
    }

    // Binary Search + Robin Karp
    // O(N*logN) - time, O(N) - space
    public int longestRepeatingSubstringRK(String S) {
        // TODO
        return 0;
    }

    private boolean hasRepeated(int length, String s) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i + length <= s.length(); i++) {
            String sub = s.substring(i, i + length);
            if (!set.add(sub)) {
                return true;
            }
        }
        return false;
    }
}
