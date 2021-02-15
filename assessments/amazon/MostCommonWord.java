package assessments.amazon;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MostCommonWord {

    public static void main(String[] args) {
        MostCommonWord s = new MostCommonWord();
        System.out.println(s.mostCommonWord("Rose, rose ! , is a flower? red rose   are flower.   bob, bob, aka, aka", Arrays.asList("is", "are", "a")));
        System.out.println(s.mostCommonWord("Rose is a flower red rose are flower", Arrays.asList("is", "are", "a")));
    }

    // O(n+m) - time, space
    public String mostCommonWord(String paragraph, List<String> banned) {
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
            if (!bannedWords.contains(word)) {
                frequency.put(word.toLowerCase(), frequency.getOrDefault(word.toLowerCase(), 1) + 1);
            }
        }
        Map.Entry<String, Integer> entry = Collections.max(frequency.entrySet(), Comparator.comparing(Map.Entry::getValue));

        return entry.getKey();
    }
}
