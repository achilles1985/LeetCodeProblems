package backtracking;

/** M
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
        //System.out.println(s.exist(new char[][] {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCCED")); // true
        //System.out.println(s.exist(new char[][] {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "SEE")); // true
        //System.out.println(s.exist(new char[][] {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCB")); // false
        //System.out.println(s.exist(new char[][] {{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}}, "AAB")); // true
        System.out.println(s.exist(new char[][] {{'A', 'A'}}, "AAA")); // false
    }

    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (findPath(i, j, word, board, 0, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findPath(int i, int j, String word, char[][] board, int counter, boolean[][] visited) {
        if (word.length() == counter) {
            return true;
        }
        if (i < 0 || i > board.length-1 || j < 0 || j > board[0].length-1 || word.charAt(counter) != board[i][j]) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = '$';
        boolean right = findPath(i, j+1, word, board, counter+1, visited);
        boolean down = findPath(i+1, j, word, board, counter+1, visited);
        boolean left = findPath(i, j-1, word, board, counter+1, visited);
        boolean up = findPath(i-1, j, word, board, counter+1, visited);

        if (right || down || left || up) {
            return true;
        }

        board[i][j] = temp;
        return false;
    }
}
