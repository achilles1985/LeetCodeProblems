package string.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** E
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 *
 * Example 1:
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 *
 * Example 2:
 * Input: s = "a"
 * Output: 1
 *
 * Example 3:
 * Input: s = "bb"
 * Output: 2
 *
 * Constraints:
 *     1 <= s.length <= 2000
 *     s consists of lowercase and/or uppercase English letters only.
 */
public class LongestPalindrome_409 {

    public static void main(String[] args) {
        LongestPalindrome_409 s = new LongestPalindrome_409();
        System.out.println(s.longestPalindrome("aaaabbbbcccdddkkk")); //15
    }

    // O(n) - time
    public int longestPalindrome(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c: s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        int count = 0;
        int max = 0;
        for (int counter: freq.values()) {
            if (counter % 2 == 0) {
                count += counter;
            } else {
                count += counter - 1;
                max = Math.max(max, counter);
            }
        }
        return count + max%2;
    }

    public int longestPalindrome2(String s) {
        if (s == null || s.length() < 1) return 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(i));
            }
            else {
                set.add(s.charAt(i));
            }
        }
        if (set.size() <= 1) {
            return s.length();
        }
        return s.length() - set.size() + 1;
    }
}
