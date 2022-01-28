package simulation;

/** M
 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid. The rules of Tic-Tac-Toe are:
 *     Players take turns placing characters into empty squares ' '.
 *     The first player A always places 'X' characters, while the second player B always places 'O' characters.
 *     'X' and 'O' characters are always placed into empty squares, never on filled ones.
 *     The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
 *     The game also ends if all squares are non-empty.
 *     No more moves can be played if the game is over.
 *
 * Given a 2D integer array moves where moves[i] = [rowi, coli] indicates that the ith move will be played on grid[rowi][coli].
 * Return the winner of the game if it exists (A or B). In case the game ends in a draw return "Draw". If there are still movements to play return "Pending".
 * You can assume that moves is valid (i.e., it follows the rules of Tic-Tac-Toe), the grid is initially empty, and A will play first.
 *
 * Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
 * Output: "A"
 * Explanation: A wins, they always play first.
 *
 * Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
 * Output: "B"
 * Explanation: B wins.
 */
public class FindWinnerOnTicTacToeGame_1275 {

    public static void main(String[] args) {
        FindWinnerOnTicTacToeGame_1275 s = new FindWinnerOnTicTacToeGame_1275();
        System.out.println(s.tictactoe(new int[][]{{0,0},{2,0},{1,1},{2,1},{2,2}})); //A
        System.out.println(s.tictactoe(new int[][]{{0,0},{1,1},{0,1},{0,2},{1,0},{2,0}})); //B
        System.out.println(s.tictactoe(new int[][]{{0,0},{1,1},{2,0},{1,0},{1,2},{2,1},{0,1},{0,2},{2,2}})); //Draw
    }

    // O(1) - time, space
    public String tictactoe(int[][] moves) {
        int n = 3;
        int[] rows = new int[3], cols = new int[3];
        int diagonal = 0, antiDiagonal = 0, player = 1;
        for (int[] move: moves) {
            int row = move[0];
            int col = move[1];
            rows[row] += player;
            cols[col] += player;
            if (row == col) {
                diagonal += player;
            }
            if (row + col == n - 1) {
                antiDiagonal += player;
            }

            if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n
                    || Math.abs(diagonal) == n || Math.abs(antiDiagonal) == n) {
                return player == 1 ? "A" : "B";
            }

            player *= -1;
        }

        return moves.length == n*n ? "Draw" : "Pending";
    }

}
