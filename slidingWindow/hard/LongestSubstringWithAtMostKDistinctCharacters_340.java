package slidingWindow.hard;

import java.util.HashMap;
import java.util.Map;

/**H
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
        if (k <= 0) {
            return 0;
        }
        if (k >= s.length()) {
            return s.length();
        }
        Map<Character, Integer> charToOccurrence = new HashMap<>();
        int left = 0, right = 0;
        int maxLen = 0;
        while (right < s.length()) {
            Character c = s.charAt(right);
            if (!charToOccurrence.containsKey(c)) {
                charToOccurrence.put(c, 1);
            } else {
                charToOccurrence.put(c, charToOccurrence.get(c) + 1);
            }
            if (charToOccurrence.size() > k) {
                // Remove characters from string until constraint is met
                while (charToOccurrence.size() > k && left < right) {
                    Character startChar = s.charAt(left);
                    if (charToOccurrence.get(startChar) == 1) {
                        charToOccurrence.remove(startChar);
                    } else {
                        charToOccurrence.put(startChar, charToOccurrence.get(startChar) - 1);
                    }
                    left++;
                }
            }
            if ((right - left + 1) > maxLen) {
                maxLen = (right - left + 1);
            }
            right++;
        }
        return maxLen;
    }
}
