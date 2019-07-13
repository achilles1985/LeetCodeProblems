package utils;

public class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            if (!current.containsKey(word.charAt(i))) {
                current.put(word.charAt(i));
            }
            current = current.get(word.charAt(i));
        }
        current.setEnd(true);
    }

    public TrieNode searchPrefix(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!current.containsKey(prefix.charAt(i))) {
                return null;
            }
            current = current.get(prefix.charAt(i));
        }

        return current;
    }

    public boolean exists(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            if (!current.containsKey(word.charAt(i))) {
                return false;
            }
            current = current.get(word.charAt(i));
        }

        return current != null && current.isEnd;
    }

    public String longestCommonPrefix(String word) {
        TrieNode current = root;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (current != null && current.size == 1 && !current.isEnd) {
                sb.append(word.charAt(i));
                current = current.get(word.charAt(i));
            }
        }
        return sb.toString().isEmpty() ? "" : sb.toString();
    }

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
        // number of children
        int size;

        public void put(char c) {
            children[c - 'a'] = new TrieNode();
            size++;
        }

        public boolean containsKey(char c) {
            return children[c - 'a'] != null;
        }

        public TrieNode get(char c) {
            return children[c - 'a'];
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }
    }

    public static void main(String[] args) {
        //System.out.println(trie.longestCommonPrefix(new String[] {"flower","flow","flight"}));
        //System.out.println(trie.longestCommonPrefix(new String[] {"dog","racecar","car"}));
        //System.out.println(trie.longestCommonPrefix(new String[] {"b"}));
        Trie trie = new Trie();
        trie.insert("word");
        trie.insert("root");
        trie.insert("wood");

        System.out.println(trie.exists("word"));
        System.out.println(trie.exists("hello"));
        System.out.println(trie.exists("wood"));
        System.out.println(trie.searchPrefix("wo"));
        System.out.println(trie.searchPrefix("wooda"));
    }
}
