package string.easy;

import java.util.HashMap;
import java.util.Map;

/** E
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only
 * if the given words are sorted lexicographicaly in this alien language.
 *
 * Example 1:
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 *
 * Example 2:
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 *
 * Example 3:
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.)
 * According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅'
 * is defined as the blank character which is less than any other character (More info).
 *
 * Constraints:
 *     1 <= words.length <= 100
 *     1 <= words[i].length <= 20
 *     order.length == 26
 *     All characters in words[i] and order are English lowercase letters.
 */
public class VerifyingAlienDictionary_953 {

    public static void main(String[] args) {
        VerifyingAlienDictionary_953 s = new VerifyingAlienDictionary_953();
        System.out.println(s.isAlienSorted(new String[]{"hello","leetcode"}, "hlabcdefgijkmnopqrstuvwxyz")); //true
        System.out.println(s.isAlienSorted(new String[]{"word","world","row"}, "worldabcefghijkmnpqstuvxyz")); //false
    }

    // O(words) - time, O(1) - space since map length == 26
    public boolean isAlienSorted(String[] words, String order) {
        if (words.length == 1) {
            return true;
        }
        Map<Character, Integer> charToPosition = new HashMap<>();
        for (int i = 0; i < order.length(); i++) { // O(1)
            charToPosition.put(order.charAt(i), i);
        }
        for (int i = 1; i < words.length; i++) { // O(words)
            if (!areEqual(words[i-1], words[i], charToPosition)) {
                return false;
            }
        }
        return true;
    }

    private boolean areEqual(String w1, String w2, Map<Character, Integer> map) {
        int i = 0, j = 0;
        while (i < w1.length() && j < w2.length()) {
            char c1 = w1.charAt(i);
            char c2 = w2.charAt(j);
            if (map.get(c1) > map.get(c2)) {
                return false;
            } else if (map.get(c1) < map.get(c2)) {
                return true;
            }
            i++;
            j++;
        }
        if (i < w1.length()) {
            return false;
        }
        return true;
    }
}
