package backtracking.medium;

import utils.SolutionUtils;

/** M
 * On a NxN chess board, find all legal Knight's Tours.
 */

// There are N*N i.e., N^2 cells in the board and we have a maximum of 8 choices to make from a cell, so we can write the worst case running time as O(8^N^2).
public class KnightsTour {

    public static void main(String[] args) {
        KnightsTour s = new KnightsTour();
        SolutionUtils.print(s.solveKT());
    }

    // O(8^(N*N)) - time
    public int[][] solveKT() {
        int[][] tour = new int[8][8];
        solveKTUtils(0, 0, 0, tour);

        return tour;
    }

    private boolean solveKTUtils(int i, int j, int counter, int[][] tour) {
        if (counter == 8*8) {
            return true;
        }

        if (i < 0 || i >= tour.length || j < 0 || j >= tour[0].length || tour[i][j] != 0) {
            return false;
        }

        tour[i][j] = counter;
        boolean upRight = solveKTUtils(i-2, j+1, counter+1, tour);
        boolean rightUp = solveKTUtils(i-1, j+2, counter+1, tour);
        boolean rightDown = solveKTUtils(i+1, j+2, counter+1, tour);
        boolean downRight = solveKTUtils(i+2, j+1, counter+1, tour);
        boolean downLeft = solveKTUtils(i+2, j-1, counter+1, tour);
        boolean leftDown = solveKTUtils(i+1, j-2, counter+1, tour);
        boolean leftUp = solveKTUtils(i-1, j-2, counter+1, tour);

        if (upRight || rightUp || rightDown || downRight || downLeft || leftDown || leftUp) {
            return true;
        }
        tour[i][j] = 0;

        return false;
    }

}
