package array.medium;

import utils.SolutionUtils;

/**M
 Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 A region is captured by flipping all 'O's into 'X's in that surrounded region.

 Example:
 X X X X
 X O O X
 X X O X
 X O X X

 After running your function, the board should be:
 X X X X
 X X X X
 X X X X
 X O X X

 Explanation:
 Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
 Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
/*
Pay attention: region to flip must be surrounded!
 */
public class SurroundedRegions_130 {

    public static void main(String[] args) {
        SurroundedRegions_130 s = new SurroundedRegions_130();
        char[][] board0 = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};

        char[][] board1 = {
                {'X', 'O', 'X', 'X', 'O'},
                {'X', 'O', 'O', 'X', 'O'},
                {'X', 'X', 'O', 'X', 'X'},
                {'X', 'O', 'X', 'O', 'O'},
                {'X', 'O', 'X', 'O', 'O'}};
        char[][] board2 = {
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'X'},
                {'O', 'X', 'O', 'X'},
                {'O', 'O', 'X', 'O'}};
        char[][] board3 = {
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}};
        char[][] board4 = {
                {'X', 'O', 'X'},
                {'X', 'O', 'X'},
                {'X', 'O', 'X'}};
        char[][] board5 = {
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'},
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'}};
        s.solve(board0);

        //s.solve(board1);
        //s.solve(board2);
        //s.solve(board3);
        //s.solve(board4);
        //s.solve(board5);
        SolutionUtils.print(board0);
    }

    // O(rows*cols) - time, O(1) - space
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = '*';
                }
            }
        }
        // left border
        for (int i = 0; i < board.length; i++) {
            flip(i, 0, board, '*', 'O');
        }
        // right border
        for (int i = 0; i < board.length; i++) {
            flip(i, board[0].length-1, board, '*', 'O');
        }
        // top border
        for (int i = 0; i < board[0].length; i++) {
            flip(0, i, board, '*', 'O');
        }
        // bottom border
        for (int i = 0; i < board[0].length; i++) {
            flip(board.length-1, i, board, '*', 'O');
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = 'X';
                }
            }
        }
        SolutionUtils.print(board);
        System.out.println();
    }

    private void flip(int i, int j, char[][] board, char oldSymbol, char newSymbol) {
        if (i < 0 || i > board.length-1 || j < 0 || j > board[0].length-1 || board[i][j] != '*') {
            return;
        }

        if (board[i][j] == oldSymbol) {
            board[i][j] = newSymbol;
        }

        flip(i, j+1, board, oldSymbol, newSymbol);
        flip(i+1, j, board, oldSymbol, newSymbol);
        flip(i, j-1, board, oldSymbol, newSymbol);
        flip(i-1, j, board, oldSymbol, newSymbol);
    }
}
