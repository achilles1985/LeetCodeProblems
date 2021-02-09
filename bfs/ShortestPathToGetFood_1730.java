package bfs;

import java.util.LinkedList;
import java.util.Queue;

/** M
 * You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.
 * You are given an m x n character matrix, grid, of these different types of cells:
 *     '*' is your location. There is exactly one '*' cell.
 *     '#' is a food cell. There may be multiple food cells.
 *     'O' is free space, and you can travel through these cells.
 *     'X' is an obstacle, and you cannot travel through these cells.
 *
 *  You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
 * Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.
 *
 * Input: grid = [['X','X','X','X','X','X'],['X','*','O','O','O','X'],['X','O','O','#','O','X'],['X','X','X','X','X','X']]
 * Output: 3
 * Explanation: It takes 3 steps to reach the food.
 *
 * Input: grid = [['X','X','X','X','X'],['X','*','X','O','X'],['X','O','X','#','X'],['X','X','X','X','X']]
 * Output: -1
 * Explanation: It is not possible to reach the food.
 *
 * Input: grid = [['X','X','X','X','X','X','X','X'],['X','*','O','X','O','#','O','X'],['X','O','O','X','O','O','X','X'],['X','O','O','O','O','#','O','X'],['X','X','X','X','X','X','X','X']]
 * Output: 6
 * Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.
 *
 * Example 4:
 * Input: grid = [['O','*'],['#','O']]
 * Output: 2
 *
 * Example 5:
 * Input: grid = [['X','*'],['#','X']]
 * Output: -1
 *
 * Constraints:
 *     m == grid.length
 *     n == grid[i].length
 *     1 <= m, n <= 200
 *     grid[row][col] is '*', 'X', 'O', or '#'.
 *     The grid contains exactly one '*'.
 */
public class ShortestPathToGetFood_1730 {

    public static void main(String[] args) {
        ShortestPathToGetFood_1730 s = new ShortestPathToGetFood_1730();
        System.out.println(s.getFood(new char[][]{
                {'X','X','X','X','X','X'},
                {'X','*','O','O','O','X'},
                {'X','O','O','#','O','X'},
                {'X','X','X','X','X','X'}})); //3
        System.out.println(s.getFood(new char[][]{
                {'X','X','X','X','X'},
                {'X','*','X','O','X'},
                {'X','O','X','#','X'},
                {'X','X','X','X','X'}})); //-1
    }

    // O(n*m) - time, O(n*m) - space for visited array (can be eliminated if we can modify grid while traversing)
    public int getFood(char[][] grid) {
        if (grid == null && grid.length == 0) {
            return -1;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) { //n*m
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '*') { // mouse, since we need to find path to any food cell
                    queue.add(new int[]{i,j});
                    visited[i][j] = true;
                    break;
                }
            }
        }
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                if (grid[curr[0]][curr[1]] == '#') {
                    return level;
                }
                for (int[] direction: directions) {
                    int newX = curr[0] + direction[0];
                    int newY = curr[1] + direction[1];
                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] != 'X' && !visited[newX][newY]) {
                        queue.add(new int[]{newX,newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
