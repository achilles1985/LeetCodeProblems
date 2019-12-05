package trie.autocomplete;

import trie.PrefixTrie;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a dictionary, find all words with the given prefix
 */
public class Solution {

    // O(m) - insertion for both time and space complexity, where m - key length
    // O(n) - search prefix, where n- length of the prefix
    // O()
    public List<String> autocomplete(String[] words, String prefix) {
        if (words == null) {
            return new ArrayList<>();
        }

        PrefixTrie trie = new PrefixTrie();
        for (String word: words) {
            trie.insert(word);
        }

        List<String> result = trie.findAllWordsFor(prefix);

        return result;
    }
}
