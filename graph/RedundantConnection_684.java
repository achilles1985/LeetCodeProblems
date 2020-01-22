package graph;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import graph.mustknow.DisjointSet;
import utils.SolutionUtils;

/** M
 In this problem, a tree is an undirected graph that is connected and has no cycles.
 The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added.
 The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
 Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers,
 return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

 Example 1:
 Input: [[1,2], [1,3], [2,3]]
 Output: [2,3]
 Explanation: The given undirected graph will be like this:
   1
  / \
 2 - 3

 Example 2:
 Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 Output: [1,4]
 Explanation: The given undirected graph will be like this:
 5 - 1 - 2
     |   |
     4 - 3

 Note:
 The size of the input 2D-array will be between 3 and 1000.
 Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */
/*
Checks each edge one by one
Find the edge that make a cycle, use union find to have O(n) - time, space
 */
public class RedundantConnection_684 {

    public static void main(String[] args) {
        RedundantConnection_684 s = new RedundantConnection_684();
        SolutionUtils.print(s.findRedundantConnection2(new int[][] {{1,2},{1,3},{2,3}})); // [2,3]
        SolutionUtils.print(s.findRedundantConnection2(new int[][] {{1,2},{2,3},{3,4},{1,4},{1,5}})); // [1,4]
    }

    // O(V+E) - time, O(V) - space
    public int[] findRedundantConnection2(int[][] edges) {
        DisjointSet ds = new DisjointSet();
        for (int i = 1; i <= edges.length; i++) {
            ds.makeSet(i);
        }
        for (int[] edge: edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            int parent1 = ds.findSet(node1);
            int parent2 = ds.findSet(node2);
            if (parent1 == parent2) {
                return edge;
            }
            ds.union(node1, node2);
        }
        return new int[]{};
    }

    // O(n^2) - time, O(n) - space
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            if (graph.containsKey(x) && hasCycle(graph, x, y, -1)) {
                return edge;
            }
            graph.computeIfAbsent(edge[0], key -> graph.getOrDefault(key, new HashSet<>())).add(edge[1]);
            graph.computeIfAbsent(edge[1], key -> graph.getOrDefault(key, new HashSet<>())).add(edge[0]);
        }

        return new int[] {-1, -1};
    }

    private boolean hasCycle(Map<Integer, Set<Integer>> graph, int curr, int target, int pre) {
        if (graph.get(curr).contains(target)) {
            return true;
        }
        for (int num : graph.get(curr)) {
            if (num == pre) {
                continue;
            }
            if (hasCycle(graph, num, target, curr)) {
                return true;
            }
        }
        return false;
    }

}
