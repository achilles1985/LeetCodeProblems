package graph.medium;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** M [marked]
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the number of connected components in an undirected graph.
 0          3
 |          |
 1 --- 2    4

 Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

 Example 2:
 Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 0           4
 |           |
 1 --- 2 --- 3
 Output:  1
 Note:
 You can assume that no duplicate edges will appear in edges.
 Since all edges are undirected, [0, 1] is the same as [1, 0] and
 thus will not appear together in edges.
 */
/*
We do DFS and count how much time we cal dfs from for loop or Union find
 */
public class NumberOfConnectedComponentsInUndirectedGraph_323 {

    public static void main(String[] args) {
        NumberOfConnectedComponentsInUndirectedGraph_323 s = new NumberOfConnectedComponentsInUndirectedGraph_323();
        System.out.println(s.countComponents(5, new int[][] {{0, 1}, {1, 2}, {3, 4}})); //2
        System.out.println(s.countComponents(5, new int[][] {{0, 1}, {1, 2}, {2,3}, {3, 4}})); //1

        System.out.println(s.countComponents(5, new int[][] {{0, 1}, {1, 2}, {3, 4}})); //2
        System.out.println(s.countComponents(5, new int[][] {{0, 1}, {1, 2}, {2,3}, {3, 4}})); //1
    }

    // O(V+E) - time, O(V) - space
    public int countComponents(int n, int[][] edges) {
        if (edges == null || edges.length == 0) {
            return n;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] edge: edges) {
            if (uf.find(edge[0]) != uf.find(edge[1])) {
                uf.union(edge[0], edge[1]);
            }
        }

        return uf.size;
    }

    // O(V+E) - time, O(V) - space
    public int countComponentsDFS(int n, int[][] edges) {
        if (n == 0 || edges == null) {
            return 0;
        }
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            graph.computeIfAbsent(edges[i][0], key -> graph.getOrDefault(key, new HashSet<>())).add(edges[i][1]);
            graph.computeIfAbsent(edges[i][1], key -> graph.getOrDefault(key, new HashSet<>())).add(edges[i][0]);
        }
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                dfs(i,visited, graph);
                count++;
            }
        }
        return count;
    }

    private void dfs(int vertex, Set<Integer> visited, Map<Integer, Set<Integer>> graph) {
        if (visited.contains(vertex)) {
            return;
        }
        visited.add(vertex);
        for (Integer adjacent: graph.getOrDefault(vertex, Collections.emptySet())) {
            dfs(adjacent, visited, graph);
        }
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
