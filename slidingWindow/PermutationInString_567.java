package slidingWindow;

import java.util.Arrays;

/** M
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
 * In other words, one of the first string's permutations is the substring of the second string.
 *
 * Example 1:
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 *
 * Example 2:
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 *
 * Constraints:
 *     The input strings only contain lower case letters.
 *     The length of both given strings is in range [1, 10,000].
 */
/*
    Questions:
    1. What if pattern is empty?
    2. Only letters lowercase?
 */
public class PermutationInString_567 {

    public static void main(String[] args) {
        PermutationInString_567 s = new PermutationInString_567();
        System.out.println(s.checkInclusion("ab", "eidbaooo")); //true
        System.out.println(s.checkInclusion("ab", "eidboaoo")); //false
    }

    // O(n + m) - time, O(1) - space (sliding window with rehashing)
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()) {
            return false;
        }
        int s1L = s1.length();
        int[] s1Arr = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1Arr[s1.charAt(i) - 'a']++;
        }
        int[] s2Arr = new int[26];
        for (int i = 0; i < s2.length(); i++) {
            s2Arr[s2.charAt(i) - 'a']++;
            if (i >= s1L) {
                s2Arr[s2.charAt(i - s1L) - 'a']--;
            }
            if (Arrays.equals(s1Arr, s2Arr)) {
                return true;
            }
        }
        return false;
    }
}
