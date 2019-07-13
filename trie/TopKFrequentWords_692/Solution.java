package trie.TopKFrequentWords_692;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/** M
 Given a non-empty list of words, return the k most frequent elements.
 Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

 Example 1:
 Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 Output: ["i", "love"]
 Explanation: "i" and "love" are the two most frequent words.
 Note that "i" comes before "love" due to a lower alphabetical order.

 Example 2:
 Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 Output: ["the", "is", "sunny", "day"]
 Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 with the number of occurrence being 4, 3, 2 and 1 respectively.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Input words contain only lowercase letters.

 Follow up:
 Try to solve it in O(n log k) time and O(n) extra space.
 */
public class Solution {

    // O(n*log(k)) - time, O(2*n) - space for queue and map or just O(n)
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<WordFrequency> queue = new PriorityQueue<>(Comparator.comparing(WordFrequency::getCount).reversed().thenComparing(WordFrequency::getWord));
        for (String key: map.keySet()) {
            WordFrequency wordFrequency = new WordFrequency(key, map.get(key));
            queue.add(wordFrequency);
        }

        List<String> list = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            list.add(queue.poll().word);
        }
        Collections.reverse(list);

        return list;
    }

    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> queue = new PriorityQueue<>((word1, word2) ->
                map.get(word1) == map.get(word2) ? word1.compareTo(word2) : map.get(word1) - map.get(word2));
        for (String key: map.keySet()) {
            queue.add(key);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        List<String> list = new ArrayList<>(k);
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }

        return list;
    }

    private class WordFrequency {
        private String word;
        private int count;

        public WordFrequency(String word, int count) {
            this.word = word;
            this.count = count;
        }

        public String getWord() {
            return word;
        }

        public int getCount() {
            return count;
        }
    }
}
