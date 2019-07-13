package string.UniqueMorseCodeWords_804;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    // O(n*m)
    public int uniqueMorseRepresentations(String[] words) {
        String[] MORSE = new String[]{
                ".-",
                "-...",
                "-.-.",
                "-..",
                ".",
                "..-.",
                "--.",
                "....",
                "..",
                ".---",
                "-.-",
                ".-..",
                "--",
                "-.",
                "---",
                ".--.",
                "--.-",
                ".-.",
                "...",
                "-",
                "..-",
                "...-",
                ".--",
                "-..-",
                "-.--",
                "--.."};
        Set<String> set = new HashSet<>();
        for (String word: words) {
            char[] chars = word.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                builder.append(MORSE[chars[i] - 'a']);
            }
            set.add(builder.toString());
        }
        return set.size();
    }
}
