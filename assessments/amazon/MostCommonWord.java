package assessments.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MostCommonWord {

    public static void main(String[] args) {
        MostCommonWord s = new MostCommonWord();
        System.out.println(s.mostFrequent("Rose ! , is a flower? red rose   are flower.   bob, bob, aka, aka", Arrays.asList("is", "are", "a")));
        System.out.println(s.mostFrequent("Rose is a flower red rose are flower", Arrays.asList("is", "are", "a")));
    }

    // O(n*log(n)) - time, O(n) - space
    public List<String> mostFrequent(String helpText, List<String> wordsToExclude) {
        if (helpText == null || helpText.isEmpty()) {
            return new ArrayList<>();
        }

        Set<String> excludes = new HashSet<>(wordsToExclude);
        String[] words = helpText.split("\\W+");
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words) {
            if (!excludes.contains(word.toLowerCase())) {
                frequency.put(word.toLowerCase(), frequency.getOrDefault(word.toLowerCase(), 0) + 1);
            }
        }

        List<Item> items = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            Item item = new Item(entry.getKey(), entry.getValue());
            items.add(item);
        }
        Collections.sort(items, (e1, e2) -> e1.frequency == e2.frequency ? e1.word.compareTo(e2.word) : e2.frequency - e1.frequency);

        List<String> result = new ArrayList<>();
        result.add(items.get(0).word);
        int left = 1;
        while (items.get(0).frequency == items.get(left).frequency) {
            result.add(items.get(left).word);
            left++;
        }

        return result;
    }

    private static class Item implements Comparable<Item> {
        String word;
        int frequency;

        Item(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Item other) {
            return this.frequency == other.frequency ? this.word.compareTo(other.word) : other.frequency - this.frequency;
        }
    }
}
