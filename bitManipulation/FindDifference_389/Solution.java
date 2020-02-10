package bitManipulation.FindDifference_389;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**M
 * Given two strings s and t which consist of only lowercase letters.
 *
 * String t is generated by random shuffling string s and then add one more letter at a random position.
 *
 * Find the letter that was added in t.
 *
 * Example:
 *
 * Input:
 * s = "abcd"
 * t = "abcde"
 *
 * Output:
 * e
 *
 * Explanation:
 * 'e' is the letter that was added.
 */
public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.findTheDifference("abcd", "abcde")); // e
        System.out.println(s.findTheDifference("aaaaa", "aaafaa")); // f
        System.out.println(s.findTheDifference("zzaaaa", "zzbaaaa")); // b
    }

    // O(n + m) - time, O(1) - space
    public char findTheDifference(String s, String t) {
        int ans = 0;
        for (char c: s.toCharArray()) {
            ans ^= c;
        }

        for (char c: t.toCharArray()) {
            ans ^= c;
        }

        return (char) ans;
    }
}
