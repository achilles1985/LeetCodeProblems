package string.easy;

import java.util.HashMap;
import java.util.Map;

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
        for (int i = 0; i < order.length(); i++) {
            charToPosition.put(order.charAt(i), i);
        }
        for (int i = 1; i < words.length; i++) {
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
