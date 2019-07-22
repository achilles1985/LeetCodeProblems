package dynamic;

/** H
 The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

 Note:
 The knight's health has no upper bound.
 Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 */
public class DungeonGame_174 {

    public static void main(String[] args) {
        DungeonGame_174 s = new DungeonGame_174();
        System.out.println(s.calculateMinimumHP(new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}}));
        System.out.println(s.calculateMinimumHPTopDown(new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}}));

        System.out.println(s.calculateMinimumHP(new int[][]{{100}}));
    }

    // O(2^n) - time, O(n*m)- space
    public int calculateMinimumHP(int[][] dungeon) {
        return 1 + dfs(dungeon, 0, 0);
    }

    // O(m*n) - time, space
    public int calculateMinimumHPTopDown(int[][] dungeon) {
        Integer[][] cache = new Integer[dungeon.length][dungeon[0].length];
        return 1 + calculateMinimumHPTopDown(dungeon, 0, 0, cache);
    }

    private int calculateMinimumHPTopDown(int[][] d, int i, int j, Integer[][] cache) {
        if(i < 0 || i >= d.length || j < 0 || j >= d[0].length) {
            return Integer.MAX_VALUE;
        }
        if (cache[i][j] != null) {
            return cache[i][j];
        }

        if(i == d.length-1 && j == d[0].length-1){
            return d[i][j] > 0 ? 0 : Math.abs(d[i][j]);
        }

        int min = d[i][j] - Math.min(calculateMinimumHPTopDown(d, i+1, j, cache), calculateMinimumHPTopDown(d, i, j+1, cache));
        int res1 =  (min > 0) ? 0 : Math.abs(min);
        cache[i][j] = res1;

        return cache[i][j];
    }

    private int dfs(int[][] d, int i, int j){
        if(i < 0 || i >= d.length || j < 0 || j >= d[0].length)
            return Integer.MAX_VALUE;

        if(i == d.length-1 && j == d[0].length-1){
            return d[i][j] > 0 ? 0 : Math.abs(d[i][j]);
        }

        int res = d[i][j] - Math.min(dfs(d, i+1, j), dfs(d, i, j+1));
        return  (res > 0) ? 0 : Math.abs(res);
    }
}
