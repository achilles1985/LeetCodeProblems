package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.mustknow.DisjointSet;

/**M
 *  There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in
 *  nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of
 *  C. And we defined a friend circle is a group of students who are direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the
 * ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of
 * friend circles among all the students.
 *
 * Example 1:
 * Input:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 *
 * Example 2:
 * Input:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 *
 * Note:
 *     N is in range [1,200].
 *     M[i][i] = 1 for all students.
 *     If M[i][j] = 1, then M[j][i] = 1.
 */
public class FriendCircles_547 {

    public static void main(String[] args) {
        FriendCircles_547 s = new FriendCircles_547();
        System.out.println(s.findCircleNum2(new int[][]{
                {1},{2},{3},{0},{0}})); // 1
        System.out.println(s.findCircleNum2(new int[][]{
                {1,1,0},
                {1,1,0},
                {0,0,1}})); // 2
        System.out.println(s.findCircleNum2(new int[][]{
                {1,1,0},
                {1,1,1},
                {0,1,1}})); // 1
        System.out.println(s.findCircleNum2(new int[][]{
                {1,0,0,0,0},
                {0,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,0},
                {0,0,0,0,1}})); // 5
        System.out.println(s.findCircleNum2(new int[][]{
                {1,2,3,0,0}})); // 1
        System.out.println(s.findCircleNum2(new int[][]{
                {1,0,0,1},
                {0,1,1,0},
                {0,1,1,1},
                {1,0,1,1}})); // 1
    }

    // O(n^2) - time, O(n) - space
    public int findCircleNum2(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        DisjointSet ds = new DisjointSet();
        for (int i = 0; i < M.length; i++) {
            ds.makeSet(i);
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    ds.union(i,j);
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < M.length; i++) {
            set.add(ds.findSet(i));
        }
        return set.size();
    }

    // O(n^2) - time, space
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    graph.computeIfAbsent(i, key -> graph.getOrDefault(key, new ArrayList<>())).add(j);
                }
            }
        }
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        for (Integer node: graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(node, null, visited, graph);
                count++;
            }
        }
        return count;
    }

    private void dfs(Integer node, Integer parent, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);
        for (Integer adjacent: graph.getOrDefault(node, Collections.emptyList())) {
            if (adjacent != parent) {
                dfs(adjacent, node, visited, graph);
            }
        }
    }
}
