package design;

public class WordDictionary {

    private DictionaryNode root;

    private static class DictionaryNode {
        private DictionaryNode[] children = new DictionaryNode[26];
        private boolean isWord;
    }

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new DictionaryNode();
    }

    // O(m) - time, m - word length
    public void addWord(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        DictionaryNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            if (curr.children[word.charAt(i) - 'a'] == null) {
                curr.children[word.charAt(i) - 'a'] = new DictionaryNode();
            }
            curr = curr.children[word.charAt(i) - 'a'];
        }
        curr.isWord = true;
    }

    // O(m*24) - time?
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        return search(word, 0, root);
    }

    private boolean search(String word, int index, DictionaryNode root) {
        if (index == word.length()) {
            return root.isWord;
        }
        if (word.charAt(index) != '.') {
            return root.children[word.charAt(index)-'a'] != null && search(word, index + 1, root.children[word.charAt(index)-'a']);
        }
        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null) {
                if (search(word, index + 1, root.children[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordDictionary s = new WordDictionary();
        s.addWord("a");
        s.addWord("ab");
        s.addWord("motor");
        System.out.println(s.search(".....")); //true
        System.out.println(s.search("a")); //true
        System.out.println(s.search("a.")); //true
        System.out.println(s.search("ab")); //true
        System.out.println(s.search(".a")); //true
        System.out.println(s.search(".b")); //true
        System.out.println(s.search("ab.")); //true
        System.out.println(s.search(".")); //true
        System.out.println(s.search("..")); //true
    }
}
