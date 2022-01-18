package backtracking.medium;

import java.util.HashMap;
import java.util.Map;

/** M [marked]
 * Given a 2D board and a word, find if the word exists in the grid.
 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
 The same letter cell may not be used more than once.

 Example:
 board =
 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]

 Given word = "ABCCED", return true.
 Given word = "SEE", return true.
 Given word = "ABCB", return false.
 */

// https://discuss.leetcode.com/topic/7907/accepted-very-short-java-solution-no-additional-space/14
// Time complexity k=m*n O(k^2) - because for each cell  we can do whole board traversal.
// Space complexity O(1).
public class WordSearch_79 {

    public static void main(String[] args) {
        WordSearch_79 s = new WordSearch_79();
        System.out.println(s.existsTrie(new char[][] {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}}, "ABCCED")); // true
        System.out.println(s.existsTrie(new char[][] {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}}, "ABCB")); // false

        System.out.println(s.existsTrie(new char[][] {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "SEE")); // true
        System.out.println(s.existsTrie(new char[][] {{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}}, "AAB")); // true
        System.out.println(s.existsTrie(new char[][] {{'A', 'A'}}, "AAA")); // false
    }

    // O(row*col*4^L) - time, O(L) - space, L - word length
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (findPath(i, j, word, board, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findPath(int i, int j, String word, char[][] board, int counter) {
        if (word.length() == counter) {
            return true;
        }
        if (i < 0 || i > board.length-1 || j < 0 || j > board[0].length-1 || word.charAt(counter) != board[i][j]) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = '*';
        boolean right = findPath(i, j+1, word, board, counter+1);
        boolean down = findPath(i+1, j, word, board, counter+1);
        boolean left = findPath(i, j-1, word, board, counter+1);
        boolean up = findPath(i-1, j, word, board, counter+1);

        if (right || down || left || up) {
            return true;
        }

        board[i][j] = temp;
        return false;
    }

    // Create Trie from word, loop through matrix and check.
    // O(word+n*m) - time, O(word) - space, where m*n - board size
    public boolean existsTrie(char[][] board, String word) {
        if (word == null || word.isEmpty()) {
            return true;
        }
        Trie trie = new Trie();
        trie.insert(word);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existsTrie(board, i, j, trie.root)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existsTrie(char[][] board, int i, int j, TrieNode root) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] == '*' || !root.children.containsKey(board[i][j])) {
            return false;
        }
        char symbol = board[i][j];
        char temp = board[i][j];
        board[i][j] = '*';
        TrieNode node = root.children.get(symbol);
        if (node.isWord) {
            return true;
        }
        boolean right = existsTrie(board, i, j+1, node);
        boolean down = existsTrie(board, i+1, j, node);
        boolean left = existsTrie(board, i, j-1, node);
        boolean up = existsTrie(board, i-1, j, node);
        if (right || down || left || up) {
            return true;
        }
        board[i][j] = temp;

        return false;
    }

    private static class Trie {
        private TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void insert(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char key = word.charAt(i);
                if (!curr.children.containsKey(key)) {
                    curr.children.put(key, new TrieNode());
                }
                curr = curr.children.get(key);
            }
            curr.isWord = true;
        }

    }

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord;
    }
}
