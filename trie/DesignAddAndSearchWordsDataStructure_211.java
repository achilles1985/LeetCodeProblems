package trie;

import java.util.HashMap;
import java.util.Map;

/**
 * M
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 * Implement the WordDictionary class:
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or
 * false otherwise. word may contain dots '.' where dots can be matched with any letter.
 * <p>
 * Example:
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 * <p>
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 * <p>
 * <p>
 * Constraints:
 * 1 <= word.length <= 500
 * word in addWord consists lower-case English letters.
 * word in search consist of  '.' or lower-case English letters.
 * At most 50000 calls will be made to addWord and search.
 */
public class DesignAddAndSearchWordsDataStructure_211 {

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public DesignAddAndSearchWordsDataStructure_211() {
        root = new TrieNode();
    }

    // O(M) - time, space, where m - word length
    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        curr.isWord = true;
    }

    // O(M) for the "well-defined" words without dots, where M is the key length,
    // and N is a number of keys, and O(M*N) for the "undefined" words if there are dots '.', O(1) - space
    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return search(word, root);
    }

    private boolean search(String word, TrieNode node) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.children.containsKey(c)) {
                if (c == '.') {
                    for (char key : node.children.keySet()) {
                        TrieNode child = node.children.get(key);
                        if (search(word.substring(i + 1), child)) {
                            return true;
                        }
                    }
                }
                return false;
            } else {
                node = node.children.get(c);
            }
        }
        return node.isWord;
    }

    private static class TrieNode {
        private Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord;
    }

    public static void main(String[] args) {

    }
}
