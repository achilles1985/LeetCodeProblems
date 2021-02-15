package array.medium;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidSudoku_36 {

    public static void main(String[] args) {
        ValidSudoku_36 s = new ValidSudoku_36();
        System.out.println(s.isValidSudoku(new char[][] {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}})); //true
        System.out.println(s.isValidSudoku(new char[][]{
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}})); //false
    }

    // O(1) - time, space
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Integer>> rows = new HashMap<>();
        Map<Integer, Set<Integer>> cols = new HashMap<>();
        Map<String, Set<Integer>> grids = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int val = board[i][j] - '0'; //
                if (rows.getOrDefault(i, Collections.emptySet()).contains(val)) {
                    return false;
                }
                if (cols.getOrDefault(j,Collections.emptySet()).contains(val)) {
                    return false;
                }
                String grid = i/3+","+j/3; //or (i/3)*3 + j/3;
                if (grids.getOrDefault(grid,Collections.emptySet()).contains(val)) {
                    return false;
                }
                rows.computeIfAbsent(i, k->new HashSet<>()).add(val);
                cols.computeIfAbsent(j, k->new HashSet<>()).add(val);
                grids.computeIfAbsent(grid, k->new HashSet<>()).add(val);
            }
        }
        return true;
    }
}
