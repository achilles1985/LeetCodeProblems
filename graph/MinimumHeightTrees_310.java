package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**M
For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree.
 Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.
Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1 :
Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
        0
        |
        1
       / \
      2   3
Output: [1]

Example 2 :
Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
     0  1  2
      \ | /
        3
        |
        4
        |
        5
Output: [3, 4]

Note:
    According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
    The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */
public class MinimumHeightTrees_310 {

    public static void main(String[] args) {
        MinimumHeightTrees_310 s = new MinimumHeightTrees_310();
        System.out.println(s.findMinHeightTrees(11, new int[][]{{0,1},{0,2},{2,3},{0,4},{2,5},{5,6},{3,7},{6,8},{8,9},{9,10}})); //[5, 6]
        System.out.println(s.findMinHeightTrees(4, new int[][]{{1,0},{1,2},{1,3}})); //[1]
        System.out.println(s.findMinHeightTrees(2, new int[][]{})); //[0,1]
        System.out.println(s.findMinHeightTrees(6, new int[][]{{0,3},{1,3},{2,3},{4,3},{5,4}})); //[3, 4]
    }

    // O(n) - time, space
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 0 || edges == null) {
            return new ArrayList<>();
        }
        if (n < 3) {
            return Arrays.asList(0, 1);
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] degree = new int[n];
        for (int i = 0; i < n-1; i++) {
            graph.computeIfAbsent(edges[i][0], key -> graph.getOrDefault(key, new ArrayList<>())).add(edges[i][1]);
            graph.computeIfAbsent(edges[i][1], key -> graph.getOrDefault(key, new ArrayList<>())).add(edges[i][0]);
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty() && n > 2) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer leave = queue.poll();
                for(int parent: graph.get(leave)) {
                    degree[parent]--;
                    if (degree[parent] == 1) {
                        queue.add(parent);
                    }
                }
                n--;
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }
        return result;
    }
}
