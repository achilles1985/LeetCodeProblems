package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Keeps track of all prefixes of a word inserted
 */
public class PrefixTrie {

    private PrefixTrieNode root;

    public PrefixTrie() {
        this.root = new PrefixTrieNode("");
    }

    public static class PrefixTrieNode {
        String prefix;
        Map<Character, PrefixTrieNode> children = new HashMap<>();
        boolean isCompleteWord;
        boolean isVisited;

        public PrefixTrieNode(String prefix) {
            this.prefix = prefix;
        }

    }
    public void insert(String word) {
        PrefixTrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            if (!current.children.containsKey(word.charAt(i))) {
                current.children.put(word.charAt(i), new PrefixTrieNode(word.substring(0, i+1)));
            }
            current = current.children.get(word.charAt(i));
        }
        current.isCompleteWord = true;
    }

    public List<String> findAllWordsFor(String prefix) {
        PrefixTrieNode prefixNode = findPrefixNode(prefix);
        if (prefix == null) {
            return new ArrayList<>();
        }

        List<String> words = new ArrayList<>();
        Stack<PrefixTrieNode> stack = new Stack<>();
        stack.push(prefixNode);
        while (!stack.isEmpty()) {
            PrefixTrieNode node = stack.pop();
            if (node.isCompleteWord) {
                words.add(node.prefix);
            }
            for (PrefixTrieNode child: node.children.values()) {
                if (!child.isVisited) {
                    stack.push(child);
                    child.isVisited = true;
                }
            }
        }

        return words;
    }

    private PrefixTrieNode findPrefixNode(String prefix) {
        PrefixTrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!current.children.containsKey(prefix.charAt(i))) {
                return null;
            }
            current = current.children.get(prefix.charAt(i));
        }
        return current;
    }
}
