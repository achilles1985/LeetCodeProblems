package array;

import utils.SolutionUtils;

/**M
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton
 * devised by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its
 * eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia
 * article):
 *
 *     Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 *     Any live cell with two or three live neighbors lives on to the next generation.
 *     Any live cell with more than three live neighbors dies, as if by over-population..
 *     Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *
 * Write a function to compute the next state (after one update) of the board given its current state. The next state
 * is created by applying the above rules simultaneously to every cell in the current state, where births and deaths
 * occur simultaneously.
 *
 * Example:
 * Input:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * Output:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 *
 * Follow up:
 *     Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update
 *     some cells first and then use their updated values to update other cells.
 *     In this question, we represent the board using a 2D array. In principle, the board is infinite, which would
 *     cause problems when the active area encroaches the border of the array. How would you address these problems?
 */
/*
    Trick: Maintain the state of previous cell as a number: (2) when go from 0-2, (-1) from 1-0.
    id the matrix is big, we store it in a file, and then read current row, row above and below it. Then do required calculation and write the results back.
 */
public class GameOfLive_289 {

    public static void main(String[] args) {
        GameOfLive_289 s = new GameOfLive_289();
        s.gameOfLife(new int[][]{
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}});
    }

    // O(n*m) - time, O(1) - space
    public void gameOfLife(int[][] board) {
        int[][] directions = new int[][]{{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int count = 0;
                for (int[] direction: directions) {
                    int nextX = i + direction[0];
                    int nextY = j + direction[1];
                    if (nextX < 0 || nextX >= board.length || nextY < 0 || nextY >= board[0].length) {
                        continue;
                    }
                    if (isAlive(nextX, nextY, board)) {
                        count++;
                    }
                }
                setNewState(count, board, i, j);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                } else if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
        SolutionUtils.print(board);
    }

    private boolean isAlive(int x, int y, int[][] board) {
        if (board[x][y] == 1 || board[x][y] == -1) {
            return true;
        }
        return false;
    }

    private void setNewState(int count, int[][] board, int x, int y) {
        if (isAlive(x,y,board)) {
            if (count < 2 || count > 3) {
                board[x][y] = -1;
            }
        } else {
            if (count == 3) {
                board[x][y] = 2;
            }
        }
    }
}
