package slidingWindow.medium;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**M
 * Given a string s, find the length of the longest substring t that contains at most 2 distinct characters.
 *
 * Example 1:
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 *
 * Example 2:
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 */

public class LongestSubstringWithAtMostTwoDistinctCharacters_159 {

    public static void main(String[] args) {
        LongestSubstringWithAtMostTwoDistinctCharacters_159 s = new LongestSubstringWithAtMostTwoDistinctCharacters_159();
        System.out.println(s.lengthOfLongestSubstringTwoDistinct("ccaabbb")); //5
        System.out.println(s.lengthOfLongestSubstringTwoDistinct("eceba")); //3
        System.out.println(s.lengthOfLongestSubstringTwoDistinct("leeetcooooode")); //6
    }

    // O(n^2) - time, O(n) - space
    public int lengthOfLongestSubstringTwoDistinctBF(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Map<Character, Integer> map = new HashMap<>();
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (map.size() == 2 && !map.containsKey(c)) {
                    break;
                }
                map.put(c, map.getOrDefault(c, 0) + 1);
                max = Math.max(max, j - i + 1);
            }
        }
        return max;
    }

    // O(n) - time, O(1) - space (since map has map size of 3)
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);
            while (map.size() > 2) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }

    // O(n) - time, O(1) - space since map has map size of 3.
    public int lengthOfLongestSubstringTwoDistinct2(String s) {
        Map<Character, Integer> charToLastIndex = new HashMap<>();
        int left = 0;
        int right = 0;
        int max = 0;
        while (right < s.length()) {
            if (charToLastIndex.size() <= 2) {
                charToLastIndex.put(s.charAt(right), right++);
            }
            if (charToLastIndex.size() == 3) {
                int idxToRemove = Collections.min(charToLastIndex.values());
                charToLastIndex.remove(s.charAt(idxToRemove));
                left = idxToRemove + 1;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }

}
