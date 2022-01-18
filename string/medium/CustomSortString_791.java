package string.medium;

import java.util.HashMap;
import java.util.Map;

/** M
 * You are given two strings order and s. All the words of order are unique and were sorted in some custom order previously.
 * Permute the characters of s so that they match the order that order was sorted. More specifically,
 * if a character x occurs before a character y in order, then x should occur before y in the permuted string.
 * Return any permutation of s that satisfies this property.
 *
 * Example 1:
 * Input: order = "cba", s = "abcd"
 * Output: "cbad"
 * Explanation:
 * "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
 * Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.
 *
 * Example 2:
 * Input: order = "cbafg", s = "abcd"
 * Output: "cbad"
 *
 * Constraints:
 *     1 <= order.length <= 26
 *     1 <= s.length <= 200
 *     order and s consist of lowercase English letters.
 *     All the characters of order are unique.
 */
public class CustomSortString_791 {

    public static void main(String[] args) {
        CustomSortString_791 s = new CustomSortString_791();
        System.out.println(s.customSortString("cba", "abcd")); //cbad
        System.out.println(s.customSortString("cbafg", "abcd")); //cbad
    }

    public String customSortString(String order, String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            if (map.containsKey(c)) {
                int size = map.get(c);
                while (size-- > 0) {
                    sb.append(c);
                }
                map.remove(c);
            }
        }
        for (Character c: map.keySet()) {
            int size = map.get(c);
            while(size-- > 0) {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
