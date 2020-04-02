package dynamic;

import java.util.*;

/**
 * H
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 * <p>
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * <p>
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * <p>
 * Note:
 * The number of elements of the given array will not exceed 10,000
 * The length sum of elements in the given array will not exceed 600,000.
 * All the input string will only include lower case letters.
 * The returned elements order does not matter.
 */
public class ConcatenatedWords_472 {

    public static void main(String[] args) {
        ConcatenatedWords_472 s = new ConcatenatedWords_472();
        System.out.println(s.findAllConcatenatedWordsInADict(new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"})); //["catsdogcats","dogcatsdog","ratcatdogcat"]
            System.out.println(s.findAllConcatenatedWordsInADictTopDown(new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"})); //["catsdogcats","dogcatsdog","ratcatdogcat"]
    }

    // O(n*m^m) - time, m - word length, n - number of words, O(m^m) - space
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> dictionary = new HashSet<>(Arrays.asList(words));
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (canBeConcatenated(word, word, dictionary)) {
                result.add(word);
            }
        }
        return result;
    }

    private boolean canBeConcatenated(String word, String origin, Set<String> dictionary) {
        if (dictionary.contains(word) && !word.equals(origin)) {
            return true;
        }
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            if (dictionary.contains(prefix) && canBeConcatenated(word.substring(i), origin, dictionary)) {
                return true;
            }
        }
        return false;
    }

    // O(n*m^2) - time, O(m^2) - space
    public List<String> findAllConcatenatedWordsInADictTopDown(String[] words) {
        Set<String> dictionary = new HashSet<>(Arrays.asList(words));
        Map<String, Boolean> cache = new HashMap<>();
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (canBeConcatenatedTopDown(word, word, dictionary, cache)) {
                result.add(word);
            }
        }
        return result;
    }

    private boolean canBeConcatenatedTopDown(String word, String origin, Set<String> dictionary, Map<String, Boolean> cache) {
        if (cache.containsKey(word)) {
            return cache.get(word);
        }
        if (dictionary.contains(word) && !word.equals(origin)) {
            return true;
        }
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);
            if (dictionary.contains(prefix)) {
                if (dictionary.contains(suffix) || canBeConcatenatedTopDown(suffix, origin, dictionary, cache)) {
                    cache.put(word, true);
                    return true;
                }
            }
        }
        cache.put(word, false);
        return false;
    }
}
