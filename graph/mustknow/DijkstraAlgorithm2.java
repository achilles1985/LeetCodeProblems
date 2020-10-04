package graph.mustknow;

import java.util.*;

public class DijkstraAlgorithm2 {

    public static void main(String[] args) {
        DijkstraAlgorithm2 s = new DijkstraAlgorithm2();
        System.out.println(s.findShortestPath(0, 5, new int[][]{
                {0,1,4}, {0,2,5},{0,3,6},
                {1,2,2}, {1,4,9},
                {2,3,4}, {2,5,1},
                {3,5,5}, {4,5,3}
        }));
    }

    // O(E + V*log(V)) - time, O(V + E) - space
    public int findShortestPath(int src, int dest, int[][] edges) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            int distance = edge[2];
            graph.computeIfAbsent(from, key -> graph.getOrDefault(key, new ArrayList<>())).add(new int[]{to, distance});
            graph.computeIfAbsent(to, key -> graph.getOrDefault(key, new ArrayList<>())).add(new int[]{from, distance});
        }
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> nodeToWeight = new HashMap<>();
        for (int node: graph.keySet()) {
            nodeToWeight.put(node, Integer.MAX_VALUE);
        }
        Queue<int[]> minHeap = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
        minHeap.add(new int[]{src, 0});
        nodeToWeight.put(src, 0);
        while (!minHeap.isEmpty()) {
            int[] node = minHeap.poll();
            int nodeWeight = node[1];
            if (node[0] == dest) {
                return nodeWeight;
            }
            for (int[] child: graph.getOrDefault(node[0], new ArrayList<>())) {
                int childNode = child[0];
                int childDistance = child[1];
                if (!visited.contains(childNode) && nodeWeight + childDistance < nodeToWeight.get(childNode)) {
                    int newChildWeight = nodeWeight + childDistance;
                    nodeToWeight.put(childNode, newChildWeight);
                    minHeap.add(new int[]{childNode, newChildWeight});
                    visited.add(childNode);
                }
            }
        }

        return -1;
    }

}
