package graph.mustknow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Detect cycle in directed graph: https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
 * Detect cycle in undirected graph: https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
 */
// For cycle detection, color graph can be user, visiting node mark as -1, visited as 1
public class CycleDetection {

    public static void main(String[] args) {
        CycleDetection s = new CycleDetection();
        System.out.println(s.hasCycleDirectedGraph(new int[][]{{1,2},{1,3},{3,4},{3,1}})); // true
        System.out.println(s.hasCycleDirectedGraph(new int[][]{{1,2},{1,3},{3,4},{3,5}})); // false

        System.out.println(s.hasCycleUndirectedGraph(new int[][]{{1,2},{2,1},{2,4},{4,2},{1,3},{3,1}})); // false
        System.out.println(s.hasCycleUndirectedGraph(new int[][]{{1,2},{2,1},{2,4},{4,2},{4,3},{3,4},{1,3},{3,1}})); // true

        System.out.println(s.hasCycleUndirectedGraphUnionFind(new int[][]{{1,2},{2,4},{1,3}})); // false
        System.out.println(s.hasCycleUndirectedGraphUnionFind(new int[][]{{1,2},{2,4},{3,4},{1,3}})); // true
    }

    // O(V+E) - time, O(V) - space
    public boolean hasCycleUndirectedGraphUnionFind(int[][] edges) {
        if (edges == null) {
            return false;
        }
        Set<Integer> vertexes = new HashSet<>();
        for (int[] edge: edges) {
            vertexes.add(edge[0]);
            vertexes.add(edge[1]);
        }
        UnionFind uf = new UnionFind();
        for (int vertex: vertexes) {
            uf.makeSet(vertex);
        }
        for (int[] edge: edges) {
            UnionFind.Node s1 = uf.findSet(edge[0]);
            UnionFind.Node s2 = uf.findSet(edge[1]);
            if (s1 == s2) {
                return true;
            }
            uf.union(s1.data, s2.data);
        }

        return false;
    }

    // O(V+E) - time, O(V) - space
    public boolean hasCycleDirectedGraph(int[][] graph) {
        if (graph == null) {
            return false;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            map.computeIfAbsent(graph[i][0], key -> map.getOrDefault(key, new ArrayList<>())).add(graph[i][1]);
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> dfs = new HashSet<>();
        for (Integer vertex: map.keySet()) {
            if (visited.contains(vertex)) {
                continue;
            }
            if (hasCycleDirectedGraphHelper(map, visited, dfs, vertex)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleDirectedGraphHelper(Map<Integer, List<Integer>> map, Set<Integer> visited, Set<Integer> dfs, Integer vertex) {
        if (dfs.contains(vertex)) {
            return true;
        }
        if (visited.contains(vertex)) {
            return false;
        }
        dfs.add(vertex);
        visited.add(vertex);
        for (Integer child: map.getOrDefault(vertex, Collections.emptyList())) {
            if (hasCycleDirectedGraphHelper(map, visited, dfs, child)) {
                return true;
            }
        }
        dfs.remove(vertex);

        return false;
    }

    // O(V+E) - time, O(V) - space
    public boolean hasCycleUndirectedGraph(int[][] graph) {
        if (graph == null) {
            return false;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            map.computeIfAbsent(graph[i][0], key -> map.getOrDefault(key, new ArrayList<>())).add(graph[i][1]);
        }
        Set<Integer> visited = new HashSet<>();
        for (Integer vertex: map.keySet()) {
            if (visited.contains(vertex)) {
                continue;
            }
            if (hasCycleUndirectedGraphHelper(map, visited, vertex, null)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleUndirectedGraphHelper(Map<Integer, List<Integer>> graph, Set<Integer> visited, Integer vertex, Integer parent) {
        if (visited.contains(vertex)) {
            return true;
        }
        visited.add(vertex);
        for (Integer child: graph.getOrDefault(vertex, Collections.emptyList())) {
            if (!child.equals(parent)) {
                if (hasCycleUndirectedGraphHelper(graph, visited, child, vertex)) {
                    return true;
                }
            }
        }
        return false;
    }
}
