package backtracking.hard;

/** H [marked]
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 A sudoku solution must satisfy all of the following rules:

 Each of the digits 1-9 must occur exactly once in each row.
 Each of the digits 1-9 must occur exactly once in each column.
 Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 Empty cells are indicated by the character '.'.

 A sudoku puzzle...
 ...and its solution numbers marked in red.

 Note:
 The given board contain only digits 1-9 and the character '.'.
 You may assume that the given Sudoku puzzle will have a single unique solution.
 The given board size is always 9x9.
 */
public class SudokuSolver_37 {

    public static void main(String[] args) {
        SudokuSolver_37 s = new SudokuSolver_37();
        char[][] board = new char[][] {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}} ;
        s.solveSudoku(board);

        for (char[] row: board) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < row.length; i++) {
                sb.append(row[i]);
                sb.append(',');
            }
            System.out.println(sb.toString());
        }
    }

    // O(9!^9) - time, O(1) - space. For one row, we have 9! choices to choose from. since we have 9 rows -> 9!^9.
    // For brute force: O(9^81) - time, we have 9 choices for each cell.
    public void solveSudoku(char[][] board) {
        solveSudokuUtil(0, 0, board);
    }

    private boolean solveSudokuUtil(int row, int col, char[][] board) {
        if (col == board[0].length) {
            col = 0;
            row++;
            if (row == board.length) {
                return true;
            }
        }

        if (board[row][col] != '.') {
            return solveSudokuUtil(row, col+1, board);
        }

        for (int val = 1; val <= board[0].length; val++) {
            char charToPlace = (char) (val + '0');
            if (canBePlaced(charToPlace, board, row, col)) {
                board[row][col] = charToPlace;
                if (solveSudokuUtil(row, col+1, board)) {
                    return true;
                }
                board[row][col] = '.';
            }
        }

        return false;
    }

    private boolean canBePlaced(char val, char[][] board, int row, int col) {
        // check if val already exists in the row
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == val) {
                return false;
            }
        }

        // check if val already exists in the column
        for (int i = 0; i < board[0].length; i++) {
            if (board[i][col] == val) {
                return false;
            }
        }

        int I = row / 3;
        int J = col/3;
        int topLeftSubRowBox = I*3;
        int topLeftSubBoxCol = J*3;

        // check if val already exists in the block
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (val == board[topLeftSubRowBox + i][topLeftSubBoxCol + j]) {
                    return false;
                }
            }
        }

        return true;
    }

}
