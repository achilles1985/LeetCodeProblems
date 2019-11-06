package graph.mustknow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        System.out.println(s.sort(new int[][]{{1,3},{2,3},{2,5},{3,4},{5,6},{4,6},{6,7}})); //[2, 5, 1, 3, 4, 6, 7]
    }

    // O(V+E) - time, O(V) - space
    public List<Integer> sort(int[][] graph) {
        if (graph == null) {
            return new ArrayList<>();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            map.computeIfAbsent(graph[i][0], key -> map.getOrDefault(key, new ArrayList<>())).add(graph[i][1]);
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
        stack.add(vertex);
    }
}
