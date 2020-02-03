package bfs;

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
    }

    public int minKnightMoves(int x, int y) {
        return 0;
    }
}
