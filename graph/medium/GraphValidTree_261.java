package graph.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** M [marked]
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 *
 * Example 1:
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 *
 * Example 2:
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 *
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected,
 * [0,1] is the same as [1,0] and thus will not appear together in edges.
 */
/*
    If graph is disconnected or there is a cycle, it is not a valid tree.
    null or one node is a valid tree.
 */
// it can be done with union-find algorithm or cycle detection algorithm (in that case for undirected graph to avoid self-loop we should not go backtrack from children to its parent)
//http://www.geeksforgeeks.org/union-find/
//https://discuss.leetcode.com/topic/21712/ac-java-union-find-solution

public class GraphValidTree_261 {

    public static void main(String[] args) {
        GraphValidTree_261 s = new GraphValidTree_261();
        int[][] edges0 = new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}}; //false

        int[][] edges1 = new int[][]{{0, 1}, {0, 2}, {0, 3}, {3, 4}}; //true
        int[][] edges2 = new int[][]{}; //true

        System.out.println(s.validTree2(5, edges0));

        System.out.println(s.validTree(1, edges1));
        System.out.println(s.validTree(1, edges2));
    }

    // O(V+E) - time, space
    public boolean validTree2(int n, int[][] edges) {
        if (edges == null || edges.length == 0) {
            return n == 1;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            graph.computeIfAbsent(from, key -> new ArrayList<>()).add(to);
            graph.computeIfAbsent(to, key -> new ArrayList<>()).add(from);
        }
        Map<Integer, Integer> visited = new HashMap<>();
        int count = 0;
        for (int vertex = 0; vertex < n; vertex++) {
            if (visited.containsKey(vertex)) {
                continue;
            }
            if (hasCycle(graph, vertex, -1, visited)) {
                return false;
            }
            count++;
            if (count > 1) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle(Map<Integer, List<Integer>> graph, int node, int parent, Map<Integer, Integer> visited) {
        if (visited.containsKey(node) && visited.get(node) == -1) {
            return true;
        }
        if (visited.containsKey(node)) {
            return false;
        }
        visited.put(node, -1);
        for (int child: graph.getOrDefault(node, Collections.emptyList())) {
            if (child == parent) {
                continue;
            }
            if (hasCycle(graph, child, node, visited)) {
                return true;
            }
        }
        visited.put(node, 1);

        return false;
    }

    // O(E+V) - time, O(V) - space
    public boolean validTree(int n, int[][] edges) {
        if (edges == null) {
            return true;
        }
        if (n == 1 && edges.length == 0) {
            return true;
        }
        if (n > 1 && edges.length == 0) {
            return false;
        }
        UnionFind uf = new UnionFind(n); // O(V)
        for (int[] edge: edges) { // O(E)
            if (uf.find(edge[0]) == uf.find(edge[1])) {
                return false;
            }
            uf.union(edge[0], edge[1]);
        }

        return uf.size == 1;
    }

    private static class UnionFind {
        int[] parent;
        int size;

        UnionFind(int size) {
            this.size = size;
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int p1 = find(x);
            int p2 = find(y);
            parent[p1] = p2;
            size--;
        }
    }
}
