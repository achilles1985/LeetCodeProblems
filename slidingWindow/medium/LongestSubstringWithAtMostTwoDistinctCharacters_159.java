package slidingWindow.medium;

import java.util.HashMap;
import java.util.Map;

/**M [marked]
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
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (map.size() > 2) {
                char cc = s.charAt(left);
                map.put(cc, map.get(cc) - 1);
                if (map.get(cc) == 0) {
                    map.remove(cc);
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}
