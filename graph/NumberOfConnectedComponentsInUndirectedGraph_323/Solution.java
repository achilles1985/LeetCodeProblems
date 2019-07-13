package graph.NumberOfConnectedComponentsInUndirectedGraph_323;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
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
public class Solution {

    // Runtime complexity - O(V + E)
    // Space complexity - O(V)
    public int countComponents(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
            graph.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
        }

        Set<Integer> visited = new HashSet<>();
        int counter = 0;
        for (Integer node: graph.keySet()) {
            if (visited.contains(node)) {
                continue;
            }
            countComponentsUtils(graph, visited, node);
            counter++;
        }

        return counter;
    }

    // Runtime complexity - O(V + E)
    // Space complexity - O(V)
    public List<Set<Integer>> findAllComponents(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
            graph.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
        }

        Set<Integer> visited = new HashSet<>();
        List<Set<Integer>> result = new ArrayList<>();
        for (Integer node: graph.keySet()) {
            if (visited.contains(node)) {
                continue;
            }
            Set<Integer> component = new HashSet<>();
            findAllComponentsUtils(node, visited, graph, component);
            result.add(component);
        }


        return result;
    }

    private void findAllComponentsUtils(Integer node, Set<Integer> visited, Map<Integer, Set<Integer>> graph, Set<Integer> result) {
        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        if (graph.containsKey(node)) {
            for (Integer child : graph.get(node)) {
                findAllComponentsUtils(child, visited, graph, result);
            }
        }
        result.add(node);
    }

    private void countComponentsUtils(Map<Integer, Set<Integer>> graph, Set<Integer> visited, Integer node) {
        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        if (graph.containsKey(node)) {
            for (Integer child : graph.get(node)) {
                countComponentsUtils(graph, visited, child);
            }
        }
    }
}
