package string.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** E
 Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.
 It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
 Words in the list of banned words are given in lowercase, and free of punctuation.
 Words in the paragraph are not case sensitive.  The answer is in lowercase.

 Example:
 Input:
 paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 banned = ["hit"]
 Output: "ball"
 Explanation:
 "hit" occurs 3 times, but it is a banned word.
 "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 Note that words in the paragraph are not case sensitive,
 that punctuation is ignored (even if adjacent to words, such as "ball,"),
 and that "hit" isn't the answer even though it occurs more because it is banned.

 Note:
 1 <= paragraph.length <= 1000.
 0 <= banned.length <= 100.
 1 <= banned[i].length <= 10.
 The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
 paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 There are no hyphens or hyphenated words.
 Words only consist of letters, never apostrophes or other punctuation symbols.
 */
/*
    \\W+ - non-word character
    \\S+ - non-white space character

    \\w+ - word character (a-zA-Z_0-9)
    \\s+ - white space character (\t\n\x0B\f\r)
 */
public class MostCommonWord_819 {

    public static void main(String[] args) {
        MostCommonWord_819 s = new MostCommonWord_819();
        System.out.println(s.mostCommonWord2("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"})); // ball
        System.out.println(s.mostCommonWord2("Bob", new String[]{})); //
    }

    // O(n+m) - time, space; n - number of words in paragraph, m - number of banned words
    public String mostCommonWord2(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.isEmpty()) {
            return "";
        }
        Set<String> bannedWords = new HashSet<>();
        for (String word: banned) {
            bannedWords.add(word);
        }
        final String[] words = paragraph.split("\\W+");
        Map<String, Integer> frequency = new HashMap<>();
        for (String word: words) {
            frequency.put(word.toLowerCase(), frequency.getOrDefault(word.toLowerCase(), 1) + 1);
        }
        String result = "";
        int count = 0;
        for (Map.Entry<String, Integer> entry: frequency.entrySet()) {
            if (entry.getValue() > count && !bannedWords.contains(entry.getKey())) {
                count = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }

    // O(n + m) - time, O(n+m) - space, n - number of chars in paragraph, m - number of banned
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) {
            return "";
        }
        paragraph += ".";
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (String word: banned) {
            set.add(word.toLowerCase());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paragraph.length(); i++) {
            char symbol = paragraph.charAt(i);
            if (Character.isAlphabetic(symbol)) {
                sb.append(Character.toLowerCase(symbol));
            } else {
                if (!sb.toString().isEmpty()) {
                    map.put(sb.toString(), map.getOrDefault(sb.toString(), 1) + 1);
                    sb = new StringBuilder();
                }
            }
        }
        int maxCount = 0;
        String ans = "";
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            if (!set.contains(entry.getKey())) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    ans = entry.getKey();
                }
            }
        }

        return ans;
    }
}
