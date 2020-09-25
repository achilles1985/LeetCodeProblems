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
 *      1
 *    / | \
 *   2  3  4
 *   |   \ /
 *   5    6
 */
public class GraphTraversal {

    public static void main(String[] args) {
        GraphTraversal s = new GraphTraversal();
        System.out.println(s.dfs(new int[][]{{1,2},{1,3},{1,4},{2,5},{3,6},{4,6}})); //[1, 2, 5, 3, 6, 4]
        System.out.println(s.dfsIterative(new int[][]{{1,2},{1,3},{1,4},{2,5},{3,6},{4,6}}));//[1, 4, 6, 3, 2, 5]
        System.out.println(s.bfs(new int[][]{{1,2},{1,3},{1,4},{2,5},{3,6},{4,6}})); //[1, 2, 3, 4, 5, 6]
    }

    // O(V+E) - time, O(V) - space
    public List<Integer> dfs(int[][] edges) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            graph.computeIfAbsent(edges[i][0], (key) -> new ArrayList<>()).add(edges[i][1]);
        }
        Set<Integer> visited = new HashSet<>();
        dfsHelper(graph, visited, result, edges[0][0]);

        return result;
    }

    private void dfsHelper(Map<Integer, List<Integer>> map, Set<Integer> visited, List<Integer> result, int vertex) {
        if (visited.contains(vertex)) {
            return;
        }
        result.add(vertex);
        visited.add(vertex);
        for (int child: map.getOrDefault(vertex, Collections.emptyList())) {
            dfsHelper(map, visited, result, child);
        }
    }

    // O(V+E) - time, O(V) - space
    public List<Integer> dfsIterative(int[][] edges) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            graph.computeIfAbsent(edges[i][0], (key) -> new ArrayList<>()).add(edges[i][1]);
        }
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(edges[0][0]);
        while (!stack.isEmpty()) {
            Integer vertex = stack.pop();
            if (visited.contains(vertex)) {
                continue;
            }
            result.add(vertex);
            visited.add(vertex);
            for (int child: graph.getOrDefault(vertex, Collections.emptyList())) {
                stack.push(child);
            }
        }

        return result;
    }

    // O(V+E) - time, O(V) - space
    public List<Integer> bfs(int[][] edges) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            graph.computeIfAbsent(edges[i][0], (key) -> new ArrayList<>()).add(edges[i][1]);
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(edges[0][0]);
        visited.add(edges[0][0]);
        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            result.add(vertex);
            for (int child: graph.getOrDefault(vertex, Collections.emptyList())) {
                if (!visited.contains(child)) {
                    queue.add(child);
                    visited.add(child);
                }
            }
        }

        return result;
    }

}
