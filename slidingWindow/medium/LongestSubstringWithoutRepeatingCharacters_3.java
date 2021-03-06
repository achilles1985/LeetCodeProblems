package slidingWindow.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** M
 Given a string, find the length of the longest substring without repeating characters.

 Example 1:
 Input: "abcabcbb"
 Output: 3
 Explanation: The answer is "abc", with the length of 3.

 Example 2:
 Input: "bbbbb"
 Output: 1
 Explanation: The answer is "b", with the length of 1.

 Example 3:
 Input: "pwwkew"
 Output: 3
 Explanation: The answer is "wke", with the length of 3.
 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
/*
We have left and right pointers. Move forward right pointer till set contains its char.
If set contains char at s[right], move left pointer ahead and remote it from set.
 */
public class LongestSubstringWithoutRepeatingCharacters_3 {

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters_3 s = new LongestSubstringWithoutRepeatingCharacters_3();

        System.out.println(s.lengthOfLongestSubstring4("abba")); //2
        System.out.println(s.lengthOfLongestSubstring4("abcabcbb")); //3

        System.out.println(s.lengthOfLongestSubstring("pwwkeww")); //3
        System.out.println(s.lengthOfLongestSubstring("dvdf")); //3
        System.out.println(s.lengthOfLongestSubstring("au")); //2
        System.out.println(s.lengthOfLongestSubstring("abcabcbb")); //3
        System.out.println(s.lengthOfLongestSubstringBF("bbbbb")); //1
        System.out.println(s.lengthOfLongestSubstringBF("pwwkew")); //3
    }

    // O(n) - time, O(unique chars) - space
    public int lengthOfLongestSubstring4(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                j = Math.max(map.get(c) + 1, j);
            }
            max = Math.max(max, i - j + 1);
            map.put(c, i);
        }
        return max;
    }

    // O(2n) - time, O(unique chars) - space
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    // O(n^2) - time, O(n) - space (if limited set of chars, then O(1))
    public int lengthOfLongestSubstringBF2(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (set.contains(c)) {
                    set.remove(c);
                    break;
                }
                set.add(c);
                max = Math.max(max, j - i + 1);
            }
        }

        return max;
    }

    // O(n^3) - time, O(min(n,m)) - space
    public int lengthOfLongestSubstringBF(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                if (allUnique(i, j, s)) {
                    ans = Math.max(ans, j-i);
                }
            }
        }

        return ans;
    }

    private boolean allUnique(int start, int end, String s) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            if (set.contains(s.charAt(i))) {
                return false;
            }
            set.add(s.charAt(i));
        }
        return true;
    }

}
