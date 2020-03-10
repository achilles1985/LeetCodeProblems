package slidingWindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**M
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
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
        System.out.println(s.lengthOfLongestSubstringTwoDistinct("eceba")); //3
        System.out.println(s.lengthOfLongestSubstringTwoDistinct("ccaabbb")); //5
        System.out.println(s.lengthOfLongestSubstringTwoDistinct("leeetcooooode")); //6
    }

    // O(n) - time, O(1) - space since map has mex size of 3.
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> charCounter = new HashMap<>();
        int left = 0;
        int right = 0;
        int max = 0;
        while (right < s.length()) {
            if (charCounter.size() < 3) {
                charCounter.put(s.charAt(right), right++);
            }
            if (charCounter.size() == 3) {
                int idxToRemove = Collections.min(charCounter.values());
                charCounter.remove(s.charAt(idxToRemove));
                left = idxToRemove + 1;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }
}
