package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**H
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Example:
 * Input:
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 *
 * Note:
 *     All inputs are consist of lowercase letters a-z.
 *     The values of words are distinct.
 */
/*
Optimization:
    1. Remove visited[m][n] completely by modifying board[i][j] = '*' directly.
    2. Use StringBuilder instead of s += bord[i][j].
    3. Remove HashSet and use word in a Trie to Deduplicate.
 */
public class WordSearch_II_212 {

    public static void main(String[] args) {
        WordSearch_II_212 s = new WordSearch_II_212();
        System.out.println(s.findWords(new char[][]{{'a','a'}}, new String[]{"aaa",})); //[]
        System.out.println(s.findWords(new char[][]{
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}},
                new String[]{"oath","pea","eat","rain","ren"})); //["eat","oath"]
        System.out.println(s.findWords(new char[][]{{'a','a'}}, new String[]{"a",})); //["a"]
    }

    // O(rows*columns*4^n) - time, n - rows*columns, O(rows*columns) - space
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || words == null) {
            return Collections.emptyList();
        }
        Trie trie = new Trie();
        for (String word: words) {
            trie.insert(word);
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWordsHelper(board, trie, i, j, new StringBuilder(), result);
            }
        }
        return result;
    }

    private void findWordsHelper(char[][] board, Trie trie, int row, int column, StringBuilder sb, List<String> results) {
        if (row < 0 || row > board.length-1 || column < 0 || column > board[0].length-1 || board[row][column] == '*') {
            return;
        }
        if (!trie.hasPrefix(sb.toString())) {
            sb.deleteCharAt(sb.length()-1);
            return;
        }
        sb.append(board[row][column]);

        Trie.TrieNode node = trie.isWord(sb.toString());
        if (node != null && node.word != null && node.word.equals(sb.toString())) {
            results.add(sb.toString());
            node.word = null;
            return;
        }

        char temp = board[row][column];
        board[row][column] = '*';

        // right
        findWordsHelper(board, trie, row, column+1, sb, results);
        // down
        findWordsHelper(board, trie, row+1, column, sb, results);
        // left
        findWordsHelper(board, trie, row, column-1, sb, results);
        // up
        findWordsHelper(board, trie, row-1, column, sb, results);

        board[row][column] = temp;
    }

    private static class Trie {
        TrieNode root = new TrieNode();

        private static class TrieNode {
            Map<Character, TrieNode> children = new HashMap<>();
            String word; // use String instead of isWord (boolean) to simplify deduplication
        }

        void insert(String word) {
            TrieNode current = root;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                if (!current.children.containsKey(word.charAt(i))) {
                    current.children.put(word.charAt(i), new TrieNode());
                }
                sb.append(word.charAt(i));
                current = current.children.get(word.charAt(i));
            }
            current.word = sb.toString();
        }

        boolean hasPrefix(String prefix) {
            TrieNode current = root;
            for (int i = 0; i < prefix.length(); i++) {
                if (!current.children.containsKey(prefix.charAt(i))) {
                    return false;
                }
                current = current.children.get(prefix.charAt(i));
            }
            return true;
        }

        TrieNode isWord(String prefix) {
            TrieNode current = root;
            for (int i = 0; i < prefix.length(); i++) {
                if (!current.children.containsKey(prefix.charAt(i))) {
                    return null;
                }
                current = current.children.get(prefix.charAt(i));
            }
            return current;
        }
    }
}
