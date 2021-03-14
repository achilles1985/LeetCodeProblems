package slidingWindow.medium;

import java.util.*;

/** M
 * Given a string s and an integer k, return the length of the longest substring of s such that
 * the frequency of each character in this substring is greater than or equal to k.
 *
 * Example 1:
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 *
 * Example 2:
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 * Constraints:
 *     1 <= s.length <= 104
 *     s consists of only lowercase English letters.
 *     1 <= k <= 105
 */
/*
    Questions: all english lowercase?
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters_395 {

    public static void main(String[] args) {
        LongestSubstringWithAtLeastKRepeatingCharacters_395 s = new LongestSubstringWithAtLeastKRepeatingCharacters_395();
        System.out.println(s.longestSubstring("aaabb", 3)); //3
        System.out.println(s.longestSubstring("ababbc", 2)); //5
    }

    // O(n^3) - time, O(1) - space
    public int longestSubstringBF(String s, int k) {
        if (s == null || s.isEmpty() || k > s.length()) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (valid(sub, k)) {
                    if (sub.length() > max) {
                        max = sub.length();
                    }
                }
            }
        }
        return max;
    }

    // O(n) - time, O(1) - space
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int[] countMap = new int[26];
        Set<Character> maxUnique = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            maxUnique.add(s.charAt(i));
        }

        int result = 0;
        for (int currUnique = 1; currUnique <= maxUnique.size(); currUnique++) {
            // reset countMap
            Arrays.fill(countMap, 0);
            int windowStart = 0, windowEnd = 0, idx = 0, unique = 0, countAtLeastK = 0;
            while (windowEnd < str.length) {
                // expand the sliding window
                if (unique <= currUnique) {
                    idx = str[windowEnd] - 'a';
                    if (countMap[idx] == 0) unique++;
                    countMap[idx]++;
                    if (countMap[idx] == k) countAtLeastK++;
                    windowEnd++;
                }
                // shrink the sliding window
                else {
                    idx = str[windowStart] - 'a';
                    if (countMap[idx] == k) countAtLeastK--;
                    countMap[idx]--;
                    if (countMap[idx] == 0) unique--;
                    windowStart++;
                }
                if (unique == currUnique && unique == countAtLeastK)
                    result = Math.max(windowEnd - windowStart, result);
            }
        }

        return result;
    }

    private boolean valid(String sub, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < sub.length(); i++) {
            char c = sub.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int value: map.values()) {
            if (value < k) {
                return false;
            }
        }
        return true;
    }
}
