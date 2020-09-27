package graph.medium;

import java.util.*;

/**M
For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree.
 Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
 Given such a graph, write a function to find all the MHTs and return a list of their root labels.
Format
The graph contains n nodes which are labeled from 0 to n - 1.
 You will be given the number n and a list of undirected edges (each edge is a pair of labels).
You can assume that no duplicate edges will appear in edges.
 Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

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
    According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path.
    In other words, any connected graph without simple cycles is a tree.”
    The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */
/*
The idea is to remove leaves layer by layer till we end up with 1 if number of layers are odd, or 2 if even.
To determine if the node is a leave we maintain degree[] array containing how many connections each node has. If 1 - then it is a leave.
We remove that leave and decrease degree of its parent.
 */
public class MinimumHeightTrees_310 {

    public static void main(String[] args) {
        MinimumHeightTrees_310 s = new MinimumHeightTrees_310();
        System.out.println(s.findMinHeightTrees(6, new int[][]{{0,3},{1,3},{2,3},{4,3},{5,4}})); //[3, 4]
        System.out.println(s.findMinHeightTrees2(6, new int[][]{{0,3},{1,3},{2,3},{4,3},{5,4}})); //[3, 4]
        System.out.println(s.findMinHeightTrees2(4, new int[][]{{1,0},{1,2},{1,3}})); //[1]
        System.out.println(s.findMinHeightTrees(11, new int[][]{{0,1},{0,2},{2,3},{0,4},{2,5},{5,6},{3,7},{6,8},{8,9},{9,10}})); //[5, 6]
        System.out.println(s.findMinHeightTrees(2, new int[][]{})); //[0,1]
    }

    // O(E+V) - time, space
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
            int from = edges[i][0];
            int to = edges[i][1];
            graph.computeIfAbsent(from, key -> graph.getOrDefault(key, new ArrayList<>())).add(to);
            graph.computeIfAbsent(to, key -> graph.getOrDefault(key, new ArrayList<>())).add(from);
            degree[from]++;
            degree[to]++;
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
                for(int parent: graph.getOrDefault(leave, new ArrayList<>())) {
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

    // O(V+E) - time, space
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        if (edges == null) {
            return Collections.emptyList();
        }
        if (n == 1) {
            return Arrays.asList(0);
        }
        if (n < 3) {
            return Arrays.asList(0, 1);
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> endegree = new HashMap<>();
        for (int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            graph.computeIfAbsent(from, key -> graph.getOrDefault(key, new ArrayList<>())).add(to);
            graph.computeIfAbsent(to, key -> graph.getOrDefault(key, new ArrayList<>())).add(from);
            endegree.put(from, endegree.getOrDefault(from, 0) + 1);
            endegree.put(to, endegree.getOrDefault(to, 0) + 1);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Integer node: graph.keySet()) {
            if (endegree.getOrDefault(node, 0) == 1) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty() && n > 2) {
            int size = queue.size();
            while(size-- > 0) {
                Integer node = queue.poll();
                for (Integer child: graph.getOrDefault(node, new ArrayList<>())) {
                    endegree.put(child, endegree.get(child) - 1);
                    if (endegree.getOrDefault(child, 0) == 1) {
                        queue.add(child);
                    }
                }
                n--;
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            result.add(queue.poll());
        }

        return result;
    }
}
