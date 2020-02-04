package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**M
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 *
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction,
 * then one square in an orthogonal direction.
 *
 * Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer
 * exists.

 * Example 1:
 * Input: x = 2, y = 1
 * Output: 1
 * Explanation: [0, 0] → [2, 1]
 *
 * Example 2:
 * Input: x = 5, y = 5
 * Output: 4
 * Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 *
 * Constraints:
 *     |x| + |y| <= 300
 */
public class MinimumKnightMoves_1197 {

    public static void main(String[] args) {
        MinimumKnightMoves_1197 s = new MinimumKnightMoves_1197();
        System.out.println(s.minKnightMoves(2,1)); //1
        System.out.println(s.minKnightMoves(5,5)); //4
        System.out.println(s.minKnightMoves(25,-87)); //44
    }

    public int minKnightMoves(int x, int y) {
        int[][] directions = new int[][]{
                {1,2},{-1,2},{2,1},{2,-1},
                {-2,1},{-2,-1},{-1,2},{1,-2}};
        Queue<int[]> neighbors = new LinkedList<>();
        neighbors.add(new int[]{0,0});

        Set<String> seen = new HashSet<>();
        seen.add("0:0");

        int levels = 0;
        while (!neighbors.isEmpty()) {
            int size = neighbors.size();
            while (size-- > 0) {
                int[] cell = neighbors.poll();
                int curX = cell[0];
                int curY = cell[1];
                if (curX == x && curY == y) {
                    return levels;
                }
                for (int[] direction: directions) {
                    int nextX = curX + direction[0];
                    int nextY = curY + direction[1];
                    if (seen.contains(nextX+":"+nextY) || Math.abs(nextX) + Math.abs(nextY) > 300) {
                        continue;
                    }
                    neighbors.add(new int[]{nextX, nextY});
                    seen.add(nextX+":"+nextY);
                }
            }
            levels++;
        }
        return -1;
    }
}
