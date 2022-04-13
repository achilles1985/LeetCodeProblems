package slidingWindow.hard;

import java.util.HashMap;
import java.util.Map;

/**M [marked]
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 *
 * Example 1:
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 * Example 2:
 *
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 */
public class LongestSubstringWithAtMostKDistinctCharacters_340 {

    public static void main(String[] args) {
        LongestSubstringWithAtMostKDistinctCharacters_340 s = new LongestSubstringWithAtMostKDistinctCharacters_340();
        System.out.println(s.lengthOfLongestSubstringKDistinct("eceba", 2)); //3
        System.out.println(s.lengthOfLongestSubstringKDistinct("a", 1)); //2
    }

    // O(n) - time, O(k) - space
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.isEmpty() || k <= 0) {
            return 0;
        }
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c,0) + 1);
            while (map.size() > k) {
                char cc = s.charAt(left++);
                map.put(cc, map.get(cc) - 1);
                if (map.get(cc) == 0) {
                    map.remove(cc);
                }
            }
            max = Math.max(max, right-left+1);
        }

        return max;
    }
}
