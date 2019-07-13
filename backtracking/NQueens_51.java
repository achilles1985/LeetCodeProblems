package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * H
  The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
  Given an integer n, return all distinct solutions to the n-queens puzzle.
  Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

  Example:
  Input: 4
  Output: [
  [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],
  <p>
  ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
  ]
  Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */

// T(n)=O(N2)+n∗T(n−1) - time complexity which boils down to O(n!) (https://www.codesdope.com/course/algorithms-backtracking/)
public class NQueens_51 {

    public static void main(String[] args) {
        NQueens_51 s = new NQueens_51();
        System.out.println(s.solveNQueens(4));
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        solveNQueens(n, 0, new ArrayList<>(), result);
        return result;
    }

    private void solveNQueens(int n, int row, List<Integer> colPlacements, List<List<String>> result) {
        if (row == n) {
            result.add(generateBoardFromPlacements(colPlacements, n));
            return;
        }
        for (int col = 0; col < n; col++) {
            colPlacements.add(col);
            if (isValid1(colPlacements)) {
                solveNQueens(n, row + 1, colPlacements, result);
            }
            colPlacements.remove(colPlacements.size() - 1);
        }
    }

    private boolean isValid1(List<Integer> colPlacements) {
        int rowWeAreValidatingOn = colPlacements.size() - 1;
        Integer columnWeAreValidatingOn = colPlacements.get(colPlacements.size() - 1);
        for (int ithQueenRow = 0; ithQueenRow < rowWeAreValidatingOn; ithQueenRow++) {
            Integer ithQueueColumn = colPlacements.get(ithQueenRow);
            if (columnWeAreValidatingOn == ithQueueColumn || // on the same column
               (ithQueenRow-ithQueueColumn) == (rowWeAreValidatingOn-columnWeAreValidatingOn) || (ithQueenRow+ithQueueColumn) == (rowWeAreValidatingOn+columnWeAreValidatingOn)) { // on the same diagonal
                return false;
            }
        }

        return true;
    }

    private List<String> generateBoardFromPlacements(List<Integer> colPlacements, int n) {
        List<String> board = new ArrayList<>();
        int totalItemsPlaced = colPlacements.size();
        for (int row = 0; row < totalItemsPlaced; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < n; col++) {
                if (col == colPlacements.get(row)) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            board.add(sb.toString());

        }

        return board;
    }

}
