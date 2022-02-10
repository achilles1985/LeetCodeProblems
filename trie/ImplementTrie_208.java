package trie;

import java.util.HashMap;
import java.util.Map;

public class ImplementTrie_208 {

    private TrieNode root;

    /** Initialize your data structure here. */
    public ImplementTrie_208() {
        root = new TrieNode();
    }

    // O(k) - k - length of the word
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            if (!current.children.containsKey(word.charAt(i))) {
                current.children.put(word.charAt(i), new TrieNode());
            }
            current = current.children.get(word.charAt(i));
        }
        current.isComplete = true;
    }

    public void insertRecursively(String word) {
        insertRecursively(root, word, 0);
    }

    public void insertRecursively(TrieNode current, String word, int index) {
        if (index == word.length()) {
            current.isComplete = true;
            return;
        }
        TrieNode node = current.children.get(word.charAt(index));
        if (node == null) {
            node = new TrieNode();
            current.children.put(word.charAt(index), node);
        }
        insertRecursively(node, word, index+1);
    }

    // O(k) - k - length of the word
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return current.isComplete;
    }

    // O(k) - k - length of the prefix
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return true;
    }

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isComplete;
    }

    public static void main(String[] args) {
        ImplementTrie_208 trie = new ImplementTrie_208();
        trie.insertRecursively("apple");
        System.out.println(trie.search("apple")); //true
        System.out.println(trie.search("app")); //false
        System.out.println(trie.startsWith("app")); //true
        trie.insert("app");
        System.out.println(trie.search("app")); //true
    }
}
