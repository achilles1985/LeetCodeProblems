package string.MostCommonWord_819;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** Easy
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
public class Solution {

    // O(n + m) - time, where n is size of paragraph and m is size of banner, at least O(m) - space
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        for (String word: banned) {
            set.add(word.toLowerCase());
        }
        Map<String, Integer> map = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        String answer = "";
        int counter = 0;
        for (int i = 0; i < paragraph.length(); i++) {
            if (Character.isAlphabetic(paragraph.charAt(i))) {
                builder.append(paragraph.charAt(i));
            } else {
                String word = builder.toString().toLowerCase();
                builder = new StringBuilder();
                if (!set.contains(word)) {
                    map.put(word, map.getOrDefault(word, 0) + 1);
                    if (map.get(word) > counter) {
                        counter = map.get(word);
                        answer = word;
                    }
                }
            }
        }

        return answer;
    }

}
