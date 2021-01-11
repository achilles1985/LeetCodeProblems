package graph.mustknow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 *      1   2
 *       \ /\
 *        3  5
 *       /   /
 *      4   /
 *       \ /
 *        6
 *         \
 *          7
 *
 *  Topological sort: https://www.geeksforgeeks.org/topological-sorting/
 */
public class TopologicalSort {

    public static void main(String[] args) {
        TopologicalSort s = new TopologicalSort();
        System.out.println(s.sort(new int[][]{{1,3},{2,3},{2,5},{3,4},{5,6},{4,6},{6,7}})); //[2, 5, 1, 3, 4, 6, 7], [1, 2, 3, 5, 4, 6, 7]
        System.out.println(s.sort2(new int[][]{{1,3},{2,3},{2,5},{3,4},{5,6},{4,6},{6,7}})); //[2, 5, 1, 3, 4, 6, 7], [1, 2, 3, 5, 4, 6, 7]
        System.out.println(s.sort3(new int[][]{{1,3},{2,3},{2,5},{3,4},{5,6},{4,6},{6,7}})); //[2, 5, 1, 3, 4, 6, 7], [1, 2, 3, 5, 4, 6, 7]
    }

    // O(V+E) - time, O(V) - space
    public List<Integer> sort(int[][] edges) {
        if (edges == null) {
            return new ArrayList<>();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            map.computeIfAbsent(edges[i][0], key -> map.getOrDefault(key, new ArrayList<>())).add(edges[i][1]);
        }
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (Integer vertex: map.keySet()) {
            if (visited.contains(vertex)) {
                continue;
            }
            sortHelper(map, visited, vertex, stack);
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private void sortHelper(Map<Integer, List<Integer>> graph, Set<Integer> visited, Integer vertex, Stack<Integer> stack) {
        if (visited.contains(vertex)) {
            return;
        }
        visited.add(vertex);
        for (Integer child: graph.getOrDefault(vertex, Collections.emptyList())) {
            sortHelper(graph, visited, child, stack);
        }
        stack.push(vertex);
    }

    // O(E + V) - time, O(V) - space, using BFS and indegree
    public List<Integer> sort2(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int[] edge: edges) {
            int from  = edge[0];
            int to = edge[1];
            graph.computeIfAbsent(from, key -> graph.getOrDefault(key, new HashSet<>())).add(to);
            indegree.put(to, indegree.getOrDefault(to, 0) + 1);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (Integer node: graph.keySet()){
            if (indegree.getOrDefault(node, 0) == 0) {
                queue.add(node);
            }
        }

        List<Integer> sorted = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int node = queue.poll();
                sorted.add(node);
                for (Integer child: graph.getOrDefault(node, new HashSet<>())) {
                    indegree.put(child, indegree.get(child) - 1);
                    if (indegree.get(child) == 0) {
                        queue.add(child);
                    }
                }
            }
        }
        return sorted;
    }

    // using colors, -1 - visiting, 1 - visited
    public List<Integer> sort3(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            graph.computeIfAbsent(edges[i][0], key -> graph.getOrDefault(key, new ArrayList<>())).add(edges[i][1]);
        }
        Map<Integer, Integer> visited = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (Integer node: graph.keySet()) {
            if (!visited.containsKey(node)) {
                if (dfs3(graph, node, visited, stack)) { // a cycle found
                    return Collections.emptyList();
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }

        return res;
    }

    private boolean dfs3(Map<Integer, List<Integer>> graph, Integer node, Map<Integer, Integer> visited, Stack<Integer> stack) {
        if (visited.containsKey(node) && visited.get(node) == -1) { //visiting
            return true;
        }
        if (visited.containsKey(node)) { //visited
            return false;
        }
        visited.put(node, -1);
        for (Integer child: graph.getOrDefault(node, new ArrayList<>())) {
            if (dfs3(graph, child, visited, stack)) {
                return true;
            }
        }
        visited.put(node, 1);
        stack.push(node);

        return false;
    }
}
