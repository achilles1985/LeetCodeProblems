package bfs;

import java.util.*;

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
        System.out.println(s.minKnightMoves2(2,1)); //1
        System.out.println(s.minKnightMoves2(5,5)); //4
        System.out.println(s.minKnightMoves2(25,-87)); //44
    }

    // Unidirectional search
    // O(max(|x|,|y|)^2) - time, space
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

    // Unidirectional search
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

    // Bidirectional search (go from 0,0 and target at the same time)
    // O(max(|x|,|y|)^2) - time, space
    public int minKnightMoves2(int x, int y) {
        Queue<int[]> sourceQueue = new LinkedList<>();
        Map<String, Integer> sourceVisited = new HashMap<>();
        sourceQueue.add(new int[]{0,0,0});
        sourceVisited.put("0,0", 0);

        Queue<int[]> targetQueue = new LinkedList<>();
        Map<String, Integer> targetVisited = new HashMap<>();
        targetQueue.add(new int[]{x,y,0});
        targetVisited.put(x + "," + y, 0);

        int[][] dirs = new int[][]{{1,2},{-1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,1},{-2,-1}};
        while (!sourceQueue.isEmpty() && !targetQueue.isEmpty()) {
            int[] source = sourceQueue.poll();
            String sourceCoordinates = source[0] + "," + source[1];
            if (targetVisited.containsKey(sourceCoordinates)) {
                return source[2] + targetVisited.get(sourceCoordinates);
            }

            int[] target = targetQueue.poll();
            String targetCoordinates = target[0] + "," + target[1];
            if (sourceVisited.containsKey(targetCoordinates)) {
                return target[2] + sourceVisited.get(targetCoordinates);
            }

            for (int[] dir: dirs) {
                // source
                int newSourceX = source[0] + dir[0];
                int newSourceY = source[1] + dir[1];
                String newSourceCoordinates = newSourceX + "," + newSourceY;
                if (!sourceVisited.containsKey(newSourceCoordinates)) {
                    sourceQueue.add(new int[]{newSourceX, newSourceY, source[2] + 1});
                    sourceVisited.put(newSourceCoordinates, source[2] + 1);
                }

                // target
                int newTargetX = target[0] + dir[0];
                int newTargetY = target[1] + dir[1];
                String newTargetCoordinates = newTargetX + "," + newTargetY;
                if (!targetVisited.containsKey(newTargetCoordinates)) {
                    targetQueue.add(new int[]{newTargetX, newTargetY, target[2] + 1});
                    targetVisited.put(newTargetCoordinates, target[2] + 1);
                }
            }
        }

        return -1;
    }
}
