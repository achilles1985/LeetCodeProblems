package string.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    // O(n^2) - time, space
    public int longestRepeatingSubstringBF(String S) {
        if (S == null || S.isEmpty()) {
            return 0;
        }
        Map<String, Integer> frequency = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            for (int j = i+1; j <= S.length(); j++) {
                String sub = S.substring(i, j);
                frequency.put(sub, frequency.getOrDefault(sub, 0) + 1);
            }
        }

        String result = "";
        for (Map.Entry<String, Integer> entry: frequency.entrySet()) {
            if (entry.getValue() > 1) {
                if (entry.getKey().length() > result.length()) {
                    result = entry.getKey();
                }
            }
        }
        return result.length();
    }

    // O(N*logN) - time,
    // O(n^2) - space (to reduce space to O(N), one can store string hashes (int) instead of strings (String) themselves).
    public int longestRepeatingSubstring(String S) {
        if (S == null || S.isEmpty()) {
            return 0;
        }
        int L = S.length();
        int lo = 1, hi = L;
        while (lo <= hi) {
            int mid = (lo + hi)/2;
            if (search(S, L, mid)) {
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

    private boolean search(String str, int length, int n) {
        Set<String> unique = new HashSet<>();
        for (int i = 0; i <= length - n; i++) {
            String sub = str.substring(i, i + n);
            if (unique.contains(sub)) {
                return true;
            }
            unique.add(sub);
        }
        return false;
    }
}
