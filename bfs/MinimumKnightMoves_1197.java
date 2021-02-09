package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**M
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction,
 * then one square in an orthogonal direction.
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
/*
    1. can x,y be negative?
    Optimization: instead of searching in all 4 quardrants, search only in the quardrant that contains target
 */
public class MinimumKnightMoves_1197 {

    public static void main(String[] args) {
        MinimumKnightMoves_1197 s = new MinimumKnightMoves_1197();
        System.out.println(s.minKnightMoves(2,1)); //1
        System.out.println(s.minKnightMoves(5,5)); //4
        System.out.println(s.minKnightMoves(25,-87)); //44
    }

    public int minKnightMovesBF(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new int[]{0,0});
        visited.add(0+","+0);
        int[][] directions = new int[][]{{1,2},{-1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,1},{-2,-1}};
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                if (curr[0] == x && curr[1] == y) {
                    return level;
                }
                for (int[] direction: directions) {
                    int newX = curr[0] + direction[0];
                    int newY = curr[1] + direction[1];
                    String hash = newX + "," + newY;
                    if (!visited.contains(hash)) {
                        queue.add(new int[]{newX,newY});
                        visited.add(hash);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public int minKnightMoves(int x, int y) {
        x = Math.abs(x); // transform the coordinates to lay in the first quardrant
        y = Math.abs(y);
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new int[]{0,0});
        visited.add(0+","+0);
        int[][] directions = new int[][]{{1,2},{-1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,1},{-2,-1}};
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                if (curr[0] == x && curr[1] == y) {
                    return level;
                }
                for (int[] direction: directions) {
                    int newX = curr[0] + direction[0];
                    int newY = curr[1] + direction[1];
                    String hash = newX + "," + newY;
                    if (!visited.contains(hash) && newX >= -1 && newY >= -1) { // -1,-1 to cover edge case when target=(1,1)
                        queue.add(new int[]{newX,newY});
                        visited.add(hash);
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
