package heap.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

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
public class TopKFrequentWords_692 {

    public static void main(String[] args) {
        TopKFrequentWords_692 s = new TopKFrequentWords_692();
        int res = "i".compareTo("love");
        String[] str = new String[] {"love", "i", "code", "leet"};
        Arrays.sort(str);
        System.out.println(s.topKFrequent(new String[] {"i", "love", "leetcode", "i", "love", "coding"}, 2)); // [i, love]
        System.out.println(s.topKFrequent(new String[] {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4)); // ["the", "is", "sunny", "day"]
    }

    // BF O(n*log(n)) - time, frequence map + sorting

    // O(n*log(k)) - time, O(n) - space
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        Queue<String> heap = new PriorityQueue<>((w1, w2) -> map.get(w1) == map.get(w2) ? w2.compareTo(w1) : map.get(w1) - map.get(w2));
        for (String key: map.keySet()) {
            heap.add(key);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<String> list = new ArrayList<>();
        while (!heap.isEmpty()) {
            list.add(heap.poll());
        }
        Collections.reverse(list);
        return list;
    }
}
