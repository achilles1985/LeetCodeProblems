package graph.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    //O(n) - time, space
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }
        if (getFrequency(str2) >= 26) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        for (int i = str1.length() - 1; i >= 0; i--) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if (map.containsKey(c1) && map.get(c1) != c2) {
                return false;
            }
            map.put(c1, c2);
        }
        return true;
    }

    private int getFrequency(String str) {
        Set<Character> distinct = new HashSet<>();
        for (final char c : str.toCharArray()) {
            distinct.add(c);
        }
        return distinct.size();
    }
}
