package unionfind;

import java.util.HashSet;
import java.util.Set;

/**M
 On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.

 Example 1:
 Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 Output: 5
 Explanation: One way to remove 5 stones is as follows:
 1. Remove stone [2,2] because it shares the same row as [2,1].
 2. Remove stone [2,1] because it shares the same column as [0,1].
 3. Remove stone [1,2] because it shares the same row as [1,0].
 4. Remove stone [1,0] because it shares the same column as [0,0].
 5. Remove stone [0,1] because it shares the same row as [0,0].
 Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.

 Example 2:
 Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 Output: 3
 Explanation: One way to make 3 moves is as follows:
 1. Remove stone [2,2] because it shares the same row as [2,0].
 2. Remove stone [2,0] because it shares the same column as [0,0].
 3. Remove stone [0,2] because it shares the same row as [0,0].
 Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.

 Example 3:
 Input: stones = [[0,0]]
 Output: 0
 Explanation: [0,0] is the only stone on the plane, so you cannot remove it.

 Constraints:
 1 <= stones.length <= 1000
 0 <= xi, yi <= 104
 No two stones are at the same coordinate point.
 */
/*
Take first stone and move all the way right till row end and all the way button till column end.
 */
public class MostStonesRemovedWithSameRowOrColumn_947 {

    public static void main(String[] args) {
        MostStonesRemovedWithSameRowOrColumn_947 s = new MostStonesRemovedWithSameRowOrColumn_947();
        System.out.println(s.removeStones(new int[][]{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}})); //5
        System.out.println(s.removeStones(new int[][]{{0,0},{0,2},{1,1},{2,0},{2,2}})); //3
        System.out.println(s.removeStones(new int[][]{{0,0}})); //0
    }

    // O(n^2) - time, O(n) - space
    public int removeStones(int[][] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        DisjointSet ds = new DisjointSet(stones.length);
        for (int i = 0; i < stones.length; i++) {
            for (int j = i+1; j < stones.length; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    ds.union(i,j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < stones.length; i++) {
            if (ds.parent[i] == i) {
                count++;
            }
        }
        return stones.length - count;
    }

    // DFS solution
    // O(n^2) - time, O(n) - space, Ans = # of stones – # of islands.
    public int removeStones2(int[][] stones) {
        Set<int[]> visited = new HashSet();
        int numOfIslands = 0;
        for (int[] s1:stones){
            if (!visited.contains(s1)){
                dfs(s1, visited, stones);
                numOfIslands++;
            }
        }
        return stones.length - numOfIslands;
    }

    private void dfs(int[] s1, Set<int[]> visited, int[][] stones){
        visited.add(s1);
        for (int[] s2: stones){
            if (!visited.contains(s2)){
                // stone with same row or column. group them into island
                if (s1[0] == s2[0] || s1[1] == s2[1])
                    dfs(s2, visited, stones);
            }
        }
    }

    private static final class DisjointSet {
        int[] parent;

        public DisjointSet(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int p1 = find(x);
            int p2 = find(y);
            parent[p2] = p1;
        }
    }
}
