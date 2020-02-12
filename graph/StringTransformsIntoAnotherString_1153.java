package graph;

import java.util.HashSet;
import java.util.Set;

/**H
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing
 * zero or more conversions.
 *
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 *
 * Return true if and only if you can transform str1 into str2.
 *
 * Example 1:
 * Input: str1 = "aabcc", str2 = "ccdee"
 * Output: true
 * Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
 *
 * Example 2:
 * Input: str1 = "leetcode", str2 = "codeleet"
 * Output: false
 * Explanation: There is no way to transform str1 to str2.
 *
 * Note:
 *     1 <= str1.length == str2.length <= 10^4
 *     Both str1 and str2 contain only lowercase English letters.
 */
public class StringTransformsIntoAnotherString_1153 {

    public static void main(String[] args) {
        StringTransformsIntoAnotherString_1153 s = new StringTransformsIntoAnotherString_1153();
        System.out.println(s.canConvert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyza")); // false
        System.out.println(s.canConvert("aabcc", "ccdee")); // true
        System.out.println(s.canConvert("leetcode", "codeleet")); // false
    }

    public boolean canConvert(String str1, String str2) {
        int[] groups = new int[26];
        markGroups(str1, groups);
        unmarkGroups(str2, groups);
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private void markGroups(String str, int[] groups) {
        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            if (!visited.contains(letter)) {
                for (int j = i; j < str.length(); j++) {
                    if (str.charAt(j) == letter) {
                        groups[i]++;
                    }
                }
                visited.add(letter);
            }
        }
    }

    private void unmarkGroups(String str, int[] groups) {
        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            if (!visited.contains(letter)) {
                for (int j = i; j < str.length(); j++) {
                    if (str.charAt(j) == letter) {
                        groups[i]--;
                    }
                }
                visited.add(letter);
            }
        }
    }
}
